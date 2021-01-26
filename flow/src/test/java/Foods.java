import org.apache.avro.Schema;

import java.io.IOException;

public class Foods {

    //food list schema
    public static Schema FOODS_SCHEMA;

    //food array schema
    public static Schema FOOD_ARRAY_SCHEMA;

    public static String FOODS_TOPIC = "Foods";


    static {
        try {
            FOODS_SCHEMA = new Schema.Parser().parse(AvroTest.class.getClassLoader().getResourceAsStream("schemas/Foods.avsc"));
            FOOD_ARRAY_SCHEMA = new Schema.Parser().parse(AvroTest.class.getClassLoader().getResourceAsStream("schemas/FoodArray.avsc"));

        } catch ( IOException e) {
            e.printStackTrace();
        }

    }
}
