package de.tub.ise.anwsys.resources;

import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

public class MessageResource extends ResourceSupport {

    private LocalDateTime timestamp;

    private String content;

    private String creator;

    private long channelId;

    public MessageResource(LocalDateTime timestamp, String content, String creator, long channelId) {
        this.timestamp = timestamp;
        this.content = content;
        this.creator = creator;
        this.channelId = channelId;
    }

    public MessageResource() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }
}
