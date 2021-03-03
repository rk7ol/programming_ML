package send.sessions;

import modules.Foods;

import java.io.*;

public class FoodsSession extends Session<Foods> {
    @Override
    byte[] serialize(Foods value) {


        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);

            objectOutputStream.writeObject(value);

            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    Foods deserialize(byte[] bytes) {


        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(in);

            return (Foods) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }
}
