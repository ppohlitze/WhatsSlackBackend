package de.tub.ise.anwsys.resources;

import de.tub.ise.anwsys.model.Message;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class MessageResourceAssembler extends ResourceAssemblerSupport<Message, MessageResource> {

    public MessageResourceAssembler() {
        super(Message.class, MessageResource.class);
    }

    @Override
    public MessageResource toResource(Message message) {
        MessageResource messageResource = createResourceWithId(message.getId(), message);
        messageResource.setTimestamp(message.getTimestamp());
        messageResource.setContent(message.getContent());
        messageResource.setCreator(message.getCreator());
        messageResource.setChannelId(message.getChannelId());
        return messageResource;
    }
}
