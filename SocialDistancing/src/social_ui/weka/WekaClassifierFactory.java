package social_ui.weka;

import gradingTools.comp524f20.assignment1.WekaUtil;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;

public class WekaClassifierFactory {
	
	static Classifier singleton;
	
	public static Classifier getSingleton() {
		if(singleton == null) {
			singleton = new J48();
			WekaUtil.buildTreeModel(singleton, "SafeSocialization.txt");
		}
		return singleton;
	}
	
	public static void setNewValue(final Classifier newVal) {
		singleton = newVal;
	}
	
}
