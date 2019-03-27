package de.chle.loccounter;

public class UI {

	Summary summary = new Summary();

	public void addSourceInfo(SourceFileInfo info) {
		System.out.println(info);
		summary.addToSummary(info);
	}

	public void printSummary() {
		System.out.println(summary);
	}

}
