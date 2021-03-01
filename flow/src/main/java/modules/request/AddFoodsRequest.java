package modules.request;

import modules.Food;
import modules.Foods;
import modules.Request;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.util.List;

/**
 * add new food to window
 * <p>
 * List<Food> foods
 * String ID
 */
public class AddFoodsRequest extends Request {

    private Foods foods;


    private String ID;

    public List<Food> getFoods() {
        return foods.getContent();
    }

    public String getID() {
        return ID;
    }

    public AddFoodsRequest(String ID, Food... foods) {
        super("ADD_FOODS_REQUEST");
        this.ID = ID;
        this.foods = new Foods(foods);
    }

    public AddFoodsRequest(GenericRecord record) {
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
        registerSchema(this.getClass(), "schemas/request/AddFoodsRequest.avsc");
    }
}
