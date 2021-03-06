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


    public ShowAllFoodsResponse(String session, Food... foods) {
        super(session, "SHOW_ALL_FOODS_RESPONSE");
        this.foods = new Foods(foods);

    }


    public ShowAllFoodsResponse(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord record) {

        this.session = record.get("session").toString();
        //get enum
        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.foods = new Foods((GenericRecord) record.get("foods"));

    }


    @Override
    public GenericRecord serialize() {
        GenericRecord record = new GenericData.Record(getSchema());
        record.put("session", session);
        //put enum
        record.put("type", this.symbol);

        record.put("foods", foods.serialize());


        return record;
    }

    @Override
    protected void registerSchema() {

        registerSchema(this.getClass(), "schemas/response/ShowAllFoodsResponse.avsc");

    }

}
