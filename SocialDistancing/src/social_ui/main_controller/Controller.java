package social_ui.main_controller;

import java.util.Scanner;
import social_ui.main_model.ModelFactory;

public class Controller implements IController {
	
	private final String quitStatement = "Quitting";
	
	@Override
	public void processInput() {
		final Scanner s = new Scanner(System.in);
		
		int distance = 0;
		int duration = 0;
		int exhalation = 0;
		
		while (!(distance < 0) && !(duration < 0) && !(exhalation < 0)) {
			System.out.println("Please enter data regarding your guest interaction.");
			System.out.println("Distance?");
			distance = s.nextInt();
			if(distance < 0) {
				System.out.println(quitStatement);
				break;
			}
			System.out.println("Duration?");
			duration = s.nextInt();
			if(duration < 0) {
				System.out.println(quitStatement);
				break;
			}
			System.out.println("Exhalation Level?");
			exhalation = s.nextInt();
			if(exhalation < 0) {
				System.out.println(quitStatement);
				break;
			}
			
			ModelFactory.getSingleton().setSafety(distance, duration, exhalation);
		
		}
		
		s.close();
		
	}

}
