package de.chle.loccounter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Interactors {

	//TODO: hier müssen noch die Callbacks an die UI übergeben werden!
	// nicht die UI in start() anlegen
	public static void start(String[] args) throws IOException {

		Path sourceDir = CommandLine.getPath(args);
		Stream<Path> sourceFiles = SourceFileProvider.findSourceFiles(sourceDir);
	
		Stream<SourceFileInfo> fileInfos = sourceFiles.map(Interactors::createFileInfo);

		UI ui = new UI();
		fileInfos.forEach(ui::addSourceInfo);
		ui.printSummary();
	}
	
	public static SourceFileInfo createFileInfo(Path sourceFile)
	{
		List<String> lines = SourceFileProvider.readFile(sourceFile);
		SourceFileInfo info = new SourceFileInfo(sourceFile);
		LOC.analyzeLines(lines, info);
		
		return info;
	}
}
