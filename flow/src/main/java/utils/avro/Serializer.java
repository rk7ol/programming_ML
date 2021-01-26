package utils.avro;

import org.apache.avro.Schema;

import java.util.List;

public interface Serializer<E> {

    byte[] serialize(List<E> dataList, Schema schema);

}
