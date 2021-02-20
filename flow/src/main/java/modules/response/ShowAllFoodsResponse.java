package modules.response;

import modules.Food;
import modules.Foods;
import modules.Response;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.util.List;

/**
 *  show all foods registered in server
 *
 *  List<Food> foods
 */
public class ShowAllFoodsResponse extends Response {

    private Foods foods;


    public List<Food> getFoods() {
        return foods.getContent();
    }


    public ShowAllFoodsResponse(Food... foods) {
        super("SHOW_ALL_FOODS_RESPONSE");
        this.foods = new Foods(foods);

    }


    public ShowAllFoodsResponse(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord record) {


        //get enum
        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.foods = new Foods((GenericRecord) record.get("foods"));

    }


    @Override
    public GenericRecord serialize() {
        GenericRecord foodsRecord = new GenericData.Record(getSchema());

        //put enum
        foodsRecord.put("type", this.symbol);

        foodsRecord.put("foods", foods.serialize());


        return foodsRecord;
    }

    @Override
    protected void registerSchema() {

        registerSchema(this.getClass(), "schemas/response/ShowAllFoodsResponse.avsc");

    }

}
