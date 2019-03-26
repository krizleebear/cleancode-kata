package de.chle.csvviewer;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPaging {

	private List<Record> records;

	@BeforeEach
	void readPersonen() throws IOException
	{
		Path file = Paths.get("testdata/CSVViewer/personen.csv");
		records = CSVParser.parse(file);
		
		assertNotNull(records);
		assertFalse(records.isEmpty());
	}
	
	@Test
	void testFirstPage() throws IOException
	{
		Paging paging = new Paging(records, 3);
		Page firstPage = paging.firstPage();
		
		assertEquals(3, firstPage.rows().count());
		assertEquals(5, firstPage.getHeader().getColumnCount());
	}
	
	@Test
	void testGetPageCount()
	{
		Paging paging = new Paging(records, 200);
		assertEquals(200, paging.getRecordCount());
		assertEquals(1, paging.getPageCount());
		
		paging = new Paging(records, 100);
		assertEquals(2, paging.getPageCount());
		
		paging = new Paging(records, 5);
		assertEquals(40, paging.getPageCount());
	}
	
	@Test
	void testPageFormatting()
	{
		Paging paging = new Paging(records, 3);
		Page firstPage = paging.firstPage();
		
		UI ui = new UI(firstPage);
		
		String formatted = ui.toString();
		assertNotNull(formatted);
		assertFalse(formatted.isEmpty());
		assertTrue(formatted.startsWith("Name"));
		assertTrue(formatted.endsWith("\n"));
	}
}
