
# Mutual BookStore

  **Mutual BookStore** is an application, that allows any user to search a book in google books API. The user can bookmark any book. Those books will stay on the user account for later consultation. The user can log in and bookmarks any number of books he wants. He can also delete a book from is bookmark list.
The application uses the microservice technology, we have Nginx proxy, frontend, backend, Grafana, Prometheus.
**The backend** uses open sources technologies such as :
 *Spring boot, Maven, Spring Security, JWT, JUnit, Spring actuator, Spring DevTools, Lombok, Spring Data with Mongo DB and so on ..*
 **The frontend** uses:
 *React, Redux, bootstrap 4, Axios, JWT, Node.*
 **Nginx proxy** is to avoid the user to directly access to the backend. It is for security purposes. It will reduce the DDoS attacks.
 **Grafana and Prometheus** do the monitoring of the application. 
 - [Application architecture](#application-architecture)
 - [Backend](#backend)
 - [Frontend](#frontend)
 - [Deployment locally and online](#deployment)

This is a temporary link I will deploy a multi Docker on ECS with a configuration of Grafana, Cloudwatch [http://bookstore-env.exjwu6manb.us-east-1.elasticbeanstalk.com/](http://bookstore-env.exjwu6manb.us-east-1.elasticbeanstalk.com/)

```diff
- NB: Many variables inside my different configuration files have to be hidden and manage by environment variables, but in the purposes to avoid spending time in the configuration I opened them.
```
 
# APPLICATION ARCHITECTURE
  ![Architecture](https://github.com/zinaLacina/mutualBookstore/blob/master/bookstoremutual.png)

# BACKEND

The backend uses spring boot technologies. It uses Maven to execute and resolve dependencies management.

## Mongo Database structure
![Database structure](https://github.com/zinaLacina/mutualBookstore/blob/master/classDiagram.png)
## Spring profile
We have two different profiles for the application. Dev profile for the development, and Prod profile for the production.
## Testing
### Mockito For BookController Unit Test as TDD(Test Driven Development)
Check if our search book endpoint work. This part of the code show that. When we type the value **sprin** in the search input we found items. The test method check that.
```java
 @Test
    void searchBooks() throws Exception{
        mockMvc.perform(get("/api/books/sprin").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalItems").exists());
    }

```
### Cucumber BDD (Behavior-Driven Development)
Cucumber aims at collaboration with non technical people.
You can find a example of cucumber file in test/features/user.feature
```ruby
Feature: User management

  Scenario: Retrieve user
    When I search user 'zina'
    Then the user is found
    And his last fullName is 'ZINA LACINA'
```
## Security
The application uses Spring security combine with JWT to secure access of the data. To bookmark a book, you need to be login first. The application will create a token that will last 5 minutes. The creation of the token is managed by the class JwtTokenProvider.java
```java
public String generateToken(Authentication authentication){  
    UserBookstore user = (UserBookstore)authentication.getPrincipal();  
    Date now = new Date(System.currentTimeMillis());  
      Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);  
      String userId = user.getId();  
      Map<String,Object> claims = new HashMap<>();  
      claims.put("id", (user.getId()));  
      claims.put("username", user.getUsername());  
      claims.put("fullName", user.getFullName());    
     return Jwts.builder()  
                .setSubject(userId)  
                .setClaims(claims)  
                .setIssuedAt(now)  
                .setExpiration(expiryDate)  
                .signWith(SignatureAlgorithm.HS512, SECRET)  
                .compact();  
    }
```

## Exceptions management
I create my own exceptions management to make more powerful the application likes:

    BookmarkExceptionResponse.java, BookmarkIdException.java, 
    BookmarkNotFoundException.java,BookmarkNotFoundExceptionResponse.java
    CustomResponseEntityExceptionHandler.java, InvalidLoginResponse.java
    UsernameAlreadyExistsException.java, UsernameAlreadyExistsResponse.java

  

## MongoDB Security Group
To let people making the test locally, I whitelisted all coming addresses in the security group.
![MongoDB SG](https://github.com/zinaLacina/mutualBookstore/blob/master/mongodbSecurity.png)
  

# FRONTEND
The frontend of the application using React combined with Redux, Axios to retrieve, provide, and display the data to the end-user.
  
  

## React
React is a JavaScript library for building user interfaces. It is maintained by Facebook and a community of individual developers and companies. React can be used as a base in the development of single-page or mobile applications, as it is optimal for fetching rapidly changing data that needs to be recorded. ( *wikipedia* )
React uses JSX and a virtual dom that makes it very fast. I use the last version 16.11.0.
  

## Redux
Redux is an open-source JavaScript library for managing application state. It is most commonly used with libraries such as React or Angular for building user interfaces. Similar to Facebook's Flux architecture, it was created by Dan Abramov and Andrew Clark.( *wikipedia* )
The different state I store is found in the following code located in the src/reducers/index.js
```javascript
export  default  combineReducers({
    errors:  errorReducer,
    books:  bookReducer,
    bookmark:  bookmarkReducer,
    security:  securityReducer
 });
```
  

## Jwt web token
_JSON Web Token_ is an Internet standard for creating JSON-based access tokens that assert some number of claims.
 I used the framework jwt decode to decode the encoded value. You can find the example here.
 ![JWT Decode interface](https://github.com/zinaLacina/mutualBookstore/blob/master/jwtEncode.png)
 
  # NGINX PROXY
Nginx is a web server which can also be used as a reverse proxy, load balancer, mail proxy and HTTP cache. The software was created by Igor Sysoev and first publicly released in 2004. A company of the same name was founded in 2011 to provide support and Nginx plus paid software( *wikipedia* ).
I use Nginx to limit users to access directly to the API. It reduces DDoS attacks. Please have a look at the configuration in nginx.conf file inside the frontend folder.
![NGinx conf](https://github.com/zinaLacina/mutualBookstore/blob/master/nginxconf.png)

# DEPLOYMENT


## Run locally


### With with docker compose

You can change the port of **8090** inside the docker-compose file at the line **30**.  Open your terminal, place you, in your favorite directory then run.
   ```bash
   git clone https://github.com/zinaLacina/mutualBookstore.git
   ```

After place your in the folder mutualBookstore by typing.
   ```bash
   cd mutualBookstore
   ```

Then run the following command
```bash 
docker-compose up -d --build
```

Then wait a few seconds(30 seconds) to let the spring app starts then type on your browser.

    http://localhost:8090/

### Run separately
Open your terminal, place you, in your favorite directory then run.
 ```bash
   git clone https://github.com/zinaLacina/mutualBookstore.git
   ```

After place your in the folder mutualBookstore by typing.
```bash
cd mutualBookstore/bookstore
```
For the normal run, you need to open two tab or window of your terminal.
Inside the first terminal please type
```bash 
./mvnw clean spring-boot:run
```
In the other terminal run the following commands:
```bash
cd frontend
yarn install 
yarn start
```

if you are using npm run
```bash
cd frontend
npm install 
npm start
```
Then you can access to the application in the link below
[http://localhost:3000/](http://localhost:3000/)
## Monitoring
After run the command docker-compose up, you should me be able to access to the different metrics of the application.
Prometheus link is 
```bash
localhost:9090
```
   
Grafana link is Credentials username **admin** and password **pasword**

```bash
localhost:3000
```

You can find the credentials of **Grafana** inside the docker-compose file as environment variables, lines 8 and 9.  You can change and put your own. 
During the **CI-CD** creation, this value will be added from the environment variable of pipeline support.
  
- If you are using **Gitlab** put them inside *Gitlab ci-cd setting variable* then index them inside the *docker-compose file.*
- If you are using **AWS Codepeline**, put them inside the Codebuild environment variable. You can put also inside the *SSM parameter store*.
- If you are using **Travis ci** put go to setting and then variables.

## Deploy on aws
This is a temporary link I will deploy a multi Docker on ECS with a configuration of Grafana, Cloudwatch
[http://bookstore-env.exjwu6manb.us-east-1.elasticbeanstalk.com/](http://bookstore-env.exjwu6manb.us-east-1.elasticbeanstalk.com/)
  

## CI-CD

# WHAT NEXT ? 
I can introduce **KAFKA** to stream the data coming from the Google books API, then a consumer will take the role to save to the **Books** collection. So if it happens that we don't have access anymore to the Google books API for any reason, the application will still continue to work.
