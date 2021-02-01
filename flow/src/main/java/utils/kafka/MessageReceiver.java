package utils.kafka;

import modules.Message;
import org.apache.avro.generic.GenericRecord;
import utils.avro.AvroUnit;

import java.util.List;
import java.util.stream.Collectors;

public class MessageReceiver extends Receiver {
    public MessageReceiver(String brokerAddress, Class<? extends Message> type) {
        super(brokerAddress, AvroUnit.getSchemaByClass(type), Message.getTopicByClass(type));
    }

    public List<? extends Message> receiveMessage() {
        List<GenericRecord> records = super.receive();

        return records.stream().map(Message::parseFromRecord).collect(Collectors.toList());
    }
}
