package com.anchietastudent.tuts.topic;

import com.anchietastudent.tuts.topic.model.Topic;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class TopicDTO {

    public TopicDTO() {
    }

    private UUID id;

    private String title;

    private String content;

    private String link;

    private int index;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static Topic toTopicEntity(TopicDTO dto) {
        Topic topic = new Topic();
        topic.setContent(dto.getContent());
        topic.setLink(dto.getLink());
        topic.setTitle(dto.getTitle());
        topic.setIndex(dto.getIndex());
        topic.setId(dto.getId());
        return topic;
    }

    public static List<TopicDTO> toDTOs(Set<Topic> topics) {
        return topics.stream().map(TopicDTO::toDTO).collect(Collectors.toList());
    }

    public static TopicDTO toDTO(Topic topic) {
        TopicDTO dto = new TopicDTO();
        dto.setContent(topic.getContent());
        dto.setLink(topic.getLink());
        dto.setTitle(topic.getTitle());
        dto.setIndex(topic.getIndex());
        dto.setId(topic.getId());
        return dto;
    }
}
