package utils.avro;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;


public class AvroSerializerFactory extends BasePooledObjectFactory<AvroSerializer> {


    @Override
    public AvroSerializer create() {
        return new AvroSerializer();
    }

    @Override
    public PooledObject<AvroSerializer> wrap(AvroSerializer avroSerializer) {
        return new DefaultPooledObject<>(avroSerializer);
    }


}
