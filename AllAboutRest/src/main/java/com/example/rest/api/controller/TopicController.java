package com.example.rest.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.api.model.Topic;
import com.example.rest.api.service.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {

	final private TopicService topicService;

	public TopicController(TopicService topicService) {
		this.topicService = topicService;
	}

	@GetMapping
	public ResponseEntity<List<Topic>> getAllTopics() {
		return ResponseEntity//
				.status(HttpStatus.OK)//
				.body(topicService.getAllTopics());
	}

	@PostMapping
	public ResponseEntity<Topic> saveTopic(@RequestBody Topic topic) {
		return ResponseEntity//
				.status(HttpStatus.CREATED)//
				.body(topicService.saveTopic(topic));

	}

}
