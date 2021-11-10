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

