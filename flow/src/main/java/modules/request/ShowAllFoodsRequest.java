package modules.request;

import modules.Request;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

/**
 *  show all foods registered in server
 *
 */
public class ShowAllFoodsRequest extends Request {

    public ShowAllFoodsRequest() {
        super("SHOW_ALL_FOODS_REQUEST");
    }

    public ShowAllFoodsRequest(GenericRecord record) {
        super(record);
    }

    @Override
    public void deserialize(GenericRecord record) {
        this.symbol = (GenericData.EnumSymbol) record.get("type");

    }

    @Override
    public GenericRecord serialize() {

        GenericRecord record = new GenericData.Record(getSchema());
        record.put("type", symbol);

        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/request/ShowAllFoodsRequest.avsc");

    }
}
