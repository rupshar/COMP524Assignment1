package social_ui.main_model;

import social.SocialDistancing;

public class InterpolatingMainModel extends AModel implements IModel {
	
	public InterpolatingMainModel() {
		super();
	}

	@Override
	public boolean isSafe() {
		return SocialDistancing.isInterpolatedSafe(distance, duration, exhalation);
	}

}
