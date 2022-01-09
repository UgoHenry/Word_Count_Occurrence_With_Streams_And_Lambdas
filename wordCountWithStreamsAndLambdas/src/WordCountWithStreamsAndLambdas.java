import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCountWithStreamsAndLambdas {
    public static void main(String[] args) {
//        Creating wordCounter which holds words as keys and their occurrences as values
        HashMap<String, Integer> wordCounter = new HashMap<String, Integer>();
        BufferedReader reader = null;
        try {
            //Creating BufferedReader object
            reader = new BufferedReader(new FileReader("C:\\Users\\Dell\\Desktop\\random story3.txt"));
//Reading the first line into str
            String str = reader.readLine();

            while (str != null) {
//splitting str into words and convert it into list
                List<String> words = Stream.of(str).map(w -> w.toLowerCase().split(" ")).flatMap(Arrays::stream)
                        .collect(Collectors.toList());
//Iterating each word
                for (String word : words) {
//if word is already present in wordCounter.... updating its count
                    if (wordCounter.containsKey(word)) {
                        wordCounter.put(word, wordCounter.get(word) + 1);
                    }
//else, inserting the word as key and 1 as its value
                    else {
                        wordCounter.put(word, 1);
                    }
                }
//Reading next line into str
                str = reader.readLine();

            }

//Getting all the entries of wordCounter in the form of Set
            Set<Map.Entry<String, Integer>> entrySet = wordCounter.entrySet();
//Creating a List by passing the entrySet
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(entrySet);
//Sorting the list in the decreasing order of values
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                    return (e2.getValue().compareTo(e1.getValue()));
                }
            });
//Printing Words and number of their occurrences
            System.out.println("Words and number of their occurrences :");
            for (Map.Entry<String, Integer> entry : list) {
                if (entry.getValue() >= 1) {
                    System.out.println("Words: " + entry.getKey() + "------>  Number of occurrence : " + entry.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close(); //    Closing the reader
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
