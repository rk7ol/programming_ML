package modules.request;

import modules.Request;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

/**
 *  show foods of window
 *
 *  string ID
 *
 */
public class ShowWindowFoodsRequest extends Request {


    private String ID;

    public String getID() {
        return ID;
    }

    public ShowWindowFoodsRequest(String ID) {
        super("SHOW_WINDOW_FOODS_REQUEST");
        this.ID = ID;
    }

    public ShowWindowFoodsRequest(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord record) {

        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.ID = record.get("ID").toString();

    }

    @Override
    public GenericRecord serialize() {
        GenericRecord record = new GenericData.Record(getSchema());

        record.put("type", symbol);

        record.put("ID", ID);


        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/request/ShowWindowFoodsRequest.avsc");

    }
}