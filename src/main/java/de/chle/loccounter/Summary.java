package de.chle.loccounter;

import java.nio.file.Paths;

public class Summary {

	SourceFileInfo summary = new SourceFileInfo(Paths.get(".").toAbsolutePath());
	
	public void addToSummary(SourceFileInfo info) {
		summary.linesOfCode += info.linesOfCode;
		summary.totalLines += info.totalLines;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("Summary [dir=");
		builder.append(summary.file.toAbsolutePath());
		builder.append(", totalLines=");
		builder.append(summary.totalLines);
		builder.append(", linesOfCode=");
		builder.append(summary.linesOfCode);
		builder.append("]");
		
		return builder.toString();
	}
}
