package social;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import gradingTools.comp524f20.assignment1.WekaUtil;
import weka.classifiers.Classifier;
import social_ui.weka.WekaClassifierFactory;

/**
 * SOCIAL DISTANCING UTILITY CLASS
 * @author rupinsharma
 */

public class SocialDistancing {
	
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	private static String resultAttributeName = "safe";
	private static String[] resultValueNames = {TRUE, FALSE};
	private static String[] featureNames = {"distance", "duration", "exhalation"};
	
	public static final int SMALL_DISTANCE = 6;
	public static final int MEDIUM_DISTANCE = 13;
	public static final int LARGE_DISTANCE = 27;
	
	public static final int SMALL_DURATION = 15;
	public static final int MEDIUM_DURATION = 30;
	public static final int LARGE_DURATION = 120;
	
	public static final int SMALL_EXHALATION_LEVEL = 10;
	public static final int MEDIUM_EXHALATION_LEVEL = 30;
	public static final int LARGE_EXHALATION_LEVEL = 50;
	
	private static final int[] DISTANCES = {SMALL_DISTANCE, MEDIUM_DISTANCE, LARGE_DISTANCE};
	private static final int[] DURATIONS = {SMALL_DURATION, MEDIUM_DURATION, LARGE_DURATION};
	private static final int[] EXHALATIONS = {SMALL_EXHALATION_LEVEL, MEDIUM_EXHALATION_LEVEL, LARGE_EXHALATION_LEVEL};
	
	public static final String LINE_BREAK = "----------------";
	public static final String COMMA = ",";
	public static final String GENERATE_START = "Distance,Duration,Exhalation,IsSafe";
	public static final String NEWLINE = "\n";
	
	public static final int[][] SAFE_COMBINATIONS = 
		{{MEDIUM_DISTANCE, MEDIUM_DURATION, MEDIUM_EXHALATION_LEVEL}, 
			{SMALL_DISTANCE, MEDIUM_DURATION, SMALL_EXHALATION_LEVEL},
			{LARGE_DISTANCE, MEDIUM_DURATION, LARGE_EXHALATION_LEVEL},
			{MEDIUM_DISTANCE, SMALL_DURATION, LARGE_EXHALATION_LEVEL},
			{MEDIUM_DISTANCE, LARGE_DURATION, SMALL_EXHALATION_LEVEL},
			{LARGE_DISTANCE, LARGE_DURATION, MEDIUM_EXHALATION_LEVEL},
			{SMALL_DISTANCE, SMALL_DURATION, MEDIUM_EXHALATION_LEVEL}};

	
	/**
	 * isGivenSafe: int*int*int => boolean
	 * If the combination of the method parameters is safe, based on the given data, the function returns
	 * true. Otherwise, it returns false.
	 */
	
	public static boolean isGivenSafe(final int distance, final int duration, final int exhalation) {
		final int[] given_combination = {distance, duration, exhalation};
		return Arrays.stream(SAFE_COMBINATIONS).anyMatch(comb -> Arrays.equals(comb, given_combination));
	}
	
	/**
	 * lowInterpolation: int*int[] => int
	 * Takes an input and a sorted sequence of values to interpolate the input to.
	 * The low interpolation to a sequence of values is either an element of the sequence or zero.
	 */
	public static int lowInterpolation(final int input, final int[] sequence) {
		int interpolatedValue = 0;
		if(input >= sequence[sequence.length - 1]) {
			interpolatedValue = sequence[sequence.length - 1];
			return interpolatedValue;
		} else if (input < sequence[0]) {
			interpolatedValue = 0;
			return interpolatedValue;
		}
		for(int i = 1; i < sequence.length; i++) {
			if((input >= sequence[i - 1]) && (input < sequence[i])) {
				interpolatedValue = sequence[i-1];
			}
		}
		return interpolatedValue;
	}
	
	/**
	 * highInterpolation: int*int[] => int
	 * Takes an input and a sorted sequence of values to interpolate the input to.
	 * The high interpolation to a sequence of values is either an element of the sequence or the max integer.
	 */
	public static int highInterpolation(final int input, final int[] sequence) {
		int interpolatedValue = 0;
		
		if(input > sequence[sequence.length - 1]) {
			interpolatedValue = Integer.MAX_VALUE;
			return interpolatedValue;
		} else if(input <= sequence[0]) {
			interpolatedValue = sequence[0];
			return interpolatedValue;
		}
		for(int i = 1; i < sequence.length; i++) {
			if((input > sequence[i - 1]) && (input <= sequence[i])) {
				interpolatedValue = sequence[i];
			}
		}
		return interpolatedValue;
	}
	
