package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValUtil {

	/**
	 * (yyyy-MM-dd)형식의 문자열을 java.sql.Date로 변환
	 * @param sDate (yyyy-MM-dd)
	 * @return
	 */
	public static java.sql.Date convertToSqlDate(String sDate) {
		java.sql.Date date = null;

		if (sDate != null && sDate.isBlank() == false) {
			try {
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date uDate = fmt.parse(sDate);
					date = new java.sql.Date(uDate.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return date;
	}
	
	/**
	 * 문자열을 int로 변환
	 * @param sInt
	 * @param defaultValue 변환에 실패했을떄 반환되는 기본값
	 * @return
	 */
	public static int convertToInt(String sInt, int defaultValue) {
		int result = defaultValue;
		if (sInt != null) {
			try {
				result = Integer.parseInt(sInt);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}
	
	/**
	 * 문자열을 int로 변환
	 * @param sInt
	 * @return 변환에 실패할 경우에는 -1을 반환
	 */
	public static int convertToInt(String sInt) {
		return convertToInt(sInt, -1);
	}
	
}
