# Social Network API

Welcome to the Social API! This API allows you to interact with a social network graph.


## Tech Stack

This project is built using the following technologies:

- **Spring Boot:** The application framework for building Java-based enterprise applications.

- **Neo4j:** A graph database for storing and managing social network data.

- **Render:** A cloud platform used for hosting and deploying the Spring Boot application.

- The Neo4j database is deployed on an AWS EC2 instance. You can find the database dump file called  `neo4j_dump` in root dir for reference.

  **Note:** The Render instance hosting this API may spin down due to inactivity. If you experience slow loading, please be patient as it restarts.


## Base URL

The base URL for the API is [https://social-api-2c6s.onrender.com/social](https://social-api-2c6s.onrender.com/social).

## API Endpoints

### Get User by Name

**Endpoint:** `/user`  
**Method:** `GET`  
**Request Parameters:** `name` (String)  
**Example:** `/social/user?name=JohnDoe`

### Get Users Following

**Endpoint:** `/{name}/follows`  
**Method:** `GET`  
**Path Variable:** `name` (String)  
**Example:** `/social/JohnDoe/follows`

### Get Users Followers

**Endpoint:** `/{name}/followers`  
**Method:** `GET`  
**Path Variable:** `name` (String)  
**Request Parameters:** `page` (int, optional, default: 0), `size` (int, optional, default: 2)  
**Example:** `/social/JohnDoe/followers?page=0&size=2`

### Get Mutual Followers

**Endpoint:** `/mutualFollowers`  
**Method:** `GET`  
**Request Parameters:** `user1Name` (String), `user2Name` (String)  
**Example:** `/social/mutualFollowers?user1Name=DavidKim&user2Name=EmilyChen`

### Get All Users

**Endpoint:** `/all`  
**Method:** `GET`  
**Example:** `/social/all`

## Databasee 
<img width="887" alt="Screenshot 2023-11-11 at 7 27 08 AM" src="https://github.com/arfath11/Social-API/assets/74487575/aa3cf8cb-3386-40bd-8675-f35a32189321">


