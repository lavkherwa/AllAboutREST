package com.example.rest.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.api.exception.BadRequestException;
import com.example.rest.api.exception.NotFoundException;
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

	@GetMapping("/{id}")
	public ResponseEntity<Topic> getTopic(@PathVariable(value = "id") Long topicId) {
		Topic topic = topicService.getTopic(topicId);
		if (topic == null) {
			throw new NotFoundException("No topic found for id: " + topicId);
		}
		return ResponseEntity.status(HttpStatus.OK).body(topic);
	}

	@PostMapping
	public ResponseEntity<Topic> saveTopic(@RequestBody Topic topic) {

		if (topic.getName() == null || //
				topic.getName().equals("")) {
			throw new BadRequestException("Name is mandatory field for a topic!");
		}

		Topic result = topicService.saveTopic(topic);
		return ResponseEntity//
				.status(HttpStatus.CREATED)//
				.body(topicService.saveTopic(result));

	}

}
