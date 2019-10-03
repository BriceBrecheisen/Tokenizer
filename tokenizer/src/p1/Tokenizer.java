
package p1;

import java.util.ArrayList;


public class Tokenizer 
{
	//handles words
    private String word;
    //array handles reserved words
    private String[] res_words = new String[]{"class","constructor","method","function","int","boolean",
    		"char","void","var","static","field","let","do","if","else","while","return","true","false",
    		"null","this"};
    
    private String s1 = "([])({}|.,;+-*/&<>="; //symbols stored as string  
    private char[] symbols = s1.toCharArray(); //symbols parsed out into a char array 
    private String[] comment = new String [] {"//","*/","*/","/**"}; //handles comments
    
    private ArrayList<String> a1 = new ArrayList<String>();
    private ArrayList<String> a2 = new ArrayList<String>();
    
    //default constructor
    public Tokenizer() {}
}
    
    

