package de.chle.csvviewer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVViewerMain {

	public static void main(String[] args) throws IOException {

		Path file = Paths.get(args[0]);
		
		int pageSize = 5;
		if(args.length > 1)
		{
			pageSize = Integer.parseInt(args[1]);
		}
		
		view(file, pageSize);
		
	}

	private static void view(Path file, int pageSize) throws IOException {
		List<Record> records = CSVParser.parse(file);
		Paging paging = new Paging(records, pageSize);
		Page firstPage = paging.firstPage();
		
		UI ui = new UI(firstPage);
		
		System.out.println(ui);
	}

	
	
}
