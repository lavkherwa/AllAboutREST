package com.example.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest.api.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String>{

}
