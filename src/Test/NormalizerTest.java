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
    public static void main(String[] args) throws IOException {
        //path to map file
        String mapFile="/Users/msr/Projects/PersianTextNormalizer/src/Test/charmap.txt";
        Normalizer normalizer=new Normalizer(mapFile);
        System.out.println("مي‌شود 1 ۲ 3");
        System.out.print(normalizer.normalize("مي‌شود 1 ۲ 3"));
    }
}
