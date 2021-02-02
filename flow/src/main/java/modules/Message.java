package modules;

import modules.request.FoodsRequest;
import modules.request.RegisterFoodRequest;
import modules.response.FoodsResponse;
import modules.response.RegisterFoodResponse;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import utils.avro.AvroUnit;

public abstract class Message extends AvroUnit {

    protected GenericData.EnumSymbol symbol;



    protected Message(String symbol){
        this.symbol = createSymbol(symbol);


    }


    protected Message(GenericRecord record){
        super(record);

    }

    protected abstract GenericData.EnumSymbol createSymbol(String symbol);




    public static String getTopicByClass(Class<? extends Message> clazz){
        return clazz.getName();
    }


    public String getTopic(){
        return this.getClass().getName();
    }


    public static Message parseFromRecord(GenericRecord record){

        switch (record.get("type").toString()){
            case "FOODSREQUEST":{
                return new FoodsRequest(record);
            }

            case "FOODSRESPONSE":{
                return new FoodsResponse(record);
            }

            case "REGISTERFOODREQUEST":{
                return new RegisterFoodRequest(record);
            }

            case "REGISTERFOODRESPONSE":{
                return new RegisterFoodResponse(record);
            }

            default:
                return null;
        }
    }




}
