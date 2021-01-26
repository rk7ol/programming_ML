package utils.avro;

import org.apache.avro.generic.GenericRecord;

public interface AvroSerializable {

    GenericRecord serialize();
}
