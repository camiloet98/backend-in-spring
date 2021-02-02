package com.example.practicaBackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.practicaBackend.entities.Photo;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, Integer>{

}
