package modules.request;

import modules.Food;
import modules.Request;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

/**
 *
 *  register food to server
 *
 * string name
 *
 * string method
 *
 * double price
 *
 */
public class RegisterFoodRequest extends Request {


    private String name;

    private String method;

    private double price;

    public String getName() {
        return name;
    }

    public String getMethod() {
        return method;
    }

    public double getPrice() {
        return price;
    }
    

    public RegisterFoodRequest(String name, String method, double price) {
        super("REGISTER_FOOD_REQUEST");
        this.name = name;
        this.method = method;
        this.price = price;
    }

    public RegisterFoodRequest(GenericRecord record) {
        super(record);
    }

    @Override
    public void deserialize(GenericRecord record) {

        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.name = record.get("name").toString();
        this.method = record.get("method").toString();
        this.price = (double) record.get("price");

    }

    @Override
    public GenericRecord serialize() {

        GenericRecord record = new GenericData.Record(getSchema());

        record.put("type", symbol);

        record.put("name", name);
        record.put("method", method);
        record.put("price", price);

        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/request/RegisterFoodRequest.avsc");

    }
}
