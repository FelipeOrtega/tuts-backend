package com.anchietastudent.tuts.topic;

import com.anchietastudent.tuts.topic.model.Topic;

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
}
