package social_ui.main_model;

import social_ui.main_controller.ControllerFactory;
import social_ui.main_view.ViewFactory;

public class DerivingMainMvc {
	
	public static void startDerivingMainMvc() {
		ModelFactory.setMainModel(new DerivingMainModel());
		ModelFactory.getSingleton().
							addPropertyChangeListener(ViewFactory.getSingleton());
		ControllerFactory.getSingleton().processInput();
	}

}
