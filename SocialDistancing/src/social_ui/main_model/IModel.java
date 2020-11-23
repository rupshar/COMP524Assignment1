package social_ui.main_model;

import java.beans.PropertyChangeListener;

public interface IModel {
	
	public int getDistance();
	public int getDuration();
	public int getExhalation();
	
	/**
	 * Given the distance, duration, and exhalation, determines safety of given arrangement
	 * @return true if safe, false if not safe or not enough information
	 */
	public boolean isSafe();
	
	public void resetProperties();

	public static final String DISTANCE = "Distance";
	public static final String DURATION = "Duration";
	public static final String EXHALATION = "ExhalationLevel";
	public static final String SAFE = "Safe";
	
	public void setSafety(Integer distance, Integer duration, Integer exhalation);
	
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
}
