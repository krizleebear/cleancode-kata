package de.chle.wordcount;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.chle.wordcount.WordCountMain;

class TestWordCount {

	@Test
	void testReadFromFile() throws IOException {
		WordCountMain.main(new String[] {"myText.txt"});
	}
	
	@Test
	void countUniqueWords()
	{
		String text = "Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.";
		long uniqueWords = WordCountMain.countUniqueWords(text);
		
		assertEquals(8, uniqueWords);
	}
	
	@Test
	void countUniqueWordsWithStopWords()
	{
		String text = "Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.";
		long uniqueWords = WordCountMain.countUniqueWords(text);
		
		assertEquals(8, uniqueWords);
	}
}
