package de.chle.csvviewer;

import java.util.List;

public class Paging {

	private final Record header;
	private final List<Record> records;
	private final int pageSize;
	private int currentPage = 0;

	public Paging(List<Record> allRecords, int pageSize) {
		this.pageSize = pageSize;
		this.header = allRecords.remove(0);
		this.records = allRecords;
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
		int fromIndex = pageIndex * getPageSize();
		int toIndex = fromIndex + getPageSize();
		return records.subList(fromIndex, toIndex);
	}
	
	public int getPageCount()
	{
		final double pages = (double) records.size() / getPageSize();
		return (int) Math.ceil(pages);
	}

	public int getRecordCount() {
		return records.size();
	}

	public int getPageSize() {
		return pageSize;
	}

}
