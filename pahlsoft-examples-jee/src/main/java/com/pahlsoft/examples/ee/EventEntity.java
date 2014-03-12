package com.pahlsoft.examples.ee;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EventEntity {
    @Id
    protected int eventId;
    String title;
    String description;
    int status;


}
