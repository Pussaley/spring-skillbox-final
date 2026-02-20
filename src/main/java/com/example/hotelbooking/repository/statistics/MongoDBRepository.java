package com.example.hotelbooking.repository.statistics;

import com.example.hotelbooking.statistic.entity.StatisticsInfoMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MongoDBRepository extends MongoRepository<StatisticsInfoMongoDB, UUID> {
}