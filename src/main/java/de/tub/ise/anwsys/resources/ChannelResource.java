package de.tub.ise.anwsys.resources;

import org.springframework.hateoas.ResourceSupport;

public class ChannelResource extends ResourceSupport {

    private String name;

    private String topic;

    public ChannelResource(String name, String topic) {
        this.name = name;
        this.topic = topic;
    }

    public ChannelResource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
