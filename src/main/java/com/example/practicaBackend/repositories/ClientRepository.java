package com.example.practicaBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practicaBackend.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
