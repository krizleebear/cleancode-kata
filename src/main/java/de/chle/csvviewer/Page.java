package de.chle.csvviewer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Stream;

public class Page {

	private final Record header;
	private final List<Record> rows;
	private final int[] columnWidths;

	public Page(Record header, List<Record> rows) {

		this.header = header;
		this.rows = rows;

		this.columnWidths = new int[header.getColumnCount()];
		calculateColumnWidths();
	}

	public Stream<Record> rows()
	{
		return rows.stream();
	}

	protected void calculateColumnWidths() {

		updateColWidths(getHeader());

		for (Record row : rows) {
			updateColWidths(row);
		}
	}

	protected void updateColWidths(Record record) {

		int[] otherWidths = record.getColumnWidths();

		for (int i = 0; i < getColumnWidths().length; i++) {
			getColumnWidths()[i] = Math.max(getColumnWidths()[i], otherWidths[i]);
		}
	}

	public String toString() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		printHeader(pw);

		printRows(pw);
		
		pw.flush();

		return sw.toString();
	}

	private void printHeader(PrintWriter pw) {
		printRow(getHeader(), pw);

		printRuler(pw);

	}

	private void printRuler(PrintWriter pw) {
		
		for(int i=0; i<columnWidths.length; i++)
		{
			printPadding(pw, columnWidths[i], "-");
			pw.print("-+-");
		}
		
		pw.println();
	}

	private void printRows(PrintWriter pw) {
		for (Record row : rows) {
			printRow(row, pw);
		}
	}

	private void printRow(Record row, PrintWriter pw) {
		int i=0;
		for(String col : row.columns)
		{
			pw.print(col);
			
			int padding = columnWidths[i] - col.length();
			String paddingChar = " ";
			printPadding(pw, padding, paddingChar);
			
			pw.print(" | ");
			
			i++;
		}
		pw.println();
	}

	private void printPadding(PrintWriter pw, int padding, String paddingChar) {
		for(int j=0; j<padding; j++)
		{
			pw.print(paddingChar);
		}
	}

	public int[] getColumnWidths() {
		return columnWidths;
	}

	public Record getHeader() {
		return header;
	}

}
