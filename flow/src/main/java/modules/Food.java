package modules;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import utils.avro.AvroUnit;

import java.io.IOException;

public class Food extends AvroUnit {

    //food schema
    public static Schema FOOD_SCHEMA;

    static {
        try {
            //load schema
            FOOD_SCHEMA = new Schema.Parser().parse(Food.class.getClassLoader().getResourceAsStream("schemas/Food.avsc"));

        } catch ( IOException e) {
            e.printStackTrace();
        }

    }

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
        GenericRecord record = new GenericData.Record(FOOD_SCHEMA);
        record.put("name", name);
        record.put("price", price);
        return record;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
