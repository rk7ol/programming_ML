package send.sessions;

public class BooleanSession extends Session<Boolean> {
    @Override
    byte[] serialize(Boolean value) {
        return new byte[]{(byte) (value ? 1 : 0)};
    }

    @Override
    Boolean deserialize(byte[] bytes) {
        if (bytes != null){
            return bytes[0] == 1;
        }else {
            return null;
        }

    }
}
