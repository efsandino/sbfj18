package bo.edusoft.sbf18.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerDateAndTimeDeserialize extends JsonDeserializer<Date> {
    public static final String FORMAT_DATE_TIME = "dd-MM-yyyy HH:mm:ss";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            FORMAT_DATE_TIME);

    @Override
    public Date deserialize(
            JsonParser paramJsonParser
            , DeserializationContext paramDeserializationContext
    ) throws IOException {
        String str = paramJsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            // Handle exception here
        }
        return paramDeserializationContext.parseDate(str);
    }
}
