package social_ui.main_model;

import social_ui.main_controller.ControllerFactory;
import social_ui.main_view.ViewFactory;

public class BasicMainMvc {
	
	public static void startBasicMainMvc() {
		ModelFactory.setMainModel(new BasicMainModel());
		ModelFactory.getSingleton().
							addPropertyChangeListener(ViewFactory.getSingleton());
		ControllerFactory.getSingleton().processInput();
	}

}
