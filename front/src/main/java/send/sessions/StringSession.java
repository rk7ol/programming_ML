package send.sessions;

import java.nio.charset.StandardCharsets;

public class StringSession extends Session<String>{

    @Override
    byte[] serialize(String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    String deserialize(byte[] bytes) {
        return new String(bytes);
    }
}
