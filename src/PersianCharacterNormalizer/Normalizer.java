package PersianCharacterNormalizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Mohammad Sadegh Rasooli.
 * User: Mohammad Sadegh Rasooli
 * Date: 5/30/14
 * Time: 11:56 PM
 * To report any bugs or problems contact rasooli@cs.columbia.edu
 */

public class Normalizer {
    HashMap<String,String> charMap;

    public Normalizer(String mapFile) throws IOException {
         charMap=new HashMap<String, String>();

        BufferedReader reader=new BufferedReader(new FileReader(mapFile));
        String line="";
        while((line=reader.readLine())!=null){
            line=line.trim();
            String[] split=line.split("\t");
            if (split.length>=2)
            charMap.put(split[0],split[1]);
            else
                System.err.println(line);
        }

        charMap.put("\u200E","\u200C") ;
        charMap.put("\u200D","\u200C") ;
        charMap.put("\u200F","\u200C") ;
        charMap.put("\u2029","\u200C") ;
    }

    public String normalize(String text){
        StringBuilder newText=new StringBuilder();
       for (int i=0;i<text.length();i++){
           String thisChar=String.valueOf(text.charAt(i));
           if(charMap.containsKey(thisChar))
               thisChar=charMap.get(thisChar);
           newText.append(thisChar);

       }

        return newText.toString();
    }
}
