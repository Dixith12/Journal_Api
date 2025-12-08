package com.deekshith.myFirstProject.repository;

import com.deekshith.myFirstProject.entity.JournalTable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalTable, ObjectId> {

}
