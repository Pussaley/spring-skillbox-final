package com.example.hotelbooking.repository.statistics;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MongoDBRepository extends MongoRepository<Object, UUID> {
}