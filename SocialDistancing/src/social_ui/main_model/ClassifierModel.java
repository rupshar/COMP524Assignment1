package social_ui.main_model;

import social.SocialDistancing;

public class ClassifierModel extends AModel implements IModel {

	public ClassifierModel() {
		super();
	}
	
	@Override
	public boolean isSafe() {
		return SocialDistancing.isInferredSafe(distance, duration, exhalation);
	}

}
