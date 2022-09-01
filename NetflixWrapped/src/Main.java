import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Path to Netflix viewing history CSV file
        String path = "C:.\\NetflixViewingHistory.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
        //Checks to see if next line is null, parses through entire file
            HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
            HashMap<String, Integer> dates = new HashMap<String, Integer>();
            HashMap<String, Integer> movies = new HashMap<String, Integer>();
            while((line = br.readLine()) != null) {
                //Splits data in CSV file based on being surrounded by quotation marks instead of commas
                String[] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                String[] split2 = splitted[0].split(":");
                split2[0] = split2[0].substring(1,split2[0].length());
                if (splitted[0].indexOf(':') == -1) {
                    if (movies.containsKey(split2[0])) {
                        movies.replace(split2[0], movies.get(split2[0]) + 1);
                    } else {
                        movies.put(split2[0], 1);
                    }
                }

                if (split2[0].charAt(split2[0].length() - 1) == '"') {
                    split2[0] = split2[0].substring(0,split2[0].length() - 1);
                }
                if (frequencies.containsKey(split2[0])) {
                    frequencies.replace(split2[0], frequencies.get(split2[0]) + 1);
                } else {
                    frequencies.put(split2[0], 1);
                }

                if (dates.containsKey(splitted[1])) {
                    dates.replace(splitted[1], dates.get(splitted[1]) + 1);
                } else {
                    dates.put(splitted[1], 1);
                }

            }
            String show = Collections.max(frequencies.entrySet(), Map.Entry.comparingByValue()).getKey();
            frequencies.remove(show);
            String show2 = Collections.max(frequencies.entrySet(), Map.Entry.comparingByValue()).getKey();
            frequencies.remove(show2);
            String show3 = Collections.max(frequencies.entrySet(), Map.Entry.comparingByValue()).getKey();
            frequencies.remove(show3);
            String show4 = Collections.max(frequencies.entrySet(), Map.Entry.comparingByValue()).getKey();
            frequencies.remove(show4);
            String show5 = Collections.max(frequencies.entrySet(), Map.Entry.comparingByValue()).getKey();

            String key2 = Collections.max(dates.entrySet(), Map.Entry.comparingByValue()).getKey();
            String movie = Collections.max(movies.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("Top 5 watched titles: ");
            System.out.println(show);
            System.out.println(show2);
            System.out.println(show3);
            System.out.println(show4);
            System.out.println(show5);
            System.out.println("Most binging was on day: " + key2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
