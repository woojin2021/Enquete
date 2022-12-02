package common;

public class Configure {
	
	public static final String SSM_AUTHOR = "authorization";
	public static final String SSM_ENQUETE_PATH = "enquete_URL";
	public static final String SSM_USERID = "UserID";
	public static final String SSM_USERNAME = "UserName";
	public static final String SSM_REQUEST_URI = "RequestURI";
	
	public static final String ERR_MESSAGE = "errorMassage";
	public static final String ERR_NEED_ADMINISTRATOR = "시스템 관리자 권한이 필요합니다.";

	public static final int LISTCOUNT = 10;
	public static final int PAGECOUNT = 5;
	
	public static final int AUTHOR_ADMIN = 0;
	public static final int AUTHOR_CREATER = 1;
	public static final int AUTHOR_USER = 2;

	public static final int ENQUETE_STATUS_READY = 0;
	public static final int ENQUETE_STATUS_OPEN = 1;
	public static final int ENQUETE_STATUS_CLOSED = 2;

	public enum Author {
		ADMIN(0),
		USER(1),
		Banana(2);
		
		private final int value;
		
		Author(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
}