	/**
	 * randomizer: int => int
	 * Takes in an integer input as the max value, and returns a random number between 0 and
	 * that value.
	 */
	public static int randomizer(final int maxValue) {
		return (int) (Math.random() * (maxValue));
	}
	
	/**
	 * isInterpolatedSafe: int*int*int => boolean
	 * Takes the three values given and interpolates them to a value, and determines if the values are
	 * safe based off a table.
	 */
	public static boolean isInterpolatedSafe(final int distance, final int duration, final int exhalation) {

		int distanceInterpolate = 0;
		int durationInterpolate = 0;
		int exhalationInterpolate = 0;
		
		distanceInterpolate = lowInterpolation(distance, DISTANCES);
		durationInterpolate = highInterpolation(duration, DURATIONS);
		exhalationInterpolate = highInterpolation(exhalation, EXHALATIONS);
		return isGivenSafe(distanceInterpolate, durationInterpolate, exhalationInterpolate);
	}
	
	/**
	 * isInterpolatedSafe: int*int => boolean
	 * Fixes one of the three values (exhalation level), and interpolates the three values and determines if the values
	 * are safe based off a table.
	 */
	public static boolean isInterpolatedSafe(final int distance, final int duration) {
		final int exhalation = 30;
		return isInterpolatedSafe(distance, duration, exhalation);
	}
	
	/**
	 * isInterpolatedSafe: int*int => boolean
	 * Fixes two of the three values (duration and exhalation level), and interpolates the three values and determines if the values
	 * are safe based off a table
	 */
	public static boolean isInterpolatedSafe(final int distance) {
		final int duration = 30;
		final int exhalation = 30;
		return isInterpolatedSafe(distance, duration, exhalation);
	}
	
	/**
	 * printGeneratedCombination: => void
	 * Uses Math.random() function to generate a distance, duration, and exhalation level combination.
	 * The random integers are between zero and two times the large measurement for each metric.
	 * Interpolates the random values to determine if the combination is safe.
	 * Prints a 4-tuple showing the values and safety.
	 */
	public static void printGeneratedCombination() {
		
		final int randomDistance = randomizer(LARGE_DISTANCE * 3);
		final int randomDuration = randomizer(LARGE_DURATION * 3);
		final int randomExhalation = randomizer(LARGE_EXHALATION_LEVEL * 3);
		
		final boolean isSafe = isInterpolatedSafe(randomDistance, randomDuration, randomExhalation);
		
		System.out.println("(" + randomDistance + COMMA + randomDuration + COMMA + randomExhalation + COMMA + isSafe + ")");
		
	}
	
	/**
	 * printGivenAndGeneratedCombinations: => void
	 * For each 3-tuple combination from a given table, adds true to create 4-tuples, and prints the 
	 * 4-tuple.
	 * Prints a separator line.
	 * Calls printGeneratedCombination() ten times.
	 */
	public static void printGivenAndGeneratedCombinations() {
		System.out.println(GENERATE_START);
		
		for(int row = 0; row < SAFE_COMBINATIONS.length; row++) {
			for(int col = 0; col < SAFE_COMBINATIONS[0].length; col++) {
				System.out.print(SAFE_COMBINATIONS[row][col] + COMMA);
			}
			System.out.print(true + NEWLINE);
		}
		
		System.out.println(LINE_BREAK);
		
		for(int i = 0; i < 10; i++) {
			printGeneratedCombination();
		}
		
	}
	
