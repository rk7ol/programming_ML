package modules.response;

import modules.Response;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

/**
 *  register foods to server
 *
 *  int flag
 */
public class RegisterFoodResponse extends Response {

    private boolean flag;

    public boolean getFlag() {
        return flag;
    }

    public RegisterFoodResponse(boolean flag) {
        super("REGISTER_FOOD_RESPONSE");
        this.flag = flag;
    }

    public RegisterFoodResponse(GenericRecord record) {
        super(record);
    }

    @Override
    public void deserialize(GenericRecord record) {

        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.flag = (boolean) record.get("flag");

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
