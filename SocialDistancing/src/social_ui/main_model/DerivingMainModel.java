package social_ui.main_model;

import social.SocialDistancing;

public class DerivingMainModel extends AModel implements IModel {
	
	public DerivingMainModel() {
		super();
	}
	
	@Override
	public boolean isSafe() {
		return SocialDistancing.isDerivedSafe(distance, duration, exhalation);
	}

}
