package modules.response;

import modules.Response;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

/**
 *
 *  settle window
 *
 *
 * double profit
 */
public class SettleResponse extends Response {

    private double profit;

    public double getProfit() {
        return profit;
    }

    public SettleResponse(double totallyPrice) {
        super("SETTLE_RESPONSE");
        this.profit = totallyPrice;
    }

    public SettleResponse(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord record) {

        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.profit = (double) record.get("profit");

    }

    @Override
    public GenericRecord serialize() {
        GenericRecord record = new GenericData.Record(getSchema());

        record.put("type", symbol);

        record.put("profit", profit);


        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/response/SettleResponse.avsc");

    }

}
