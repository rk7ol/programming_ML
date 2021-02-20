package utils.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

import java.io.IOException;
import java.util.HashMap;

public abstract class AvroUnit implements AvroSerializable, AvroDeserializable {

    //class-schema Map
    protected static HashMap<Class<? extends AvroUnit>, Schema> SCHEMA_MAP;

    static {
        SCHEMA_MAP = new HashMap<>();

    }


    protected abstract void registerSchema();


    /**
     *
     * check schema whether register
     *
     * @return true if register, false if not
     */
    private boolean schemaCheck(){
        return getSchema() != null;
    }

    protected Schema loadSchema(String resourcePath) {

        try {
            return new Schema.Parser().parse(AvroUnit.class.getClassLoader().getResourceAsStream(resourcePath));
        } catch (IOException e) {
            System.out.println("load schema: " + resourcePath + " fail!");
        }

        return null;

    }

    public Schema getSchema() {
        return SCHEMA_MAP.get(this.getClass());
    }

    public static Schema getSchemaByClass(Class<? extends AvroUnit> clazz){
        return SCHEMA_MAP.get(clazz);
    }

    protected void registerSchema(Class<? extends AvroUnit> clazz, Schema schema){
        SCHEMA_MAP.put(clazz, schema);
    }

    protected void registerSchema(Class<? extends AvroUnit> clazz, String path){

        if (!schemaCheck()){
            SCHEMA_MAP.put(clazz, loadSchema(path));
        }

    }

    protected AvroUnit(){
        registerSchema();
        if(!schemaCheck()){
            throw new IllegalStateException("schema not register:" + this.getClass());
        }


    }

    protected AvroUnit(GenericRecord record) {
        registerSchema();
        if(!schemaCheck()){
            throw new IllegalStateException("schema not register:" + this.getClass());
        }
        this.deserialize(record);
    }


}
