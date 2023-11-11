package com.example.with_2.repository;

import com.example.with_2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends Neo4jRepository<User, Long> {
    User findByName(@Param("name") String name);
    // Custom query to get all users
    @Query("MATCH (u:User) RETURN u")
    List<User> findAllUsers();



    // Custom query to get all users followed by a specific user
    @Query("MATCH (:User {name: $userName})-[:FOLLOWS]->(u:User) RETURN u")
    List<User> getUsersFollowing(@Param("userName") String userName);

    @Query(value = "MATCH (:User {name: $userName})<-[:FOLLOWS]-(u:User) RETURN u SKIP $skip LIMIT $limit",
            countQuery = "MATCH (:User {name: $userName})<-[:FOLLOWS]-(u:User) RETURN count(u)")
    Page<User> getUsersFollowerBy(@Param("userName") String userName, Pageable pageable);



    @Query("MATCH (u1:User {name: $user1Name})<-[:FOLLOWS]-(mutual:User)-[:FOLLOWS]->(u2:User {name: $user2Name}) RETURN mutual")
    List<User> getMutualFollowers(@Param("user1Name") String user1Name, @Param("user2Name") String user2Name);



}
