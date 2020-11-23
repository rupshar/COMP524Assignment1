package social;

public class SocialDistancingTester {
	
	public static void main(final String[] args) {
		SocialDistancing.printGivenAndGeneratedCombinationsDerivedSafety();
		SocialDistancing.printGivenAndGeneratedCombinationsInferredSafety();
		SocialDistancing.compareSafetyComputations();
		SocialDistancing.printSafeDistancesAndDurations(SocialDistancing.MEDIUM_EXHALATION_LEVEL);
		SocialDistancing.printSafeDistancesAndDurations(SocialDistancing.MEDIUM_EXHALATION_LEVEL - 1);
		SocialDistancing.printSafeDistancesAndDurations(SocialDistancing.MEDIUM_EXHALATION_LEVEL + 2);
	}

}
