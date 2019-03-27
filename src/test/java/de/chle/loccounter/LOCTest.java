package de.chle.loccounter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LOCTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void isSourceLine() {
		
		assertFalse(LOC.isCodeLine(""));
		assertFalse(LOC.isCodeLine("   "));
		assertFalse(LOC.isCodeLine("//"));
		assertFalse(LOC.isCodeLine("   // comment"));
		assertFalse(LOC.isCodeLine(" // System.out.println()"));

		assertTrue(LOC.isCodeLine("System.out.println()"));
		assertTrue(LOC.isCodeLine("     System.out.println()"));
	}
	
	@Test
	void summary()
	{
		Summary summary = new Summary();
		
		assertEquals(0, summary.summary.linesOfCode);
		assertEquals(0, summary.summary.totalLines);
		
		SourceFileInfo info = new SourceFileInfo(Paths.get("test.java"));
		info.linesOfCode = 100;
		info.totalLines = 200;
		summary.addToSummary(info);
		
		assertEquals(100, summary.summary.linesOfCode);
		assertEquals(200, summary.summary.totalLines);
	}
	
	@Test
	void getPath(){
		
		// if no path is assigned, use current working dir
		Path noSourcePath = CommandLine.getPath(new String[] {});
		assertNotNull(noSourcePath);
		assertTrue(Files.isDirectory(noSourcePath));
		
		Path srcPath = CommandLine.getPath(new String[] {"src"});
		assertNotNull(srcPath);
		assertTrue(Files.isDirectory(srcPath));
	}
	
	@Test
	void findSourceFiles() throws IOException
	{
		Stream<Path> sourceFiles = SourceFileProvider.findSourceFiles(Paths.get("."));
		List<Path> files = sourceFiles.collect(Collectors.toList());
		
		assertTrue(files.size() > 1);
		assertTrue(files.get(0).getFileName().toString().endsWith(".java"));
	}
	
	
}
