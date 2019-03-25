package de.chle.csvviewer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CSVViewerTest {

	@Test
	void testParseRecord() {
		
		Record record = Record.lineToRecord("Peter;42;NewYork");
		
		assertNotNull(record);
		assertEquals(3, record.columns.length);
	}
	
	@Test
	void testParseLines()
	{
		List<String> lines = new ArrayList<>();
		lines.add("a;b;c");
		lines.add("1;2;3");
		
		List<Record> records = CSVParser.parseLines(lines);
		assertNotNull(records);
		assertEquals(2, records.size());
		
		Record header = records.get(0);
		assertEquals(3, header.columns.length);
	}
	
	@Test
	void testColumnWidths()
	{
		Record record = Record.lineToRecord("1234;12;1234567");
		
		int[] colWidths = record.calculateColWidths();
		assertArrayEquals(new int[] {4,2,7}, colWidths);
	}

	@Test 
	void testPageColumnWidth()
	{
		Record header = Record.lineToRecord("1;12;123");
		
		List<Record> rows = new ArrayList<>();
		rows.add(Record.lineToRecord("12;123;1234"));
		rows.add(Record.lineToRecord("1;1;1"));
		rows.add(Record.lineToRecord("123;1234;12345"));
		
		Page page = new Page(header, rows);
		int[] colWidths = page.getColumnWidths();
		
		assertArrayEquals(new int[] {3,4,5}, colWidths);
	}
}
