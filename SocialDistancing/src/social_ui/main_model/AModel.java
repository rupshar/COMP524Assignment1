package social_ui.main_model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AModel implements IModel {

	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	protected Integer distance;
	protected Integer duration;
	protected Integer exhalation;
	protected Boolean isSafe;
	
	
	protected AModel() {
		// do nothing
	}
	
	@Override
	public void setSafety(final Integer newDistance, final Integer newDuration, final Integer newExhalation) {
		final Integer old_distance = this.distance;
		final Integer old_duration = this.duration;
		final Integer old_exhalation = this.exhalation;
		final Boolean old_safe = this.isSafe;
		
		this.distance = newDistance;
		this.duration = newDuration;
		this.exhalation = newExhalation;
		final Boolean safe = isSafe();
		
		propertyChangeSupport.firePropertyChange(DISTANCE, old_distance, newDistance);
		propertyChangeSupport.firePropertyChange(DURATION, old_duration, newDuration);
		propertyChangeSupport.firePropertyChange(EXHALATION, old_exhalation, newExhalation);
		propertyChangeSupport.firePropertyChange(SAFE, old_safe, safe);
		
		resetProperties();
	}

	@Override
	public int getDistance() {
		return distance;
	}

	@Override
	public int getDuration() {
		return duration;
	}

	@Override
	public int getExhalation() {
		return exhalation;
	}
	
	@Override
	public void resetProperties() {
		this.distance = null;
		this.duration = null;
		this.exhalation = null;
		this.isSafe = null;
	}

	@Override
	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);	
	}

	@Override
	public abstract boolean isSafe();


}
