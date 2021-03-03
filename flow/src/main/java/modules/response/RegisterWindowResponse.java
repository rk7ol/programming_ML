package modules.response;

import modules.Response;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

/**
 * register window to server
 *
 *
 * string ID
 *
 */
public class RegisterWindowResponse extends Response {
    private String ID;

    public String getID() {
        return ID;
    }

    public RegisterWindowResponse(String session, String ID) {
        super(session, "REGISTER_WINDOW_RESPONSE");
        this.ID = ID;
    }

    public RegisterWindowResponse(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord record) {
        this.session = record.get("session").toString();
        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.ID = record.get("ID").toString();

    }

    @Override
    public GenericRecord serialize() {
        GenericRecord record = new GenericData.Record(getSchema());
        record.put("session", session);
        record.put("type", symbol);

        record.put("ID", ID);


        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/response/RegisterWindowResponse.avsc");

    }
}
