package social_ui.main_model;

import social_ui.main_controller.ControllerFactory;
import social_ui.main_view.ViewFactory;

public class ClassifierMainMvc {
	
	public static void startClassifierMainMvc() {
		ModelFactory.setMainModel(new ClassifierModel());
		ModelFactory.getSingleton().
							addPropertyChangeListener(ViewFactory.getSingleton());
		ControllerFactory.getSingleton().processInput();
	}

}
