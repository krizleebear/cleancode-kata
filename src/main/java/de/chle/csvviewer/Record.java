package de.chle.csvviewer;

import java.util.regex.Pattern;

public class Record {

	private static Pattern SPLITTER = Pattern.compile(";");

	public final String[] columns;

	private final int[] widths;

	private final int id;

	public Record(String line, int recordID) {
		this.id = recordID;

		this.columns = SPLITTER.split(line);

		this.widths = calculateColWidths();
	}

	public static Record lineToRecord(String line) {
		return new Record(line, 0);
	}

	public static Record lineToRecord(String line, int recordNumber) {
		return new Record(line, recordNumber);
	}

	protected int[] calculateColWidths() {

		int[] widths = new int[getColumnCount()];

		int i = 0;
		for (String col : columns) {
			widths[i] = Math.max(widths[i], col.length());
			i++;
		}

		return widths;
	}

	public int getColumnCount() {
		return columns.length;
	}

	public int[] getColumnWidths() {
		return widths;
	}

	public int getId() {
		return id;
	}
}
