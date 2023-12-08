package bo.edusoft.sbf18.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerDateAndTimeSerialize extends JsonSerializer<Date> {
    public static final String FORMAT_DATE_TIME="dd-MM-yyyy HH:mm:ss";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            FORMAT_DATE_TIME);

    @Override
    public void serialize(final Date value, final JsonGenerator gen, final SerializerProvider serializers)
            throws IOException {
        if (value != null) {
            try {
                gen.writeString(dateFormat.format(value));
            } catch (Exception e) {

                serializers.defaultSerializeDateKey(value, gen);
            }
        }
    }
}