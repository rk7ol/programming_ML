package modules.response;

import modules.Response;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

/**
 *  register food to server
 *
 *  int flag
 */
public class AddFoodsResponse extends Response {

    private int flag;

    public int getFlag() {
        return flag;
    }

    public AddFoodsResponse(int flag) {
        super("ADD_FOODS_RESPONSE");
        this.flag = flag;
    }

    public AddFoodsResponse(GenericRecord record) {
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
        registerSchema(this.getClass(), "schemas/response/AddFoodsResponse.avsc");

    }
}
