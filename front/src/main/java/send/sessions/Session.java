package send.sessions;

import java.io.Serializable;
import java.util.UUID;

public abstract class Session<T extends Serializable> {
    String ID;

    T value;


    abstract byte[] serialize(T value);

    abstract T deserialize(byte[] bytes);


    private static String getRandomID() {


        return UUID.randomUUID().toString();

    }

    public Session(String ID, T value) {
        this.ID = ID;
        this.value = value;

    }

    public Session(String ID) {
        this.ID = ID;
    }

    public Session() {
        this.ID = getRandomID();
    }

    public String getID() {
        return ID;
    }

    public T getValue() {
        return value;
    }

    public byte[] getValueBytes(){
        return serialize(value);
    }

    public void setValue(byte[] bytes) {

        this.value = deserialize(bytes);
    }


}
