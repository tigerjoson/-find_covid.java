package my_tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class go {

	public static void main(String[] args) throws IOException {
	//	file_RW.print_to_screen("C:\\Users\\tiger\\Desktop\\6041_B14_十四.金融機構基本資料.csv",10);
     //  file_RW.write_file_info("C:\\Users\\tiger\\Desktop\\臺北市電子地圖使用者統計.csv");
		 
			try {
				int line_number = 0;
				String source_path="C:\\Users\\tiger\\Desktop\\6041_B14_十四.金融機構基本資料.csv";
				String destination_folder="C:\\Users\\tiger\\Desktop\\temp\\6041_B14_十四.金融機構基本資料.csv";
				//每行
				String line;
				// Financial Institutions Code (總機構代碼)
				Pattern pattern_Financial_Institutions_Code = Pattern.compile("^\\w{3,8},");
				Pattern pattern_Branch_Financial_Institutions_Code= Pattern.compile("\\,\\w{7}\\,");
				InputStream inputStream = new FileInputStream(source_path);
				InputStreamReader isr = new InputStreamReader(inputStream);
				BufferedReader bReader = new BufferedReader(isr);
				FileWriter fWriter = new FileWriter(destination_folder);
				BufferedWriter bWriter =new BufferedWriter(fWriter);
				while ((line = bReader.readLine()) != null) {
//					System.out.println(line.length());
				//System.out.println(line_number);
					Matcher matcher_Financial_Institutions_Code = pattern_Financial_Institutions_Code.matcher(line);
					Matcher matcher_Branch_Financial_Institutions_Code = pattern_Branch_Financial_Institutions_Code.matcher(line);
					line_number++;
					if (matcher_Branch_Financial_Institutions_Code.find()&& matcher_Financial_Institutions_Code.find()) {
					//	bWriter.write(matcher_Financial_Institutions_Code.group()+matcher_Branch_Financial_Institutions_Code.group() + "\r\n");
						//System.out.print("matcher_Branch_Financial_Institutions_Code:"+matcher_Branch_Financial_Institutions_Code.group());
						//System.out.println("matcher_Financial_Institutions_Code:"+matcher_Financial_Institutions_Code.group());
					}else {
						System.out.print(line_number+":");
						System.out.print("matcher_Financial_Institutions_Code:"+matcher_Financial_Institutions_Code.find()+",");
						System.out.println("matcher_Branch_Financial_Institutions_Code:"+matcher_Branch_Financial_Institutions_Code.find());
						bWriter.write(line+"\r\n");
						
					}
//					//System.out.println(line);
				
			}
				bReader.close();
				inputStream.close();
				isr.close();
				bWriter.close();
				fWriter.close();
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException e");
				e.printStackTrace();
			}
		

	}
}
