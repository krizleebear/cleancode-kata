package de.chle.csvviewer;

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

	

	public int[] getColumnWidths() {
		return columnWidths;
	}

	public Record getHeader() {
		return header;
	}

}
