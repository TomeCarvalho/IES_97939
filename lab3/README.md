### 3.1

b)

* **The “UserController” class gets an instance of “userRepository” through its constructor; how is this new repository instantiated?**

  The repository is automatically instantiated thanks to the "`@Autowired`" annotation in the `UserController` constructor.

  

* **List the methods invoked in the “userRepository” object by the “UserController”. Where are these methods defined?**

  The methods invoked are:

  * `findAll`
  * `save`
  * `findById`
  * `delete`

  They're defined in the `CrudRepository` class, which `UserRepository` extends.

  

* **Where is the data being saved?**

  The data is being saved in memory. 

  

* **Where is the rule for the “not empty” email address defined?**

  The rule for the "not empty" email address field is defined through the `@NotBlank(message = "Email is mandatory")` annotation above the declaration `private String email;`.



​	

### 3.2

Guide followed to get the Docker MySQL DB working: https://medium.com/tech-learn-share/docker-mysql-access-denied-for-user-172-17-0-1-using-password-yes-c5eadad582d3

**Examples of some requests sent through Postman**

1. HTTP Method: **POST** , Request URL: http://localhost:8080/api/v1/employees 

   Body:

   `{"firstName": "John", "lastName": "Cena", "emailId": "johncena@gmail.com"}`

   Response:

   `{"id": 1, "firstName": "John", "lastName": "Cena", "emailId": "johncena@gmail.com"}`

2. HTTP Method: **GET**, Request URL: http://localhost:8080/api/v1/employees/1

   Response:

   `{"id": 1, "firstName": "John", "lastName": "Cena", "emailId": "johncena@gmail.com"}`

Before 3, I added a few more employees.

3. HTTP Method: **GET**, Request URL: http://localhost:8080/api/v1/employees

   Response:

   `[`

     `{"id": 1, "firstName": "John", "lastName": "Cena",`

   ​    `"emailId": "johncena@gmail.com"},`

     `{"id": 3, "firstName": "Dwight", "lastName": "Schrute", `

   ​    `"emailId": "dwight@m.com"},`

     `{"id": 4, "firstName": "Jim", "lastName": "Halpert",`

   ​    `"emailId": "jim@m.com"}`

   `]`

4. HTTP Method: PUT, Request URL: http://localhost:8080/api/v1/employees/1

   Body:

   `{"firstName": "Pam", "lastName": "Beesly", "emailId": "pam@m.com"}`

   Response:

   `{"id": 1, firstName": "Pam", "lastName": "Beesly", "emailId": "pam@m.com"}`

5. HTTP Method: DELETE, Request URL: http://localhost:8080/api/v1/employees/3

   Response:

   `{"deleted": true}`

