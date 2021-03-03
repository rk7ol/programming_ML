package send.sessions;

import modules.Foods;

import java.io.*;

public class DoubleSession extends Session<Double>{
    @Override
    byte[] serialize(Double value) {

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
    Double deserialize(byte[] bytes) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(in);

            return (Double) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }
}