	/**
	 * generateSafeDistancesAndDurations:int => List<Integer[]>
	 * Given an exhalation level, and computes a list of given safe distance and duration pairs assoicated
	 * with an interpolation of the exhalation level in a given table.
	 */
	public static List<Integer[]> generateSafeDistancesAndDurations(final int exhalation) {
		
		final Integer[] safe_small_exhalation_one = {SMALL_DISTANCE, MEDIUM_DURATION};
		final Integer[] safe_small_exhalation_two = {MEDIUM_DISTANCE, LARGE_DURATION};
		final Integer[] safe_medium_exhalation_one = {MEDIUM_DISTANCE, MEDIUM_DURATION};
		final Integer[] safe_medium_exhalation_two = {LARGE_DISTANCE, LARGE_DURATION};
		final Integer[] safe_medium_exhalation_three = {SMALL_DISTANCE, SMALL_DURATION};
		final Integer[] safe_large_exhalation_one = {LARGE_DISTANCE, MEDIUM_DURATION};
		final Integer[] safe_large_exhalation_two = {MEDIUM_DISTANCE, SMALL_DURATION};
		
		int exhalationInterpolate = 0;
		
		exhalationInterpolate = highInterpolation(exhalation, EXHALATIONS);
		
		final List<Integer[]> safe_distances_and_durations = new ArrayList<Integer[]>();
		
		if(exhalationInterpolate == SMALL_EXHALATION_LEVEL) {
			safe_distances_and_durations.add(safe_small_exhalation_one);
			safe_distances_and_durations.add(safe_small_exhalation_two);		
		} else if(exhalationInterpolate == MEDIUM_EXHALATION_LEVEL) {
			safe_distances_and_durations.add(safe_medium_exhalation_one);
			safe_distances_and_durations.add(safe_medium_exhalation_two);
			safe_distances_and_durations.add(safe_medium_exhalation_three);
		} else if(exhalationInterpolate == LARGE_EXHALATION_LEVEL) {
			safe_distances_and_durations.add(safe_large_exhalation_one);
			safe_distances_and_durations.add(safe_large_exhalation_two);
		}
		
		return safe_distances_and_durations;
	}
	
	/**
	 * printSafeDistancesAndDurations: int => void
	 * Takes in an exhalation level and uses generateSafeDistancesAndDurations() to determine
	 * the list of safe distances and durations for the passed exhalation-level and prints out:
	 * -the passed exhalation level
	 * -the list returned from generateSafeDistancesAndDurations()
	 */
	public static void printSafeDistancesAndDurations(final int exhalation) {
		
		final List<Integer[]> safe_distances_and_durations = generateSafeDistancesAndDurations(exhalation);
		
		System.out.print(exhalation + ",[");
		
		for(int listIter = 0; listIter < safe_distances_and_durations.size(); listIter++) {
			System.out.print("{");
			System.out.print(safe_distances_and_durations.get(listIter)[0] + COMMA);
			System.out.print(safe_distances_and_durations.get(listIter)[1] + "}");
		}
		System.out.print("]\n");
	}
	
