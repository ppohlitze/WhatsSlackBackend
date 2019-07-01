package de.tub.ise.anwsys.resources;

import de.tub.ise.anwsys.model.Channel;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ChannelResourceAssembler extends ResourceAssemblerSupport<Channel, ChannelResource> {

    public ChannelResourceAssembler() {
        super(Channel.class, ChannelResource.class);
    }

    @Override
    public ChannelResource toResource(Channel channel) {
        ChannelResource channelResource = createResourceWithId(channel.getId(), channel);
        channelResource.setName(channel.getName());
        channelResource.setTopic(channel.getTopic());
        return channelResource;
    }
}
