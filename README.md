# API-Docs
### Register
* Method <br>
POST
* URL <br>
  /register
* Body Request
```
{
    "username": "test",
    "gender": "female",
    "email": "test@email.com",
    "password": "password"
}
```
* Response
```
{
    "status": "success",
    "message": "User created successfully"
}
```
### Login
* Method <br>
POST
* URL <br>
  /login
* Body Request
```
{
    "email": "test@email.com",
    "password": "password"
}
```
* Response
```
{
    "status": "success",
    "message": "login successful",
    "username": "test",
    "email": "test@gmail.com",
    "gender": "female",
    "data": {
        "token": "<token>"
    }
}
```
### Read User
* Method <br>
GET
* URL <br>
/readUser
* Headers <br>
Key = Authorization <br>
Value = Bearer (token from login)
* Response
```
{
    "status": "success",
    "message": "read successful",
    "data": {
        "id": (user id),
        "username": "test",
        "gender": "female",
        "email": "test@gmail.com"
    }
}
```
### Update User
* Method <br>
PUT
* URL <br>
/updateUser
* Headers <br>
Key = Authorization <br>
Value = Bearer (token from login)
* Body Request
```
{
  "username": "test",
  "gender": "female",
  "email": "test@gmail.com",
  "password": "password"
}
```
* Response
```
{
    "status": "success",
    "message": "update successful"
}
```
### Delete User
* Method <br>
POST
* URL <br>
  /deleteUser
* Headers <br>
Key = Authorization <br>
Value = Bearer (token from login)
* Body Request
```
{
    "password": "password"
}
```
* Response
```
{
    "status": "success",
    "message": "Delete successful"
}
```
### Predict
* Method <br>
POST
* URL <br>
  /predict
* Headers <br>
Key = Authorization <br>
Value = Bearer (token from login)
* Body Request
Key = image <br>
Value = (select files)
* Response
```
{
    "status": "success",
    "message": "Model predicted successfully",
    "data": {
        "id": "505db21a-69be-48f8-a053-a0d16e71998f",
        "predictedClassName": "mid-dark",
        "colorCodeSkin": [
            "#906d60"
        ],
        "predictions": {
            "0": 6.849454052826331e-7,
            "1": 5.806282388221007e-8,
            "2": 0.9927035570144653,
            "3": 0.007295762654393911
        },
        "predictedClassIndex": 2,
        "createdAt": "2024-06-15T11:56:24.735Z",
        "recommendation": [
            "#8c001a",
            "#d7c0d0",
            "#64113f",
            "#2e294e"
        ],
        "colorNamePalette": [
            "Red cherry",
            "Lavender",
            "Purple",
            "Dark Grey"
        ],
        "colorCodeJewelry": [
            "#ffc536"
        ],
        "jewelryRecommendation": [
            "gold"
        ]
    }
}
```
