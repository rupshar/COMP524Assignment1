package social_ui.main_view;

import java.beans.PropertyChangeEvent;

import social_ui.main_model.IModel;

public class View implements SocialMainView {

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if(IModel.DISTANCE == evt.getPropertyName()) {
			System.out.println(evt.toString());
		} else if(IModel.DURATION == evt.getPropertyName()) {
			System.out.println(evt.toString());
		} else if(IModel.EXHALATION == evt.getPropertyName()) {
			System.out.println(evt.toString());
		} else if(IModel.SAFE == evt.getPropertyName()) {
			final boolean safe = (Boolean) evt.getNewValue();
			System.out.println(evt.toString());
			checkSafety(safe);
		}
	}
	
	public void checkSafety(final boolean isSafe) {
		if(isSafe) {
			System.out.println("Safe.");
		} else {
			System.out.println("Not safe!");
		}
	}

}
