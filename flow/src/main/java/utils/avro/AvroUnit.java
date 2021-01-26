package utils.avro;

import org.apache.avro.generic.GenericRecord;

public abstract class AvroUnit implements AvroSerializable, AvroDeserializable {


    protected AvroUnit(GenericRecord record) {
        this.deserialize(record);
    }

    protected AvroUnit() {
    }

}
