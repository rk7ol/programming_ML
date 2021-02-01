package modules;

import modules.response.FoodsResponse;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.io.IOException;

public abstract class Request extends Message {

    private static Schema REQUEST_ENUM_SCHEMA;

    static {
        try {
            //load schema
            REQUEST_ENUM_SCHEMA = new Schema.Parser().parse(FoodsResponse.class.getClassLoader().getResourceAsStream("schemas/RequestType.avsc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Request(String symbol){
        super(symbol);
    }


    protected Request(GenericRecord record){
        super(record);
    }

    @Override
    protected GenericData.EnumSymbol createSymbol(String symbol) {
        return new GenericData.EnumSymbol(REQUEST_ENUM_SCHEMA, symbol);
    }
}
