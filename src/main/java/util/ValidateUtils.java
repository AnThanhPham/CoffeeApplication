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
	
	public static boolean checkPhoneNumber(String Phone) {
	    String regex = "^((0|\\+84)([3|5|7|8|9]{1})([0-9]{8})|([0-9]{3}-[0-9]{4}-[0-9]{3}))$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(Phone);
	    return matcher.matches();
	}
	
	public static boolean checkEmptyAndNull(String text) {
		if(text == null || text.trim().equals("")) {
			return true;
		}
		return false;
	}
}
