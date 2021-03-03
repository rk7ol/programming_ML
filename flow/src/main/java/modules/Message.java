package modules;

import modules.request.*;
import modules.response.*;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import utils.avro.AvroUnit;

import java.util.HashMap;

public abstract class Message extends AvroUnit {

    protected GenericData.EnumSymbol symbol;

    protected String session;

    public String getSession() {
        return session;
    }

    protected Message(String session, String symbol) {
        this.symbol = createSymbol(symbol);

        this.session = session;


    }




    protected Message(GenericRecord record) {
        super(record);

    }

    protected abstract GenericData.EnumSymbol createSymbol(String symbol);


    public static String getTopicByClass(Class<? extends Message> clazz) {
        return clazz.getName();
    }


    public String getTopic() {
        return this.getClass().getName();
    }


    private interface MessageParserUnit<T extends Message> {
        T parse(GenericRecord record);
    }

    private static final HashMap<String, MessageParserUnit<? extends Message>> TYPE_CLASS_TABLE;

    static {
        TYPE_CLASS_TABLE = new HashMap<>();



        addMessageType("ADD_FOODS_REQUEST", (MessageParserUnit<AddFoodsRequest>) AddFoodsRequest::new);
        addMessageType("REGISTER_FOOD_REQUEST", (MessageParserUnit<RegisterFoodRequest>) RegisterFoodRequest::new);
        addMessageType("REGISTER_WINDOW_REQUEST", (MessageParserUnit<RegisterWindowRequest>) RegisterWindowRequest::new);
        addMessageType("SELL_FOOD_REQUEST", (MessageParserUnit<SellFoodRequest>) SellFoodRequest::new);
        addMessageType("SETTLE_REQUEST", (MessageParserUnit<SettleRequest>) SettleRequest::new);
        addMessageType("SHOW_ALL_FOODS_REQUEST", (MessageParserUnit<ShowAllFoodsRequest>) ShowAllFoodsRequest::new);
        addMessageType("SHOW_WINDOW_FOODS_REQUEST", (MessageParserUnit<ShowWindowFoodsRequest>) ShowWindowFoodsRequest::new);



        addMessageType("ADD_FOODS_RESPONSE", (MessageParserUnit<AddFoodsResponse>) AddFoodsResponse::new);
        addMessageType("REGISTER_FOOD_RESPONSE", (MessageParserUnit<RegisterFoodResponse>) RegisterFoodResponse::new);
        addMessageType("REGISTER_WINDOW_RESPONSE", (MessageParserUnit<RegisterWindowResponse>) RegisterWindowResponse::new);
        addMessageType("SELL_FOOD_RESPONSE", (MessageParserUnit<SellFoodResponse>) SellFoodResponse::new);
        addMessageType("SETTLE_RESPONSE", (MessageParserUnit<SettleResponse>) SettleResponse::new);
        addMessageType("SHOW_ALL_FOODS_RESPONSE", (MessageParserUnit<ShowAllFoodsResponse>) ShowAllFoodsResponse::new);
        addMessageType("SHOW_WINDOW_FOODS_RESPONSE", (MessageParserUnit<ShowWindowFoodsResponse>) ShowWindowFoodsResponse::new);


    }

    private static void addMessageType(String type, MessageParserUnit<? extends Message> unit) {
        TYPE_CLASS_TABLE.put(type, unit);
    }

    private static MessageParserUnit<? extends Message> getMessageType(String type) {
        return TYPE_CLASS_TABLE.get(type);
    }


    /**
     *  parse record to message class
     * @param record
     * @return
     */
    public static Message parseFromRecord(GenericRecord record) {

        MessageParserUnit<? extends Message> unit = getMessageType(record.get("type").toString());


        if (unit == null) {
            System.out.println("unknown message type");
            return null;
        } else {
            return unit.parse(record);
        }
    }


}
