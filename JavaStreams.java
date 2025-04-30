import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class JavaStreams {

    // STREAM has 3 methods
        //  1. Source
        //  2. Operation
        //  3. Collections (Termination)
        
    public static void main(String[] args) {

        //1. Integer stream
        IntStream
            .range(1, 10)
            .forEach(e -> System.out.print(e + ", "));

    

        //2. Integer stream with skip
        IntStream
            .range(1, 10)
            .skip(5)
            .forEach(x -> System.out.print(x + ", "));

    

        //3. Integer stream with sum
        int sum = IntStream
            .range(1, 10)
            .sum();
        System.out.println("Sum: " + sum);

        //4. Stream .of() sorted() .findFirst
        Stream.of("Ava", "Aneri", "Alberto")
            .sorted()
            .findFirst()
            .ifPresent(e -> System.out.println(e));
    
        
        //5. Stream from Array, sort, filter and print
        String[] names = {"Al", "Anugrah", "Sebastian", "Gary", "Dudul"};
        Arrays.stream(names)        //same as .of(names)
            .sorted()
            .filter(x -> x.startsWith("A"))
            .forEach(x -> System.out.print(x + ", "));

        
        //6. Average of squares of an int array
        Arrays.stream(new int[] {2, 4, 7, 6, 1})
            .map(x -> x * x)
            .average()
            // .forEach(x -> System.out.println(x));
            .ifPresent(System.out::print);

        //7. Stream from List, filter and print
        // List<String> people = Arrays.asList("Al", "Anugrah", "Sebastian", "Gary", "Dudul");
        // people
        //     .stream()
        //     .map(x -> String.toLower(x))
        //     .filter(x -> x.startsWith("a"))
        //     .forEach(System.out::println);

        //8. Stream rows from text file, sort, filter and print
        // Stream<String> bands = Files.lines(Path.get("bands.txt"));
        // bands   
        //     .sorted()
        //     .filter(x -> x.length() > 13)
        //     .forEach(System.out::println);
        // bands.close();

        // //9. Stream rows from text file and save to List
        // List<String> bands2 = Files.lines(Path.get("bands.txt"))
        //                             .filter(x -> contains("jit"))
        //                             .collect(Collector.toList());
        // bands2.forEach(x -> System.out.println(x + ","));

        // //10. Stream rows from CSV file and count the rows with 3 columns
        // Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
        // int rowCount = (int)rows1
        //                     .map(x -> x.split(","))
        //                     .filter(x -> x.length == 3)
        //                     .count();
        // System.out.println("Rows:" + rowCount);
        // rows1.close();
        
        
        // //11. Stream rows from CSV file, count the rows with 3 columns and print rows 1st column value > 15
        // Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
        // rows2
        // .map(x -> x.split(","))
        // .filter(x -> x.length == 3)
        // .filter(x -> Integer.parseInt(x[1]) > 15)
        // .forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[1]));
        // rows2.close();
        
        // //12. Stream rows from CSV file, store fields in HashMap
        // Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
        // Map<String, Integer> map = new HashMap<>();
        // map = rows3
        //         .map(x -> x.split(","))
        //         .filter(x -> x.length == 3)
        //         .filter(x -> Integer.parseInt(x))
        //         .collect(Collectors.toMap(x -> x[0], x -> Integer.parseInt(x[1])));
        // for (String key : map.keySet()) {
        //     System.out.println(key + " " + map.get(key));
        // }
        // rows3.close();


        //13. Reduction - sum
        // double total = Stream.of(7.3, 11, 5.1, 4.6)
        //                 .reduce(0, (Double accummulator, Double element) -> accummulator + element);

        // //14. Reduction - summary statistics (CAN ONLY BE APPLIED TO INTEGERS)
        // IntSummaryStatistics summaryStatistics = IntStream
        //                                             .range(0, 10)
        //                                             .summaryStatistics();
        // System.out.println(summaryStatistics);


        //15. Given a sentence find the word that has the highest length
        String str = "I am evil";
        Optional<String> maxLength =  Arrays
                                .stream(str.split(" "))
                                .max(Comparator.comparing(s -> s.length()));

        //16. Remove duplicates from the string and return in the same order
        String str2 = "dajnjadskasd";
        String distinctCharString = Arrays
            .stream(str2.split(""))
            .distinct()
            .collect(Collectors.joining());
        System.out.println(distinctCharString);
    }
}
