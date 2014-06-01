package PersianCharacterNormalizer;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by Mohammad Sadegh Rasooli.
 * User: Mohammad Sadegh Rasooli
 * Date: 5/30/14
 * Time: 11:56 PM
 * To report any bugs or problems contact rasooli@cs.columbia.edu
 */

public class Normalizer {
    HashMap<String,String> charMap;
    HashMap<String,String> illegalSemiSpaceMap;


    public Normalizer(String mapFile) throws IOException {
         charMap=new HashMap<String, String>();
        illegalSemiSpaceMap=new HashMap<String, String>();

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

        String illegalSemiSpaces="\t ادذرزژوؤاآأإ";
        String illegalNextChars="آاأإ" ;
        for (int i=0;i<illegalSemiSpaces.length();i++){
            String str=String.valueOf(illegalSemiSpaces.charAt(i));
            String illegal=str+ "\u200C";
            illegalSemiSpaceMap.put(illegal,str);
        }
        for (int i=0;i<illegalNextChars.length();i++){
            String str=String.valueOf(illegalNextChars.charAt(i));
            String illegal="\u200C"+str;
            illegalSemiSpaceMap.put(illegal,str);
        }
    }

    public String normalize(String text){
        text=text.replace("\r","").replace("هٔ","ه‌ی").replace("...","…");

       StringBuilder newText=new StringBuilder();
       for (int i=0;i<text.length();i++){
           String thisChar=String.valueOf(text.charAt(i));
           if(charMap.containsKey(thisChar))
               thisChar=charMap.get(thisChar);
           newText.append(thisChar);
       }
        text= newText.toString();

        for(String illegalSequence:illegalSemiSpaceMap.keySet()){
            if(text.contains(illegalSequence))
                text=text.replace(illegalSequence,illegalSemiSpaceMap.get(illegalSequence));
        }

        return text;
    }

    public void normalizeFile(String file,String outputFile) throws Exception{
        BufferedReader reader=new BufferedReader(new FileReader(file));
        BufferedWriter writer=new BufferedWriter(new FileWriter(outputFile));
        int counter=0;
        String line="";
        while((line=reader.readLine())!=null){
            writer.write(normalize(line.trim())+"\n");
            counter++;
            if(counter%1000000==0)
                System.err.print(counter + "...");
        }
        writer.flush();
        writer.close();
        System.err.println("\nnormalize file done!");
    }
}
