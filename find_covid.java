package my_tool;

import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class find_covid{
	public static void main(String[] args) throws IOException {
		String test_file ="C:\\XX\\Day_Confirmation_Age_County_Gender_19CoV.csv";
		InputStream inputStream = new FileInputStream(test_file);
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader bReader = new BufferedReader(isr);
		FileWriter fWriter = new FileWriter("C:\\XX\\新北市_6月_永和區非境外.csv");
		BufferedWriter bWriter =new BufferedWriter(fWriter);
		try{
			String line ;
			int i=0 ;
			Pattern p_newtaipei = Pattern.compile("新北市");
			Pattern p_date_Pattern = Pattern.compile("2021/06");
			//是否為境外移入
			Pattern p_localPatternn = Pattern.compile("否");
			
			Pattern homePattern = Pattern.compile("永和");
		
			//per line
			while((line = bReader.readLine())!=null){
				if(i<=0) {
					i++;	
					bWriter.write(line + "\r\n");
				}
				
				Matcher may_matcher =p_date_Pattern.matcher(line);
				Matcher localMatcher = p_localPatternn.matcher(line);
				Matcher newtaipeiMatcher =p_newtaipei.matcher(line);
				Matcher homeMatcher = homePattern.matcher(line);
			
					//System.out.println(matcher.group());
					if (may_matcher.find() && localMatcher.find() && newtaipeiMatcher.find() && homeMatcher.find()  ) {
						i++;
						System.out.println(i);
						//System.out.print(line);
						bWriter.write(line + "\r\n");
					}
					//System.out.println((matcher.group().replaceAll(regex, replacement)(",", "")));				
			} 
		}catch(FileNotFoundException e){
				e.printStackTrace();
		}
		bReader.close();
		isr.close();
		inputStream.close();
		bWriter.close();
		fWriter.close();
	}
}
