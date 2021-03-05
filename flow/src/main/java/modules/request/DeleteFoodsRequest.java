package modules.request;

import modules.Food;
import modules.Foods;
import modules.Request;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.util.List;

public class DeleteFoodsRequest extends Request {

    private Foods foods;

    public List<Food> getFoods() {
        return foods.getContent();
    }

    public DeleteFoodsRequest(String session, Food... foods) {
        super(session, "DELETE_FOODS_REQUEST");
        this.foods = new Foods(foods);
    }

    public DeleteFoodsRequest(GenericRecord record) {
        super(record);
    }


    @Override
    public void deserialize(GenericRecord record) {
        this.session = record.get("session").toString();

        this.symbol = (GenericData.EnumSymbol) record.get("type");

        this.foods = new Foods((GenericRecord) record.get("foods"));


    }

    @Override
    public GenericRecord serialize() {

        GenericRecord record = new GenericData.Record(getSchema());

        record.put("session", session);

        record.put("type", symbol);

        record.put("foods", foods.serialize());



        return record;
    }

    @Override
    protected void registerSchema() {
        registerSchema(this.getClass(), "schemas/request/DeleteFoodsRequest.avsc");
    }

}
