package modules;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import utils.avro.AvroUnit;

public class Food extends AvroUnit {

    private String name;

    private double price;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Food(GenericRecord record) {
        super(record);
    }

    @Override
    public void deserialize(GenericRecord record) {
        this.name = record.get("name").toString();
        this.price = (double) record.get("price");
    }

    @Override
    public GenericRecord serialize() {
        GenericRecord record = new GenericData.Record(getSchema());
        record.put("name", name);
        record.put("price", price);
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
