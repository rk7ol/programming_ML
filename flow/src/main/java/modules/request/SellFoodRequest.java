package modules.request;

import modules.Food;
import modules.Foods;
import modules.Request;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.util.List;

/**
 *  sell foods
 *
 *  List<Food> foods
 */
public class SellFoodRequest extends Request {
    private Foods foods;

    public List<Food> getFoods() {
        return foods.getContent();
    }

    public SellFoodRequest(Food... foods) {
        super("SELL_FOOD_REQUEST");
        this.foods = new Foods(foods);
    }

    public SellFoodRequest(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord record) {

        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.foods = new Foods((GenericRecord) record.get("foods"));

    }

    @Override
    public GenericRecord serialize() {

        GenericRecord record = new GenericData.Record(getSchema());

        record.put("type", symbol);

        record.put("foods", foods.serialize());



        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/request/SellFoodRequest.avsc");
    }
}
