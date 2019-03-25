package de.chle.wordcount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WordCountMain {

	public static void main(String[] args) throws IOException {

		String text = readText(args);
		
		long wordCount = countWords(text);
		
		long stopWordCount = countStopWordsInText(text);
		wordCount = wordCount - stopWordCount;
		
		long uniqueWords = countUniqueWords(text);

		System.out.println("Number of stop words: " + stopWordCount);
		System.out.println("Number of words: " + wordCount);
		System.out.println("Unique words: " + uniqueWords);
	}

	public static long countWords(String text) throws IOException {

		Stream<String> words = splitToWords(text);
		return words.count();
	}
	
	public static long countStopWordsInText(String text) throws IOException
	{
		Set<String> stopWords = readStopWords();
		
		Stream<String> words = splitToWords(text);
		
		return words.filter(word -> stopWords.contains(word)).count();
	}

	public static long countUniqueWords(String text) {
		
		Stream<String> words = splitToWords(text);
		return words.distinct().count();
	}
	
	private static String readText(String[] args) throws IOException {

		if (args.length == 1) {
			Path textFile = Paths.get(args[0]);
			return readTextFromFile(textFile);
		}

		return readTextFromConsole();
	}

	private static String readTextFromFile(Path textFile) throws IOException {

		byte[] bytes = Files.readAllBytes(textFile);
		String text = new String(bytes, StandardCharsets.UTF_8);
		return text;
	}

	private static Set<String> readStopWords() throws IOException {
		
		Path stopWordFile = Paths.get("stopwords.txt");
		List<String> lines = Files.readAllLines(stopWordFile, StandardCharsets.UTF_8);

		return new HashSet<String>(lines);
	}

	private static Stream<String> splitToWords(String text) {
		return Pattern.compile("\\s+").splitAsStream(text);
	}

	public static String readTextFromConsole() throws IOException {
		System.out.println("Enter text: ");
		return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

}
