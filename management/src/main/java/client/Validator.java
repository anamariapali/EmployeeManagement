package client;

import java.util.regex.Pattern;

public class Validator {
	private static final String NAME_PATTERN="[a-zA-Z0-9._-]{3,}$";
	public static boolean validateName(String name) {
		Pattern pattern = Pattern.compile(NAME_PATTERN);
		if (!pattern.matcher(name).matches()) {
			throw new IllegalArgumentException("name not valid!");
			//return false;
		}
		return true;
	}
		/*private static final String City_PATTERN="^\\d+\\s[A-z]+\\s[A-z]+";
		
		public static boolean validateAddress(String address) {
			Pattern pattern = Pattern.compile(City_PATTERN);
			if (!pattern.matcher(address).matches()) {
				throw new IllegalArgumentException("address not valid!");
				//return false;
			}
			return true;
}*/
	private static final String USER_PATTERN="[a-zA-Z0-9._-]{3,}$";
		
		public static boolean validateUser(String user) {
			Pattern pattern = Pattern.compile(USER_PATTERN);
			if (!pattern.matcher(user).matches()) {
				throw new IllegalArgumentException("user not valid!");
				//return false;
			}
			return true;
		}
	private static final String PAS_PATTERN="[a-zA-Z0-9._-]{3,}$";
			
			public static boolean validatePas(String password) {
				Pattern pattern = Pattern.compile(PAS_PATTERN);
				if (!pattern.matcher(password).matches()) {
					throw new IllegalArgumentException("password not valid!");
					//return false;
				}
				return true;	
}
	private static final String TEL_PATTERN="\"\\\\d{10}|(?:\\\\d{3}-){2}\\\\d{4}|\\\\(\\\\d{3}\\\\)\\\\d{3}-?\\\\d{4}\";";
			
			public static boolean validateTel(String telephone) {
				Pattern pattern = Pattern.compile(TEL_PATTERN);
				if (!pattern.matcher(telephone).matches()) {
					throw new IllegalArgumentException("telephone not valid!");
					//return false;
				}
				return true;	
}
		
}
