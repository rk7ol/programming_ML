package modules;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericArray;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import utils.avro.AvroUnit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Foods extends AvroUnit implements Serializable {

    private List<Food> content;

    //food array schema
    private static Schema FOOD_ARRAY_SCHEMA;

    static {
        try {
            //load schema
            FOOD_ARRAY_SCHEMA = new Schema.Parser().parse(Foods.class.getClassLoader().getResourceAsStream("schemas/FoodArray.avsc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Food> getContent() {
        return content;
    }

    public Foods(List<Food> content) {
        this.content = content;
    }

    public Foods(Food... foods) {
        this.content = new ArrayList<>(foods.length);
        this.content.addAll(Arrays.asList(foods));
    }

    public Foods(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord record) {

        GenericArray<GenericRecord> foodArray = (GenericArray<GenericRecord>) record.get("FoodArray");

        this.content = new ArrayList<>(foodArray.size());

        for (GenericRecord foodRecord : foodArray) {
            content.add(new Food(foodRecord));
        }

    }

    @Override
    public GenericRecord serialize() {

        GenericRecord record = new GenericData.Record(getSchema());

        GenericArray<GenericRecord> foodArray = new GenericData.Array<>(content.size(), FOOD_ARRAY_SCHEMA);

        for (Food food : content) {
            foodArray.add(food.serialize());
        }

        //put food array
        record.put("FoodArray", foodArray);

        return record;
    }

    @Override
    protected void registerSchema() {

        registerSchema(this.getClass(), "schemas/FoodsRecord.avsc");

    }

    private void writeObject(ObjectOutputStream out) throws IOException {


        out.writeObject(content);

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        this.content = (List<Food>) in.readObject();


    }


}
