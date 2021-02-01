package modules.request;

import modules.Request;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

public class FoodsRequest extends Request {


    private String ID;

    public FoodsRequest(String ID) {
        super("FOODSREQUEST");
        this.ID = ID;
    }

    public FoodsRequest(GenericRecord record) {
        super(record);
    }



    @Override
    public void deserialize(GenericRecord record) {
        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.ID = record.get("id").toString();



    }

    @Override
    public GenericRecord serialize() {
        GenericRecord record = new GenericData.Record(getSchema());

        record.put("type", this.symbol);
        record.put("id", ID);
        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/request/FoodsRequest.avsc");
    }


    public String getID() {
        return ID;
    }
}
