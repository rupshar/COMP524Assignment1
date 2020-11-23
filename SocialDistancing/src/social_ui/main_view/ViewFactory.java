package social_ui.main_view;

public class ViewFactory {
	
	static SocialMainView singleton;

	public static SocialMainView getSingleton() {
		if(singleton == null) {
			singleton = new View();
		}
		return singleton;
	}
	
	public static void setMainView(final SocialMainView newVal) {
		singleton = newVal;
	}

}