	/**
	 * isDerivedSafe: int*int*int => boolean
	 * Takes in a distance, duration, and exhalation level, and returns true if the combination of
	 * the three parameters was safer than at least one of the combinations in a given table.
	 * A given combination will be safe if the distance is larger than the safe distance, 
	 * the duration is smaller than the safe duration, and the exhalation level is smaller than the
	 * safe exhalation level.
	 */
	public static boolean isDerivedSafe(final int distance, final int duration, final int exhalation) {
		if(distance >= MEDIUM_DISTANCE && duration <= MEDIUM_DURATION && exhalation <= MEDIUM_EXHALATION_LEVEL) {
			return true;
		} else if(distance >= SMALL_DISTANCE && duration <= MEDIUM_DURATION && exhalation <= SMALL_EXHALATION_LEVEL) {
			return true;
		} else if(distance >= LARGE_DISTANCE && duration <= MEDIUM_DURATION && exhalation <= LARGE_EXHALATION_LEVEL) {
			return true;
		} else if(distance >= MEDIUM_DISTANCE && duration <= SMALL_DURATION && exhalation <= LARGE_EXHALATION_LEVEL) {
			return true;
		} else if(distance >= MEDIUM_DISTANCE && duration <= LARGE_DURATION && exhalation <= SMALL_EXHALATION_LEVEL) {
			return true;
		} else if(distance >= LARGE_DISTANCE && duration <= LARGE_DURATION && exhalation <= MEDIUM_EXHALATION_LEVEL) {
			return true;
		} else if(distance >= SMALL_DISTANCE && duration <= SMALL_DURATION && exhalation <= MEDIUM_EXHALATION_LEVEL) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * printGeneratedCombinationDerivedSafety: void
	 * Uses Math.random() to generate distance, duration, and exhalation, and determines
	 * if the combination is safe according to isDerivedSafe()
	 * Prints a 4-tuple with the given values, and the safety of the combination.
	 */
	public static void printGeneratedCombinationDerivedSafety() {
		final int random_distance = randomizer(LARGE_DISTANCE * 3);
		final int random_duration = randomizer(LARGE_DURATION * 3);
		final int random_exhalation = randomizer(LARGE_EXHALATION_LEVEL * 3);
		
		final boolean isSafe = isDerivedSafe(random_distance, random_duration, random_exhalation);
		System.out.println("" + random_distance + COMMA + random_duration + COMMA + random_exhalation + COMMA + isSafe + "");
	}

	/**
	 * printGivenAndGeneratedCombinationsDerivedSafety: void
	 * Calls printGeneratedCombinationDerivedSafety multiple times to establish safety of combinations
	 * Prints a series of 4-tuples with distance, duration, and exhalation amounts, and the safety
	 * of their combination.
	 */
	public static void printGivenAndGeneratedCombinationsDerivedSafety() {
		System.out.println(GENERATE_START);
				
		for(int row = 0; row < SAFE_COMBINATIONS.length; row++) {
			System.out.print("");
			for(int col = 0; col < SAFE_COMBINATIONS[0].length; col++) {
				System.out.print(SAFE_COMBINATIONS[row][col] + COMMA);
			}
			System.out.print(true + NEWLINE);
		}
		
		System.out.println(LINE_BREAK);
		
		for(int i = 0; i < 10; i++) {
			printGeneratedCombinationDerivedSafety();
		}
	}
	
	/**
	 * printGivenAndGeneratedCombinationsInferredSafety: void
	 * Calls printGeneratedCombinationInferredSafety multiple times to establish safety of combinations
	 * Prints a series of 4-tuples with distance, duration, and exhalation amounts, and the safety
	 * of their combination.
	 */
	public static void printGivenAndGeneratedCombinationsInferredSafety() {
		System.out.println(GENERATE_START);
	
		for(int row = 0; row < SAFE_COMBINATIONS.length; row++) {
			System.out.print("");
			for(int col = 0; col < SAFE_COMBINATIONS[0].length; col++) {
				System.out.print(SAFE_COMBINATIONS[row][col] + COMMA);
			}
			System.out.print(true + NEWLINE);
		}
		
		System.out.println(LINE_BREAK);
		
		for(int i = 0; i < 10; i++) {
			printGeneratedCombinationInferredSafety();
		}
	}
	
	/**
	 * printGeneratedCombinationInferredSafety: void
	 * Uses Math.random() to generate distance, duration, and exhalation, and determines
	 * if the combination is safe according to isDerivedSafe()
	 * Prints a 4-tuple with the given values, and the safety of the combination.
	 */
	public static void printGeneratedCombinationInferredSafety() {
		final int random_distance = randomizer(LARGE_DISTANCE * 3);
		final int random_duration = randomizer(LARGE_DURATION * 3);
		final int random_exhalation = randomizer(LARGE_EXHALATION_LEVEL * 3);
		
		final boolean isSafe = isInferredSafe(random_distance, random_duration, random_exhalation);
		
		System.out.println("" + random_distance + COMMA + random_duration + COMMA + random_exhalation + COMMA + isSafe + "");
	}
	
	/**
	 * isInferredSafe: int*int*int => boolean
	 * Uses the Weka Classifier to determine if the combination of distance, duration,
	 * and exhalation is safe
	 */
	public static boolean isInferredSafe(final int distance, final int duration, final int exhalation) {
		final Classifier c = WekaClassifierFactory.getSingleton();
		final double[] inputFeatureValues = {distance, duration, exhalation};
		final String result = WekaUtil.predictString(c, featureNames, 
				inputFeatureValues, resultAttributeName, resultValueNames);
		return TRUE.equals(result)?true:false;
	}
	
	/**
	 * compareSafetyComputations: int
	 * Generates ten random combinations of distance, duration, and exhalation, and prints
	 * the derived and inferred safety values for each combination.
	 * Returns an integer representing the number of correct inferences.
	 */
	public static int compareSafetyComputations() {
		System.out.println("Distance,Duration,Exhalation,Derived,Inferred");
		final boolean[] derived_results = new boolean[10];
		final boolean[] inferred_results = new boolean[10];
		int correctInferences = 0;
		for(int i = 0; i < derived_results.length; i++) {
			final int randomDistance = (int) (Math.random() * (LARGE_DISTANCE * 3));
			final int randomDuration = (int) (Math.random() * (LARGE_DURATION * 3));
			final int randomExhalation = (int) (Math.random() * (LARGE_EXHALATION_LEVEL * 3));
			derived_results[i] = isDerivedSafe(randomDistance, randomDuration, randomExhalation);
			inferred_results[i] = isInferredSafe(randomDistance, randomDuration, randomExhalation);
			System.out.println(randomDistance + COMMA + randomDuration + COMMA 
								+ randomExhalation + COMMA + derived_results[i] + COMMA
								+ inferred_results[i]);
		}
		for(int j = 0; j < derived_results.length; j++) {
			if(derived_results[j] == inferred_results[j]) {
				correctInferences++;
			}
		}
		return correctInferences;
	}

}
