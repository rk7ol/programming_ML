package modules.request;

import modules.Food;
import modules.Foods;
import modules.Request;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.util.List;

/**
 * sell foods
 * <p>
 * List<Food> foods
 * String ID
 */
public class SellFoodRequest extends Request {
    private Foods foods;


    private String ID;

    public List<Food> getFoods() {
        return foods.getContent();
    }

    public String getID() {
        return ID;
    }

    public SellFoodRequest(String ID, Food... foods) {
        super("SELL_FOOD_REQUEST");
        this.foods = new Foods(foods);
        this.ID = ID;
    }

    public SellFoodRequest(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord record) {

        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.foods = new Foods((GenericRecord) record.get("foods"));

        this.ID = record.get("ID").toString();

    }

    @Override
    public GenericRecord serialize() {

        GenericRecord record = new GenericData.Record(getSchema());

        record.put("type", symbol);

        record.put("foods", foods.serialize());

        record.put("ID", ID);


        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/request/SellFoodRequest.avsc");
    }
}
