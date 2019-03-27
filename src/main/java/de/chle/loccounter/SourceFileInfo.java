package de.chle.loccounter;

import java.nio.file.Path;

public class SourceFileInfo {

	public final Path file;

	public int totalLines = 0;
	public int linesOfCode = 0;

	public SourceFileInfo(Path file) {
		this.file = file;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SourceFileInfo [file=");
		builder.append(file.getFileName());
		builder.append(", totalLines=");
		builder.append(totalLines);
		builder.append(", linesOfCode=");
		builder.append(linesOfCode);
		builder.append("]");
		return builder.toString();
	}

}
