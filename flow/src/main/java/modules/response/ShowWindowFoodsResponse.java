package modules.response;

import modules.Food;
import modules.Foods;
import modules.Response;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.util.List;


/**
 *  show foods of window
 *
 * List<Food> foods
 */
public class ShowWindowFoodsResponse extends Response {

    private Foods foods;


    public List<Food> getFoods() {
        return foods.getContent();
    }


    public ShowWindowFoodsResponse(Food... foods) {
        super("SHOW_WINDOW_FOODS_RESPONSE");
        this.foods = new Foods(foods);

    }


    public ShowWindowFoodsResponse(GenericRecord record) {
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

        registerSchema(this.getClass(), "schemas/response/ShowWindowFoodsResponse.avsc");

    }

}
