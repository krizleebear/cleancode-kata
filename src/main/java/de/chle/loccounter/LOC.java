package de.chle.loccounter;

import java.util.List;

public class LOC {

	public static void analyzeLines(List<String> lines, SourceFileInfo info) {
		for (String line : lines) {

			info.totalLines++;

			if (isCodeLine(line)) {
				info.linesOfCode++;
			}
		}
	}

	public static boolean isCodeLine(String line) {

		line = line.trim();

		if (line.isEmpty()) {
			return false;
		}

		if (line.startsWith("//")) {
			return false;
		}

		return true;
	}

}
