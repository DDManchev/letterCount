package wordsCountTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {

	static final char BARCHART_SYMBOL = '#';

	public static void printBarChart(int timesToPrint) {
		for (int i = 0; i < timesToPrint; i++) {
			System.out.print(BARCHART_SYMBOL);
		}
		System.out.println("");

	}

	public static <K, V extends Comparable<? super V>> Map<K, V> SortingMapByValues(Map<K, V> map) {
		List<Map.Entry<K, V>> listOfLetters = new LinkedList<>(map.entrySet());
		Collections.sort(listOfLetters, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> toCompareletter, Map.Entry<K, V> compLetter) {
				return (compLetter.getValue()).compareTo(toCompareletter.getValue());
			}
		});

		Map<K, V> res = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : listOfLetters) {
			res.put(entry.getKey(), entry.getValue());
		}
		return res;
	}

	public static void letterPrinting(Map<Character, Integer> mapofCharactersFromSentence) {

		mapofCharactersFromSentence = Main.SortingMapByValues(mapofCharactersFromSentence);
		Set<Map.Entry<Character, Integer>> setForIteration = mapofCharactersFromSentence.entrySet();
		int printingCoef = (int) Math
				.ceil((double) 20 / mapofCharactersFromSentence.entrySet().iterator().next().getValue());
		int loopCondition = (mapofCharactersFromSentence.size() > 20) ? 20 : mapofCharactersFromSentence.size();
		int indexOfCurrentLetterInIteration = 0;
		System.out.println("Most common letters:");
		for (Iterator loopIterator = setForIteration
				.iterator(); indexOfCurrentLetterInIteration < loopCondition; indexOfCurrentLetterInIteration++) {
			Map.Entry currentLetter = (Entry) loopIterator.next();
			int letterChartFinalMultiplier = (int) currentLetter.getValue() * printingCoef;
			System.out.print(currentLetter.getKey() + ": " + currentLetter.getValue() + " ");
			printBarChart(letterChartFinalMultiplier);

		}

	}


	public static void main(String[] args) {
		int k = 0;
		String inputString = null;
		BufferedReader inputDStream = null;
		char[] arrayOfLetters = null;
		Map<Character, Integer> mappingOfLetters = new LinkedHashMap();

		try {

			inputDStream = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Please enter a sentence:");
			inputString = inputDStream.readLine();
			arrayOfLetters = inputString.toUpperCase().toCharArray();
			for (int i = 0; i < inputString.length(); i++) {
				if (mappingOfLetters.containsKey(arrayOfLetters[i])) {
					mappingOfLetters.put(arrayOfLetters[i], mappingOfLetters.get(arrayOfLetters[i]) + 1);
				} else {
					if (Character.isLetter(arrayOfLetters[i])) {
						mappingOfLetters.put(arrayOfLetters[i], 1);
					} else {
						continue;
					}
				}

			}
			
			
		letterPrinting(mappingOfLetters);	
				

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
