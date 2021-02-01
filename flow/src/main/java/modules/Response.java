package modules;

import modules.response.FoodsResponse;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.io.IOException;

public abstract class Response extends Message {



    private static Schema RESPONSE_ENUM_SCHEMA;

    static {
        try {
            //load schema
            RESPONSE_ENUM_SCHEMA = new Schema.Parser().parse(FoodsResponse.class.getClassLoader().getResourceAsStream("schemas/ResponseType.avsc"));
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
