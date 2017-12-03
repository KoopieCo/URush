package com.csce4623.rynolan.urush.models;

public class Announcement {
    String eventTitle;
    String eventLocation;
    String eventTime;

    public Announcement() {}

    public Announcement(String eventTitle, String eventLocation, String eventTime) {
        this.eventTitle = eventTitle;
        this.eventLocation = eventLocation;
        this.eventTime = eventTime;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
