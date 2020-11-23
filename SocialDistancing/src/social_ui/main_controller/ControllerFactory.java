package social_ui.main_controller;

public class ControllerFactory {
	
	static IController singleton;

	public static IController getSingleton() {
		if(singleton == null) {
			singleton = new Controller();
		}
		return singleton;
	}
	
	public static void setMainFactory(final IController newVal) {
		singleton = newVal;
	}

}
