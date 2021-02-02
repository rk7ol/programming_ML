package modules.response;

import modules.Response;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

public class RegisterFoodResponse extends Response {

    private int flag;

    public int getFlag() {
        return flag;
    }

    public RegisterFoodResponse(int flag) {
        super("REGISTERFOODRESPONSE");
        this.flag = flag;
    }

    public RegisterFoodResponse(GenericRecord record) {
        super(record);
    }

    @Override
    public void deserialize(GenericRecord record) {

        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.flag = (int) record.get("flag");

    }

    @Override
    public GenericRecord serialize() {
        GenericRecord record = new GenericData.Record(getSchema());
        record.put("type", symbol);


        record.put("flag", flag);


        return record;
    }

    @Override
    protected void registerSchema() {

        registerSchema(this.getClass(), "schemas/response/RegisterFoodResponse.avsc");

    }
}
