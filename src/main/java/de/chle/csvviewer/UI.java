package de.chle.csvviewer;

import java.io.PrintWriter;
import java.io.StringWriter;

public class UI {
	
	private final Page page;
	private int[] columnWidths;

	public UI(Page page)
	{
		this.page = page;
		
		columnWidths = page.getColumnWidths();
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
		printRow(page.getHeader(), pw);

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
		
		page.rows().forEach(row -> printRow(row, pw));
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
	
}
