package com.anchietastudent.tuts.topic.repository;

import com.anchietastudent.tuts.topic.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
}
