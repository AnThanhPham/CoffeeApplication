package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
	ValidateUtils(){
		
	}
	
	public static boolean checkEmail(String email) {
		String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean checkEmptyAndNull(String text) {
		if(text.trim().equals("") || text.trim() == null) {
			return true;
		}
		return false;
	}
}
