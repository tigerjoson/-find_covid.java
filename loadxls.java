package myexcel;

import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class loadxls {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
			
		Accessexcel accessexcel2 = new Accessexcel();
		accessexcel2.setfile("C:\\Users\\tiger\\Downloads\\endpoint.xls");
		InputStream i2 =  accessexcel2.getinputStream();
		HSSFWorkbook hssfWorkbook2 = new HSSFWorkbook(i2);
		
		Accessexcel adminAccessexcel = new Accessexcel();
		adminAccessexcel.setfile("C:\\Users\\tiger\\OneDrive\\桌面\\hn\\table factory\\oridinal\\Administrators最高權限.xlsx");
		InputStream admInputStream = adminAccessexcel.getinputStream();
		HSSFWorkbook hssfadminbook = new HSSFWorkbook(admInputStream);
		
		
		System.out.println(hssfadminbook.getNumberOfNames());
		
		
		HSSFSheet brlistHssfSheet2 = hssfWorkbook2.getSheet("brlist");
		System.out.println( brlistHssfSheet2.getLastRowNum());
		DataFormatter fmt = new DataFormatter();
		ArrayList<String> brfullnameArrayList = new ArrayList<String>();
		for(int i=1;i<brlistHssfSheet2.getLastRowNum();i++) {
			HSSFRow hssfRow = brlistHssfSheet2.getRow(i);
			//column0
			HSSFCell hssfcell= hssfRow.getCell(0);
			//column1
			HSSFCell hssfcell2= hssfRow.getCell(1);
			String brcodestring = fmt.formatCellValue(hssfcell);
			String brnamestring = fmt.formatCellValue(hssfcell2);
			// System.out.println(brnamestring);
			brfullnameArrayList.add(brcodestring+brnamestring);
		}
	//make excel\\10.8.50.250.
	
		
		

		//System.out.print(attach2.getSheetName());
		
		 
	}

}
