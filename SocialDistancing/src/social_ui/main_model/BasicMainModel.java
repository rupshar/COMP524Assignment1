package social_ui.main_model;

import social.SocialDistancing;

public class BasicMainModel extends AModel implements IModel {
	
	public BasicMainModel() {
		super();
	}


	@Override
	public boolean isSafe() {
		return SocialDistancing.isGivenSafe(distance, duration, exhalation);
	}

}
