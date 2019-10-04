package p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class main {
	
	public static void main(String[] args) throws Exception 
	{
		//new instance of Tokenizer
		Tokenizer tokenizer = new Tokenizer();
        
        try 
        {
			File file = new File("SquareGame.jack");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) 
			{
				tokenizer.Analyze(line);           
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}

}
