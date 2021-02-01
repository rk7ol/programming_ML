package utils.kafka;

import modules.Message;

import java.util.List;

public class MessageSender extends Sender{
    public MessageSender(String brokerAddress) {
        super(brokerAddress);
    }

    public void produce(List<? extends Message> messages) {
        super.produce(messages, messages.get(0).getTopic());
    }

    public void produce(Message message) {
        super.produce(message, message.getTopic());
    }
}
