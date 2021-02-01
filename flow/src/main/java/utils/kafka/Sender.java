package utils.kafka;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import utils.avro.AvroSerializable;
import utils.avro.AvroSerializer;
import utils.avro.AvroSerializerFactory;
import utils.avro.AvroUnit;

import java.util.*;

/**
 * produce crawl result
 */
public class Sender {

    private final Producer<String, byte[]> kafkaProducer;

    private final ObjectPool<AvroSerializer> serializerPool;

    public Sender(String brokerAddress) {

        Properties props = new Properties();
        props.put("bootstrap.servers", brokerAddress);
        props.put("acks", "1");

        kafkaProducer = new KafkaProducer<>(props, new StringSerializer(), new ByteArraySerializer());

        serializerPool = new GenericObjectPool<>(new AvroSerializerFactory());


    }


    public void produce(List<? extends AvroUnit> datum, String topic) {



        if (datum == null) {
            return;
        }

        if (datum.size() != 0) {

            Schema schema = datum.get(0).getSchema();

            List<GenericRecord> records = new LinkedList<>();
            for (AvroSerializable data : datum) {
                records.add(data.serialize());

            }

            AvroSerializer serializer = null;

            try {
                serializer = serializerPool.borrowObject();
                byte[] encodedRecord = Base64.getMimeEncoder().encode(serializer.serialize(records, schema));

                kafkaProducer.send(new ProducerRecord<>(topic, Calendar.getInstance().toString(), encodedRecord));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (serializer != null) {
                    try {
                        serializerPool.returnObject(serializer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }


        }
    }


    public void produce(AvroUnit data, String topic){
        produce(Collections.singletonList(data), topic);

    }

    public void close(){
        kafkaProducer.close();
        serializerPool.close();
    }


}
