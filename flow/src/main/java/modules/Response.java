package modules;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import utils.avro.AvroUnit;

import java.io.IOException;

public abstract class Response extends Message {



    private static Schema RESPONSE_ENUM_SCHEMA;

    static {
        try {
            //load schema
            RESPONSE_ENUM_SCHEMA = new Schema.Parser().parse(Response.class.getClassLoader().getResourceAsStream("schemas/ResponseType.avsc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected Response(String symbol){
        super(symbol);
    }


    protected Response(GenericRecord record){
        super(record);
    }

    @Override
    protected GenericData.EnumSymbol createSymbol(String symbol) {
        return new GenericData.EnumSymbol(RESPONSE_ENUM_SCHEMA, symbol);
    }


}
