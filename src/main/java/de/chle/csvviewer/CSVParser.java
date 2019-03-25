package de.chle.csvviewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVParser {

	public static List<Record> parse(Path csvFile) throws IOException
	{
		List<String> lines = Files.readAllLines(csvFile);
		return parseLines(lines);
	}

	public static List<Record> parseLines(List<String> lines) {
		
		Stream<Record> records = lines.stream().map(Record::lineToRecord);
		
		return records.collect(Collectors.toList());
	}
	
	
}
