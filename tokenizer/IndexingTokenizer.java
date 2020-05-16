package tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndexingTokenizer implements Tokenizer{
        @Override
	public ArrayList<String> tokenize(String s) {

		// Note: can see following page for special characters in Java Regex:
		//       https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html
		
		ArrayList<String> ret = new ArrayList<String>();
		String[] words = s.split("[,?!]|[\\s]");
                
                for ( String individual : words) {
                                
                   if(!individual.isEmpty()){                       
                        ret.add(individual.toLowerCase()); 
                   }
                }

		return ret;
	}
}
