2.3 - result of:

`PS C:\Users\ToméCarvalho> curl http://localhost:8081/greeting?name=Saul%20Goodman`

`StatusCode     : 200`

`StatusDescription :`

`Content      : {"id":11,"content":"Hello, Saul Goodman!"}`

`RawContent     : HTTP/1.1 200`

​          `Transfer-Encoding: chunked`

​          `Keep-Alive: timeout=60`

​          `Connection: keep-alive`

​          `Content-Type: application/json`

​          `Date: Wed, 27 Oct 2021 11:12:12 GMT`



​          `{"id":11,"content":"Hello, Saul Goodma...`

`Forms       : {}`

`Headers      : {[Transfer-Encoding, chunked], [Keep-Alive, timeout=60], [Connection, keep-alive], [Content-Type, application/json]...}`

`Images       : {}`

`InputFields    : {}`

`Links       : {}`

`ParsedHtml     : System.__ComObject`

`RawContentLength  : 42`



## Review Questions

**A. What are the responsibilities/services of a “servlet container”?**

Servlets have no static main() method and thus they must be executed under the control of a servlet container. These containers manage the servlets, calling servlet methods and providing services the servlet needs when executing. They provide easy access to properties of the HTTP request (e.g., headers and parameters). 

When a servlet is called, the Web server passes the HTTP request to the servlet container, which, in turn, passes the it to the servlet.

In the course of managing a servlet, a container performs the following tasks:

- Create an instance of the servlet and call its `init()` method to initialize it;
- Construct a request object to pass to the servlet (this includes, among others: HTTP headers from the client, parameters and values passed, the URI of the servlet request);
- Construct a response object for the servlet;
- Invoke the servlet `service()` method;
- Call the servlet `destroy()` method when appropriate, for it to be garbage collected.



**B. Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)**

MVC is a design pattern used to decouple UI (view), data (model) and application logic (controller), achieving separation of concerns.

Requests are routed to a Controller that is responsible for working with the Model to perform actions and/or retrieve data. The Controller chooses the View to display and provides it with the Model. The View renders the final page, based on the data in the Model.

Example using 2.4:

In this case, the View is rather trivial. The user only interacts with the application via the URL, and the response is given as JSON data.

The Controller is responsible for fetching the data (quote or list of shows) according to the GET request. In order to achieve this, it interacts with the Model, which, in this case, is composed of static Java structures, as opposed to a database.



**C. Inspect the POM.xml for the previous Spring Boot projects. What is the role of the “starters” dependencies?**

Spring Boot Starter POMs are a set of convenient dependency descriptors that can be included in applications. They can be selected when using Spring Initializr and they're useful because they make it so that it's not necessary to search multiple places for dependency descriptors. For example, it's easier to add the Web starter than to add multiple separate dependencies like Spring MVC, Tomcat and Jackson.



**D. Which annotations are transitively included in the @SpringBootApplication?**

Many Spring Boot developers like their apps to use auto-configuration, component scan and be able to define extra configuration on their "application class".A single `@SpringBootApplication` annotation can be used to enable those three features, that is:

- `@EnableAutoConfiguration`: enable [Spring Boot’s auto-configuration mechanism](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.auto-configuration)

- `@ComponentScan`: enable `@Component` scan on the package where the application is located (see [the best practices](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code))

- `@SpringBootConfiguration`: enable registration of extra beans in the context or the import of additional configuration classes. An alternative to Spring’s standard `@Configuration` that aids [configuration detection](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing.spring-boot-applications.detecting-configuration) in your integration tests.

  

**E. Search online for the topic “Best practices for REST API design”. From what you could learn, select your “top 5” practices, and briefly explain them in you own words.**

1. Use JSON as the format for sending and receiving data

   JSON is the standard for data transferring. A lot of programming languages provide inbuilt methods to parse JSON data. Other formats like XML, for example, are often a hassle to decode and encode data.

2. Use nouns instead of verbs in endpoint paths

   HTTP methods (such as GET, POST, PUT, etc.) are already in verb form for performing basic CRUD operations. Nouns that represent the entity we're manipulating should be used as the pathname. The action should be indicated by the HTTP request method that we’re making.

3. Use nesting on endpoints to show relationships

   When designing endpoints, it makes sense to group those that contain associated information. In other words, if an object can contain another one, the endpoint should reflect that. For example, on imdb.com, the path for the main page of *The Witcher* is https://www.imdb.com/title/tt5180504, and the path for its episode list is https://www.imdb.com/title/tt5180504/episodes. Evidently, this makes sense because the show contains the episodes.

   However, the nesting shouldn't be too many levels deep, as this can make the API less readable.

4. Use filtering, pagination and sorting to retrieve data

   Databases behind REST APIs can become quite large. Retrieving a large amount of data all at once is usually slow and can affect the system's performance.

   Filtering reduces the total amount of data to retrieve and pagination makes it so that only a few results are returned at a time, to avoid tying up resources for too long.

5. Handle errors gracefully and return status codes

   Regular HTTP status codes (404 Not Found, 401 Unauthorized, etc.) should be used in responses to requests made to the API. This helps the users know what's going on. 

   The error codes should be accompanied by messages so that the maintainers have enough information to troubleshoot issues, but attackers can't use the error content for attacks.

