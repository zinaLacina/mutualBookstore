
# Mutual BookStore

  **Mutual BookStore** is an application, that allows any user to search a book in google books API. The user can bookmark any book. Those books will stay on the user account for later consultation. The user can log in and bookmarks any number of books he wants. He can also delete a book from is bookmark list.
The application uses the microservice technology, we have Nginx proxy, frontend, backend, Grafana, Prometheus.
**The backend** uses open sources technologies such as :
 *Spring boot, Maven, Spring Security, JWT, JUnit, Spring actuator, Spring DevTools, Lombok, Spring Data with Mongo DB and so on ..*
 **The frontend** uses:
 *React, Redux, bootstrap 4, Axios, JWT, Node.*
 **Nginx proxy** is to avoid the user to directly access to the backend. It is for security purposes. It will reduce the DDoS attacks.
 **Grafana and Prometheus** do the monitoring of the application.
 
# Application architecture
  ![Architecture](https://github.com/zinaLacina/mutualBookstore/blob/master/bookstoremutual.png)

# Backend

Introduction here

## Diagram

  

## Security

  

## Code

  

## MongoDB

  
  

# Frontend

  
  

## React

  

## Redux


  

## JWT WEB TOKEN

  
  
  

# Deployment

  
  

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
