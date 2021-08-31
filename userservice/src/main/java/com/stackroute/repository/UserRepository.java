package com.stackroute.repository;

import com.stackroute.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    public Users findByIdAndPassword(String id, String password);
}
