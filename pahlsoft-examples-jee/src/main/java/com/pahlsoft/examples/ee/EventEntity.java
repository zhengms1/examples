package com.pahlsoft.examples.ee;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EventEntity {
    @Id
    private int eventId;
    private String title;

    public EventEntity(int eventId, String title, String description, int status) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    private String description;
    private int status;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
