package utils.avro;

import org.apache.avro.generic.GenericRecord;

public interface AvroDeserializable {


    void deserialize(GenericRecord record);




}
