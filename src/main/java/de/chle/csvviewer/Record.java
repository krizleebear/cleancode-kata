package de.chle.csvviewer;

import java.util.regex.Pattern;

public class Record {

	private static Pattern SPLITTER = Pattern.compile(";");

	public final String[] columns;

	private final int[] widths;
	
	public Record(String line)
	{
		columns = SPLITTER.split(line);
		
		this.widths = calculateColWidths();
	}
	
	public static Record lineToRecord(String line)
	{
		return new Record(line);
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

	public int[] getColumnWidths()
	{
		return widths;
	}
}
