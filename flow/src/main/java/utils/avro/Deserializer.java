package utils.avro;

import org.apache.avro.Schema;

import java.util.List;

public interface Deserializer<E> {

    List<E> deserialize(byte[] dataByteArray, Schema schema);
}
