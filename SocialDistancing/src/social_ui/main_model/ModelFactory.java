package social_ui.main_model;

public class ModelFactory {
	
	static IModel singleton;

	public static IModel getSingleton() {
		if(singleton == null) {
			singleton = new BasicMainModel();
		}
		return singleton;
	}
	
	public static void setMainModel(final IModel newVal) {
		singleton = newVal;
	}

}
