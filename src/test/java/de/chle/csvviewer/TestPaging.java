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
	void testPageFormatting()
	{
		Paging paging = new Paging(records, 3);
		Page firstPage = paging.firstPage();
		
		String formatted = firstPage.toString();
		assertNotNull(formatted);
		assertFalse(formatted.isEmpty());
		assertTrue(formatted.startsWith("Name"));
		assertTrue(formatted.endsWith("\n"));
	}
}
