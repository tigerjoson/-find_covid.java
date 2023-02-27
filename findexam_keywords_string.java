package my_tool;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class findexam_keywords_string {
	public static void main(String[] args) throws Exception {
		File_RW file_RW = new File_RW();
		String readfolderparhString = "folder_path";
		file_RW.setFolderpathString(readfolderparhString);
		ArrayList<String> arrayList = file_RW.get_all_files(file_RW.getFolderpathString());
		for (int i = 0; i < arrayList.size(); i++) {
			InputStream inputStream = new FileInputStream(arrayList.get(i));
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader bReader = new BufferedReader(isr);
			String writefilepathString = arrayList.get(i).replace(".txt", "result.txt");
			FileWriter fWriter = new FileWriter(writefilepathString);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			try {
				String line;
				int line_number = 0;
				Pattern target_string = Pattern.compile("現金卡");
				// per line
				while ((line = bReader.readLine()) != null) {
					if (line_number <= 0) {
						i++;
						bWriter.write(line + "\r\n");
					}
					Matcher matcher = target_string.matcher(line);
					// System.out.println(matcher.group());
					if (matcher.find()) {
						System.out.println(line_number);
						System.out.print(line);
						bWriter.write(line + "\r\n");
					}
					// System.out.println((matcher.group().replaceAll(regex, replacement)(",",
					// "")));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			bReader.close();
			isr.close();
			inputStream.close();
			bWriter.close();
			fWriter.close();
		}

	}
}
