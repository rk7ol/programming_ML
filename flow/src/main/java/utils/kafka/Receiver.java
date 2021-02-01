package utils.kafka;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import utils.avro.AvroDeserializer;

import java.time.Duration;
import java.util.*;

public class Receiver {
    private final Schema schema;
    private KafkaConsumer<String, byte[]> consumer;
    private AvroDeserializer deserializer;

    private static boolean isRunning = true;


    public Receiver(String brokerAddress, Schema schema, String... topics) {

        if (topics.length == 0)
            throw new IllegalStateException("topic number suppose more than 0");

        this.schema = schema;

        deserializer = new AvroDeserializer();


        Properties props = new Properties();
        props.setProperty("bootstrap.servers", brokerAddress);
        props.setProperty("group.id", "1");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("auto.commit.interval.ms", "100");
        consumer = new KafkaConsumer<>(props, new StringDeserializer(), new ByteArrayDeserializer());

        consumer.subscribe(Arrays.asList(topics));

    }


    public List<GenericRecord> receive() {


        List<GenericRecord> receiveList = new LinkedList<>();
        ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));



        for (ConsumerRecord<String, byte[]> record : records) {
            //decode
            receiveList.addAll(deserializer.deserialize(decode(record.value()), schema));

            consumer.commitAsync();
        }

        return receiveList;
    }

    private byte[] decode(byte[] bytes) {

        return Base64.getMimeDecoder().decode(bytes);
    }


    public void close(){
        consumer.close();
    }



}
