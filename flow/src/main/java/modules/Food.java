package modules;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import utils.avro.AvroUnit;

import java.io.Serializable;

/**
 * string ID
 * <p>
 * string name
 * <p>
 * double price
 * <p>
 * string method
 */
public class Food extends AvroUnit implements Serializable {


    private String ID;

    private String name;

    private double price;

    private String method;

    public String getID() {
        return ID;
    }

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

    public Food(String ID, String name, double price, String method) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.method = method;
    }

    public Food(GenericRecord record) {
        super(record);
    }

    @Override
    public void deserialize(GenericRecord record) {

        if (record.get("ID") != null)
            this.ID = record.get("ID").toString();

        this.name = record.get("name").toString();
        this.price = (double) record.get("price");

        if (record.get("method") != null)
            this.method = record.get("method").toString();
    }

    @Override
    public GenericRecord serialize() {
        GenericRecord record = new GenericData.Record(getSchema());

        if (ID != null)
            record.put("ID", ID);


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
