package social;

import social_ui.main_controller.Controller;
import social_ui.main_controller.ControllerFactory;
import social_ui.main_model.BasicMainModel;
import social_ui.main_model.ClassifierModel;
import social_ui.main_model.InterpolatingMainModel;
import social_ui.main_model.DerivingMainModel;
import social_ui.main_model.ModelFactory;
import social_ui.main_view.View;
import social_ui.main_view.ViewFactory;
import social_ui.mvc.TestClassifierMainMvc;
import social_ui.mvc.TestInterpolatingMainMvc;
import social_ui.mvc.TestBasicMainMvc;
import social_ui.mvc.TestDerivingMainMvc;
import social_ui.weka.WekaClassifierFactory;

public class ClassRegistry implements gradingTools.comp524f20.assignment1.SocialDistanceClassRegistry{

//	public static void main(String[] args) {
//		
//	}

	@Override
	public Class<?> getBasicSocialDistanceUtility() {
		// TODO Auto-generated method stub
		return SocialDistancing.class;
	}

	@Override
	public Class<?> getSocialDistancDerivingModel() {
		// TODO Auto-generated method stub
		return DerivingMainModel.class;
	}

	@Override
	public Class<?> getSocialDistancInferringModel() {
		// TODO Auto-generated method stub
		return ClassifierModel.class;
	}

	@Override
	public Class<?> getSocialDistanceBasicModel() {
		// TODO Auto-generated method stub
		return BasicMainModel.class;
	}

	@Override
	public Class<?> getSocialDistanceClassifierFactory() {
		// TODO Auto-generated method stub
		return WekaClassifierFactory.class;
	}

	@Override
	public Class<?> getSocialDistanceController() {
		// TODO Auto-generated method stub
		return Controller.class;
	}

	@Override
	public Class<?> getSocialDistanceControllerFactory() {
		// TODO Auto-generated method stub
		return ControllerFactory.class;
	}

	@Override
	public Class<?> getSocialDistanceInterpolatingModel() {
		// TODO Auto-generated method stub
		return InterpolatingMainModel.class;
	}

	@Override
	public Class<?> getSocialDistanceMVCBasicMain() {
		// TODO Auto-generated method stub
		return TestBasicMainMvc.class;
	}

	@Override
	public Class<?> getSocialDistanceMVCDerivingMain() {
		// TODO Auto-generated method stub
		return TestDerivingMainMvc.class;
	}

	@Override
	public Class<?> getSocialDistanceMVCInferringMain() {
		// TODO Auto-generated method stub
		return TestClassifierMainMvc.class;
	}

	@Override
	public Class<?> getSocialDistanceMVCInterpolatingMain() {
		// TODO Auto-generated method stub
		return TestInterpolatingMainMvc.class;
	}

	@Override
	public Class<?> getSocialDistanceModelFactory() {
		// TODO Auto-generated method stub
		return ModelFactory.class;
	}

	@Override
	public Class<?> getSocialDistanceUilityTesterMain() {
		// TODO Auto-generated method stub
		return SocialDistancingTester.class;
	}

	@Override
	public Class<?> getSocialDistanceView() {
		// TODO Auto-generated method stub
		return View.class;
	}

	@Override
	public Class<?> getSocialDistanceViewFactory() {
		// TODO Auto-generated method stub
		return ViewFactory.class;
	}

}

