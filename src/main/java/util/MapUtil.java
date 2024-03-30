package util;

public class MapUtil {
	public static String convertObjectToString(Object value) {
		if(value != null && !value.equals("") && !value.equals("null")) {
			return value.toString();
		}else {
			return null;
		}
	}
}
