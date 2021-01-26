package utils.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class AvroSerializer implements Serializer<GenericRecord> {


    //serialize tools
    private final DatumWriter<GenericRecord> writer;
    private final DataFileWriter<GenericRecord> genericDataWriter;
    private final ByteArrayOutputStream byteArrayOutput;

    public AvroSerializer() {

        //serialize tools init
        writer = new GenericDatumWriter<>();
        genericDataWriter = new DataFileWriter<>(writer);
        byteArrayOutput = new ByteArrayOutputStream();


    }


    public byte[] serialize(List<GenericRecord> dataList, Schema schema) {
        writer.setSchema(schema);

        try {
            byteArrayOutput.reset();
            genericDataWriter.create(schema, byteArrayOutput);

            //converse to byte
            for (GenericRecord record : dataList) {
                genericDataWriter.append(record);
            }
            genericDataWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutput.toByteArray();

    }


    public void close() {

        try {
            genericDataWriter.close();
            byteArrayOutput.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}



