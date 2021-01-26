package modules;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericArray;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import utils.avro.AvroUnit;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Foods extends AvroUnit {
    //food list schema
    public static Schema FOODS_SCHEMA;

    //food array schema
    private static Schema FOOD_ARRAY_SCHEMA;

    static {
        try {
            //load schema
            FOODS_SCHEMA = new Schema.Parser().parse(Foods.class.getClassLoader().getResourceAsStream("schemas/Foods.avsc"));
            FOOD_ARRAY_SCHEMA = new Schema.Parser().parse(Foods.class.getClassLoader().getResourceAsStream("schemas/FoodArray.avsc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Food> foods;

    public Foods(List<Food> foods) {
        this.foods = foods;
    }

    public Foods(Food... foods) {
        this.foods = new LinkedList<>();

        this.foods.addAll(Arrays.asList(foods));

    }

    public List<Food> getFoods() {
        return foods;
    }

    public Foods(GenericRecord record) {
        super(record);
    }

    @Override
    public void deserialize(GenericRecord arrayRecord) {
        GenericArray<GenericRecord> array = (GenericArray<GenericRecord>) arrayRecord.get("foods");

        for (GenericRecord record : array) {

            if (foods == null) {
                foods = new LinkedList<>();
                foods.add(new Food(record));
            }

        }

    }

    @Override
    public GenericRecord serialize() {
        GenericRecord foodsRecord = new GenericData.Record(FOODS_SCHEMA);

        GenericArray<GenericRecord> array = new GenericData.Array<>(1, FOOD_ARRAY_SCHEMA);

        for (Food food : foods) {
            array.add(food.serialize());
        }

        foodsRecord.put("foods", array);

        return foodsRecord;
    }


}
