package modules;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import utils.avro.AvroUnit;

/**
 *  string name
 *
 *  double price
 *
 *  string method
 */
public class Food extends AvroUnit {

    private String name;

    private double price;

    private String method;


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getMethod() {
        return method;
    }

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Food(String name, String method, double price) {
        this.name = name;
        this.price = price;
        this.method = method;
    }

    public Food(GenericRecord record) {
        super(record);
    }

    @Override
    public void deserialize(GenericRecord record) {
        this.name = record.get("name").toString();
        this.price = (double) record.get("price");
        this.method = record.get("method").toString();
    }

    @Override
    public GenericRecord serialize() {
        GenericRecord record = new GenericData.Record(getSchema());
        record.put("name", name);
        record.put("price", price);

        if (method != null)
            record.put("method", method);

        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/Food.avsc");

    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
