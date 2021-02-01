package modules.response;

import modules.Food;
import modules.Response;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericArray;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FoodsResponse extends Response {
    //food array schema
    private static Schema FOOD_ARRAY_SCHEMA;

    static {
        try {
            //load schema
            FOOD_ARRAY_SCHEMA = new Schema.Parser().parse(FoodsResponse.class.getClassLoader().getResourceAsStream("schemas/FoodArray.avsc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Food> foods;

    public FoodsResponse(List<Food> foods) {
        super("FOODSRESPONSE");
        this.foods = foods;
    }

    public FoodsResponse(Food... foods) {
        super("FOODSRESPONSE");
        this.foods = new LinkedList<>();

        this.foods.addAll(Arrays.asList(foods));

    }

    public List<Food> getFoods() {
        return foods;
    }

    @Override
    protected void registerSchema() {

        registerSchema(this.getClass(), "schemas/response/FoodsResponse.avsc");

    }

    public FoodsResponse(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord arrayRecord) {


        GenericArray<GenericRecord> array = (GenericArray<GenericRecord>) arrayRecord.get("foods");

        //get enum
        this.symbol = (GenericData.EnumSymbol) arrayRecord.get("type");

        for (GenericRecord record : array) {

            if (foods == null) {
                foods = new LinkedList<>();
            }
            foods.add(new Food(record));

        }

    }



    @Override
    public GenericRecord serialize() {
        GenericRecord foodsRecord = new GenericData.Record(getSchema());

        //put enum
        foodsRecord.put("type", this.symbol);

        GenericArray<GenericRecord> array = new GenericData.Array<>(foods.size(), FOOD_ARRAY_SCHEMA);

        for (Food food : foods) {
            array.add(food.serialize());
        }

        //put food array
        foodsRecord.put("foods", array);



        return foodsRecord;
    }


}
