
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
    
    //arrayLists that will contain analyzed code to be converted to xml
    private ArrayList<String> a1 = new ArrayList<String>();
    private ArrayList<String> a2 = new ArrayList<String>();
    Output xml = new Output(); // new instance of Output
    
    //default constructor
    public Tokenizer() {}
    
    //method to analyze a string
    public void Analyze_str(String a)
    {
        System.out.println("<stringConstant> "+ a +" <stringConstant>");
        a1.add("symbol");
        a2.add(a);
    }
    
    //method to analyze for either keywords or identifiers
    public void Analyze_string(String a)
    {
        int i=0;
        for(String o : res_words)
        {
            if(o.equalsIgnoreCase(a))
                i=1;
        }
        if(i==1)
        {
            System.out.println("<keyword> "+ a +" </keyword>");
            a1.add("keyword");
            a2.add(a);
        }
        else
        {
            System.out.println("<identifier> "+ a +" </identifier>");
            a1.add("identifier");
            a2.add(a);
        }
    }
    
    //method to analyze an integer
    public void Analyze_integer(String a)
    {
        int b = Integer.parseInt(a);
        System.out.println("<integerConstant> "+ a +" <integerConstant>");
        a1.add("integerConstant");
        a2.add(a);
    }
    
    public void Analyze_char(char a)
    {
        int i=0;
        for(char o : symbols)
        {
            if(a==o)
                i=1;
        }
        if(i==1)
        {
            System.out.println("<symbol> "+ a +" <symbol>");
            String stra = String.valueOf(a);
            a1.add("symbol");
            a2.add(stra);
        }
    }
    
    
    public void Analyze(String token) throws Exception
    {
    	int i = 0,j = 0;//simple iterators
        int count_char =0; // counts characters
        int first =0; // keeps track of first character each loop
    	StringBuilder tb = new StringBuilder(); //the token builder
    	String current_t = null; //contains current token to be analyzed
    	
        //array of character that will contain the character present in the string passed in parameter

        while(count_char <=(token.length()-1)){
            first = count_char;
            i=0;
         // check for if first char is a letter
            if (Character.isLetter(token.charAt(first)))
            { 
                tb.append(token.charAt(first)); // appends letter to token builder
                i =first + 1;
                //while char is a letter or a integer
                while(Character.isLetter(token.charAt(i)) || Character.isDigit(token.charAt(i)))
                {
                    tb.append(token.charAt(i)); //append to token builder
                    i++;
                }
                current_t = tb.toString(); // takes token builder and makes it a string
                //calls string_result analyzer
                this.Analyze_string(current_t); 
                //clears the token builder
                tb.delete(0, tb.length()); 
                count_char = i;
            }
            
         // check for if the first char is a letter
            else if (Character.isDigit(token.charAt(first)))
            { 
            	//append to token buuilder
                tb.append(token.charAt(first)); 
                i= first + 1;
                //while character is a digit
                while(Character.isDigit(token.charAt(i)))
                {
                	//append
                    tb.append(token.charAt(i));
                    i++;
                }
                
                //convert token buiilder to string, places it into current_t
                current_t = tb.toString(); 
                //calls Analyze)integer_result
                this.Analyze_integer(current_t);
                //clears the token builder
                tb.delete(0, tb.length()); 
                count_char = i;
            }
            
            //check for if chracter is a whitespace
            else if(Character.isWhitespace(token.charAt(first)))
            { 
                count_char += 1;
            }
         // check if first char is a string
            else if(token.charAt(first)=='"')
            { 
                i = first + 1;
                while(token.charAt(i)!='"')
                {
                	//appends to the token builder
                    tb.append(token.charAt(i)); 
                    i++;
                }
                //converts to string the token builder
                current_t = tb.toString(); 
                //calls the Anaylze_str_result function
                this.Analyze_str(current_t);
                //clears the token builder
                tb.delete(0, tb.length()); 
                count_char += i;
            }
            //follwoing if statements are for checking if character is a comment
            else if(token.charAt(first)=='/')
            { 
                i=first + 1;
                if(token.charAt(i)!='/')
                {
                    i=token.length();
                }
                count_char+=i;
            }
            
            else if(token.charAt(first)=='/')
            { 
                i = first + 1;
                if(token.charAt(i)!='*')
                {
                    i=token.length();
                }
                count_char+=i;
            }
            else if(token.charAt(first)=='*')
            {   
                i=token.length();
                count_char += i;
            }
            
            else if(token.charAt(first)=='*')
            { 
                i=first + 1;
                if(token.charAt(i)!='/')
                {
                    i=token.length();
                }
                count_char+=i;
            }
            else 
            {
                this.Analyze_char(token.charAt(first));
                 count_char = first + 1;
            }
        }
          
        //creates an output using array lists
        xml.create_Output(a1, a2);
    	
    }
}
    
    

