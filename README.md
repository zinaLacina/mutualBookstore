
# Mutual BookStore

  **Mutual BookStore** is an application, that allows any user to search a book in google books API. The user can bookmark any book. Those books will stay on the user account for later consultation. The user can log in and bookmarks any number of books he wants. He can also delete a book from is bookmark list.
The application uses the microservice technology, we have Nginx proxy, frontend, backend, Grafana, Prometheus.
**The backend** uses open sources technologies such as :
 *Spring boot, Maven, Spring Security, JWT, JUnit, Spring actuator, Spring DevTools, Lombok, Spring Data with Mongo DB and so on ..*
 **The frontend** uses:
 *React, Redux, bootstrap 4, Axios, JWT, Node.*
 **Nginx proxy** is to avoid the user to directly access to the backend. It is for security purposes. It will reduce the DDoS attacks.
 **Grafana and Prometheus** do the monitoring of the application.
 
# APPLICATION ARCHITECTURE
  ![Architecture](https://github.com/zinaLacina/mutualBookstore/blob/master/bookstoremutual.png)

# BACKEND

The backend uses spring boot technologies. It uses Maven to execute and resolve dependencies management.

## Mongo Database structure
![Database structure](https://github.com/zinaLacina/mutualBookstore/blob/master/classDiagram.png)

## Security
The application uses Spring security combine with JWT to secure access of the data. To bookmark a book, you need to be login first. The application will create a token that will last 30 seconds. The creation of the token is managed by the class JwtTokenProvider.java

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
React uses JSX and a virtual dom that makes it very fast. We use the last version 16.11.0.
  

## Redux
Redux is an open-source JavaScript library for managing application state. It is most commonly used with libraries such as React or Angular for building user interfaces. Similar to Facebook's Flux architecture, it was created by Dan Abramov and Andrew Clark.( *wikipedia* )
The different state we store is found in the following code located in the src/reducers/index.js

    export  default  combineReducers({
	    errors:  errorReducer,
	    books:  bookReducer,
	    bookmark:  bookmarkReducer,
	    security:  securityReducer
    });

  

## Jwt web token

  
 
  # NGINX PROXY

# DEPLOYMENT

  
  

## Run locally

  

### With one command line (docker compose)

You can change the port of **8090** inside the docker-compose file at the line **30**.  Open your terminal, place you, in your favorite directory then run.
   

     git clone https://github.com/zinaLacina/mutualBookstore.git

After place your in the folder mutualBookstore by typing.
   

     cd mutualBookstore

Then run the following command

    docker-compose up -d --build

Then wait a few seconds(30 seconds) to let the spring app starts then type on your browser.

    http://localhost:8090/

### Normal run
Open your terminal, place you, in your favorite directory then run.

     git clone https://github.com/zinaLacina/mutualBookstore.git

After place your in the folder mutualBookstore by typing.

    cd mutualBookstore/bookstore

For the normal run, you need to open two tab or window of your terminal.
Inside the first terminal please type

    ./mvnw clean spring-boot:run
In the other terminal run the following commands:

    cd frontend
    yarn install 
    yarn start

if you are using npm run

    cd frontend
    npm install 
    npm start
Then you can access to the application in the link below
[http://localhost:3000/](http://localhost:3000/)
## Monitoring
After run the command docker-compose up, you should me be able to access to the different metrics of the application.
Prometheus link is 

    localhost:9090
   
Grafana link is

    localhost:3000

 You can find the credentials of **Grafana** inside the docker-compose file as environment variables, lines 8 and 9.  You can change and put your own. 
  During the **CI-CD** creation, this value will be added from the environment variable of pipeline support.
  
 - If you are using **Gitlab** put them inside *Gitlab ci-cd setting variable* then index them inside the *docker-compose file.*
 - If you are using **AWS Codepeline**, put them inside the Codebuild environment variable. You can put also inside the *SSM parameter store*.
 - If you are using **Travis ci** put go to setting and then variables.

## Deploy on aws

  
  

## CI-CD
