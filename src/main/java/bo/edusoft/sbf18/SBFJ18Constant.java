package bo.edusoft.sbf18;

import java.text.SimpleDateFormat;

public interface SBFJ18Constant {
	final String REQUEST_DATE_FORMAT = "MM/dd/yyyy";
	final String RESPONSE_DATE_FORMAT = "MM/dd/yyyy";
	final SimpleDateFormat formatter = new SimpleDateFormat(REQUEST_DATE_FORMAT);
	final SimpleDateFormat displayFormatter = new SimpleDateFormat(RESPONSE_DATE_FORMAT);
}
