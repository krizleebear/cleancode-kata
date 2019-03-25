package de.chle.csvviewer;

import java.util.List;

public class Paging {

	private final Record header;
	private final List<Record> rows;
	private final int windowSize;
	private int currentPage = 0;

	public Paging(List<Record> allRecords, int pageSize) {
		this.windowSize = pageSize;
		this.header = allRecords.remove(0);
		this.rows = allRecords;
	}

	public Page firstPage() {
		currentPage = 0;
		return getCurrentPage();
	}

	public Page getCurrentPage() {
		return getPage(currentPage);
	}

	public Page getPage(int pageIndex) {
		return new Page(header, rowsForPage(pageIndex));
	}

	public List<Record> rowsForPage(int pageIndex) {
		int fromIndex = pageIndex * windowSize;
		int toIndex = fromIndex + windowSize;
		return rows.subList(fromIndex, toIndex);
	}

}
