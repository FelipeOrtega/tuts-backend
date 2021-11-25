package com.anchietastudent.tuts.topic;

import com.anchietastudent.tuts.topic.model.Topic;

import java.util.List;
import java.util.stream.Collectors;

public class TopicDTO {

    public TopicDTO() {
    }

    private String title;

    private String content;

    private String link;

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

    public static Topic toTopicEntity(TopicDTO dto) {
        Topic topic = new Topic();
        topic.setContent(dto.getContent());
        topic.setLink(dto.getLink());
        topic.setTitle(dto.getTitle());
        return topic;
    }

    public static List<TopicDTO> toDTOs(List<Topic> topics) {
        return topics.stream().map(TopicDTO::toDTO).collect(Collectors.toList());
    }

    public static TopicDTO toDTO(Topic topic) {
        TopicDTO dto = new TopicDTO();
        dto.setContent(topic.getContent());
        dto.setLink(topic.getLink());
        dto.setTitle(topic.getTitle());
        return dto;
    }
}
