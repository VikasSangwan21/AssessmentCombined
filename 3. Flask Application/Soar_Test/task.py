from flask import Flask, request, jsonify
import sqlite3
import jwt
import requests
import json
import subprocess  # For executing a shell command
import os

app = Flask(__name__)


def get_db_connection():
    conn = sqlite3.connect('database.db')
    #conn.row_factory = sqlite3.Row
    cursor = conn.cursor()
    return conn


def generateJWT(payload):
    secret = '123456'
    algorithm = 'HS256'
    #token = jwt.encode(payload,secret,algorithm)
    token = jwt.encode(payload,secret, algorithm=algorithm)
    print(token)
    return token

def decodeNoneJwt(token):
    secret = '123456'
    algorithm = 'HS256'
    data = jwt.decode(token,secret, options={"verify_signature":False})
    print(data)
    return data

@app.route("/")
def hello_world():
    dbConn = get_db_connection()
    testConn = dbConn.cursor()
    testConn = dbConn.execute('select * from users').fetchall()
    dbConn.close()
    print(testConn)
    # for x in testConn:
    #     print (x)
    return "<p>Hello, World!</p>"

@app.route("/client_registeration", methods = ['POST'])
def registerToSite():
    #Paramters -> [fullName, userName, email, password, phone]
    fullName = request.form['fullName']
    userName = request.form['userName']
    email = request.form['email']
    password = request.form['password']
    phone = request.form['phone']
    if fullName != '' and userName != '' and email != '' and password != '' and phone != '':
        # Check if email already exist
        dbConn = get_db_connection()
        dbCursor = dbConn.cursor()
        q = 'select userName from users where email = "' + email + '"'
        dbData = dbCursor.execute(q).fetchall()
        if len(dbData) > 0:
            return {'msg':'Email already Exist'}
        
        dbCursor.execute("INSERT INTO users (fullName, userName, email, password, phone, privillage) VALUES (?, ?, ?, ?, ?, ?)",
            (fullName, userName, email, password, phone, 2)
            )
        dbConn.commit()
        dbConn.close()       
        return {'msg':'User Registered'}
    else:
        return {'msg':'Invalid Data'}
    

@app.route("/client_login", methods = ['Post'])
def loginToSite():
    userName = request.form['userName']
    email = request.form['email']
    password = request.form['password']
    qMail = 'select privillage from users where email = "' + email +'" and password = "' + password + '"'
    qUser = 'select privillage from users where userName = "' + userName +'"'
    dbConn = get_db_connection()
    dbCursor = dbConn.cursor()
    if email != '':
        dbData = dbCursor.execute(qMail).fetchall()
        if len(dbData) > 0:
            role = dbData[0][0]
            payload = {'userName':userName,'email':email,'role':role}
            token = generateJWT(payload)
            return {'token':token}
        else:
            return {'msg':'In correct email or password'}
    
        
    elif userName != '':
        dbData = dbCursor.execute(qUser).fetchall()
        if len(dbData) > 0:
            role = dbData[0][0]
            payload = {'userName':userName,'email':email,'role':role}
            token = generateJWT(payload)
            return {'token':token}
        else:
            return {'msg':'In correct username or password'}
        
    else:
        return {'msg':'Failed'}


@app.route("/update_info",methods = ['POST'])
def updateUserPassword():
    token = request.form['token']
    currentPassword = request.form['currentPassword']
    newPassword = request.form['newPassword']
    
    try:
        #decode token
        data = decodeNoneJwt(token)
        updatePassByUsername = 'update users set password = "' + newPassword + '" where userName = "' + data['userName'] + '"'
        updatePassByEmail = 'update users set password = "' + newPassword + '" where userName = "' + data['email'] + '"'
        selectQ = 'select privillage from users where userName = "{userName}" and email = "{email}" and password = "{password}"'.format(userName = data['userName'],email=data['email'],password = currentPassword)
        #Database
        print(selectQ)
        dbConn = get_db_connection()
        dbCursor = dbConn.cursor()
        dbData = dbCursor.execute(selectQ).fetchall()
        print(dbData)

        if data['role'] == 1:
            dbCursor.execute(updatePassByUsername)
            dbConn.commit()
            dbConn.close()
            return {'msg':'Passowrd Reseted Forced By Admin Role'}
        
        if len(dbData) > 0:
            dbCursor.execute(updatePassByUsername)
            dbConn.commit()
            dbConn.close()
            return {'msg':'Passowrd Reseted'}
        else:
            return {'msg':'Invalid Data'}

    except:
        return {'msg':'Token Not Valid'}

    return {'msg':'Error'}
    

@app.route("/products",methods = ['GET'])
def listProducts():
    try:
        source = request.form['source']
        print(source)
        if source == '':
            source = 'https://dummyjson.com/products'
        print(source)
        #Ping before send request
        try:
            cmd = 'ping -c 1 '+ source
            response = os.system(cmd)
            print(response)
        except:
            pass

        data = requests.get(source).content
        jsonData = {}
        try:
            jsonData = json.loads(data)
            return jsonData
        except:
            return data
    except:
        return {'msg':'Error'}




    

if __name__ == '__main__':  
   app.run()  