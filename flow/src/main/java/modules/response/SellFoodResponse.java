package modules.response;

import modules.Response;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

/**
 *  sell foods
 *
 *  double totallyPrice
 *
 */
public class SellFoodResponse extends Response {

    private double totallyPrice;

    public double getTotallyPrice() {
        return totallyPrice;
    }

    public SellFoodResponse(String session, double totallyPrice) {
        super(session, "SELL_FOOD_RESPONSE");
        this.totallyPrice = totallyPrice;
    }

    public SellFoodResponse(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord record) {
        this.session = record.get("session").toString();
        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.totallyPrice = (double) record.get("totallyPrice");

    }

    @Override
    public GenericRecord serialize() {
        GenericRecord record = new GenericData.Record(getSchema());
        record.put("session", session);
        record.put("type", symbol);

        record.put("totallyPrice", totallyPrice);


        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/response/SellFoodResponse.avsc");

    }
}
