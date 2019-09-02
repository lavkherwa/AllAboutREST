package com.example.rest.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rest.api.model.Topic;
import com.example.rest.api.repository.TopicRepository;

@Service
public class TopicService {

	final private TopicRepository topicRepository;

	public TopicService(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(topics::add);

		return topics;
	}

	public Topic saveTopic(Topic topic) {
		return topicRepository.save(topic);
	}

}
