package de.chle.loccounter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CommandLine {

	public static Path getPath(String[] args) {
		if (args.length < 1) {
			return Paths.get(".").toAbsolutePath();
		} else {
			return Paths.get(args[0]).toAbsolutePath();
		}
	}
}
