package com.deekshith.myFirstProject.repository;

import com.deekshith.myFirstProject.entity.UserTable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserTable, ObjectId> {
}
