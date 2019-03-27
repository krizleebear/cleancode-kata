package de.chle.loccounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class SourceFileProvider {

	public static List<String> readFile(Path file) {
		try {
			return Files.readAllLines(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Stream<Path> findSourceFiles(Path sourceDir) throws IOException {

		return Files.walk(sourceDir).filter(SourceFileProvider::isSourceFile);
	}

	public static boolean isSourceFile(Path file) {
		Path filename = file.getFileName();
		return filename.toString().endsWith(".java");
	}
}
