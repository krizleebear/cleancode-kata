package de.chle.csvviewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

	public static List<Record> parse(Path csvFile) throws IOException {
		List<String> lines = Files.readAllLines(csvFile);
		return parseLines(lines);
	}

	public static List<Record> parseLines(List<String> lines) {

		List<Record> records = new ArrayList<>(lines.size());

		int id = 0;
		for (String line : lines) {
			if (line.trim().isEmpty()) {
				continue;
			}

			records.add(Record.lineToRecord(line, id));
			id++;
		}

		return records;
	}

}
