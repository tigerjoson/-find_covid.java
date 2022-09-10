package my_tool;

import java.io.File;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.odftoolkit.simple.Document;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;

public class modify {
	public modify() {
	}

	static void remove_column_by_index(File file, int start_of_column, int end_of_column) throws Exception {
		int delete_count = end_of_column - start_of_column + 1;
		String output_filetostring = file.toString().replace(".ods", "") + "_result.ods";
		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(file);
		Table activetable = spreadsheetDocument.getSheetByIndex(0);
		activetable.removeColumnsByIndex(start_of_column, delete_count);
		SpreadsheetDocument spreadsheetDocument2 = SpreadsheetDocument.newSpreadsheetDocument();
		// table
		spreadsheetDocument2.appendSheet(activetable, output_filetostring);
		spreadsheetDocument2.save(output_filetostring);
		System.out.println("ok~!");
		System.out.println(output_filetostring);
	}

	

	static void remove_column_by_total_column(File file, int start_of_column, int total_columns) throws Exception {

		String output_filetostring = file.toString().replace(".ods", "") + "_result.ods";
		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(file);
		Table activetable = spreadsheetDocument.getSheetByIndex(0);
		activetable.removeColumnsByIndex(start_of_column, total_columns);
		SpreadsheetDocument spreadsheetDocument2 = SpreadsheetDocument.newSpreadsheetDocument();
		// table
		spreadsheetDocument2.appendSheet(activetable, output_filetostring);
		spreadsheetDocument2.save(output_filetostring);
		System.out.println("ok~!");
		System.out.println(output_filetostring);
	}
	
	static void insertstring() {
		
	}
}
