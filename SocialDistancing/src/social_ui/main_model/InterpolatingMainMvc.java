package social_ui.main_model;

import social_ui.main_controller.ControllerFactory;
import social_ui.main_view.ViewFactory;

public class InterpolatingMainMvc {
	
	public static void startInterpolatingMainMvc() {
		ModelFactory.setMainModel(new InterpolatingMainModel());
		ModelFactory.getSingleton().
							addPropertyChangeListener(ViewFactory.getSingleton());
		ControllerFactory.getSingleton().processInput();
	}

}
