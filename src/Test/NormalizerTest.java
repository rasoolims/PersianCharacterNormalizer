package Test;

import PersianCharacterNormalizer.Normalizer;

import java.io.IOException;

/**
 * Created by Mohammad Sadegh Rasooli.
 * User: Mohammad Sadegh Rasooli
 * Date: 5/31/14
 * Time: 12:00 AM
 * To report any bugs or problems contact rasooli@cs.columbia.edu
 */

public class NormalizerTest {
    public static void main(String[] args) throws Exception {
        //path to map file (use the file charmap.txt)
        String mapFile="/Users/msr/Projects/PersianTextNormalizer/src/Test/charmap.txt";


        if (args.length>=1){
            mapFile=args[0];
        }

        Normalizer normalizer=new Normalizer(mapFile);
        System.out.println("مي‌شود 1 ۲ 3");
        System.out.println(normalizer.normalize("مي‌شود 1 ۲ 3"));

        if(args.length>=3){
            String inputFile=args[1];
            String outputFile=args[2];

           normalizer.normalizeFile(inputFile,outputFile);
        }
    }
}
