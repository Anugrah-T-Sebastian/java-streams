import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreaPractice1 {
    public static class Employee {
        String name;
        int age;
        List<String> email;

        public Employee(String name, int age, List<String> email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getEmail() {
            return email;
        }

        public int getAge() {
            return this.age;
        }

        public void setEmail(List<String> email) {
            this.email = email;
        }


    }

    public static void main(String[] args) {

        final String commonString = "I am learning streams API in Java in Java Environment";
        final int[] commonNumArr = {1, 2 , 4, 7, 8, 6, 1, 0, 2, 3, 6, 20, 30, 100, 78, 69, 0};
        final String[] mixedList = {"abc", "123", "456", "xyz"};
        final List<Integer> numberList1 = Arrays.asList(1, 2, 3, 4);
        final List<Integer> numberList2 = Arrays.asList(6, 7, 5, 9, 10);
        final List<String> nameList = Arrays.asList("Alice", "Bob", "Anna", "Gary");
        final List<String> alphanumericList = Arrays.asList("a124", "1dsa78", "weq78985assd");

        final List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("ABC", 26, Arrays.asList("abc@abc.com")));
        employees.add(new Employee("PQR", 30, Arrays.asList("pqr@pqr.com")));
        employees.add(new Employee("XYZ", 35, Arrays.asList("xyz@xyz.com")));

        System.out.println();
        System.out.println();

        //1. Given a sentence find the word that has the highest length
        String maxLength = Arrays
                .stream(commonString.split(" "))
                .max(Comparator.comparing(s -> s.length()))
                .get();

        //2. Remove duplicates from the string and return in the same order
        String distinctCharString = Arrays
                .stream(commonString.split(""))
                .distinct()
                .collect(Collectors.joining());

        //3. Find the word that has the second highest length
        String secondLargestWord = Arrays
                .stream(commonString.split(" "))
                .sorted(Comparator.comparing((String s) -> s.length()).reversed())
                .skip(1)
                .findFirst()
                .get();

        //4. Find the 2nd highest word length in the given sentence
        Integer secondHighestWordLength = Arrays
                .stream(commonString.split(" "))
                .map(x -> x.length())
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .get();

        //5. Given a sentence, find the occurrence of each word
        Map<String, Long> wordCountMap = Arrays
                .stream(commonString.split(" "))
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        //6. Given a sentence, find the words with a specified number of vowels
        List<String> wordsWithVowels = Arrays
                .stream(commonString.split(" "))
                .filter((String s) -> s.replaceAll("[^aeiouAEIOU]", "").length() == 2)
                .toList();

        //7. Divide given integer list into lists of even and odd numbers
        List<List<Integer>> oddEvenList = IntStream
                .range(1, 10)
                .boxed()
                .collect(Collectors.groupingBy(x -> x % 2 == 0, Collectors.toList()))
                .entrySet()
                .stream()
                .map(x -> x.getValue())
                .toList();

        //8. Given a word, find the occurrence of each character
        Map<String, Long> characterOccurence = Arrays
                .stream(commonString.split(""))
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        //9. Arrange the numbers in Descending/Ascending Order
        List<Integer> sortedArray = Arrays
                .stream(commonNumArr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .toList();

        //10. Given an array, find the sum of unique elements
        Integer sumOfUniqueNumber = Arrays
                .stream(commonNumArr)
                .boxed()
                .distinct()
                .reduce(0, (a, b) -> a + b);
        
        //11. Given a string, find the first non-repeated character
        String firstNonRepeatedCharacter = Arrays
                .stream(commonString.split(""))
                .filter(x -> commonString.indexOf(x) == commonString.lastIndexOf(x))
                .findFirst()
                .get();

        //12. Given a string, find the first repeated character
        String firstRepeatCharacter = Arrays
                .stream(commonString.split(""))
                .filter(x -> commonString.indexOf(x) < commonString.lastIndexOf(x))
                .findFirst()
                .get();

        //13. Given an array of integers, group the numbers by the range
        TreeMap<Integer, List<Integer>> groupRangedElements = Arrays
                .stream(commonNumArr)
                .boxed()
                .collect(Collectors.groupingBy(
                        x -> x / 10,
                        () -> new TreeMap<Integer, List<Integer>>(),
                        Collectors.toList()));

        //14. Given a list of strings, create a list that contains only integers
        List<Integer> intergerList = Arrays
                .stream(mixedList)
                .filter(x -> {
                    try {
                        Integer.parseInt(x);
                        return true;
                    } catch (RuntimeException e) {
                        return false;
                    }
                })
                .map(Integer::parseInt)
                .toList();

        System.out.println("Integer list: " + intergerList);

        //15. Find the products of the first two elements in an array
        Integer productOfTwoElements = Arrays
                .stream(commonNumArr)
                .boxed()
                .limit(2)
                .reduce(1, (a, b) -> a * b);

        //16. Group /Pair anagrams from a list of Strings
        String[] anagramQuestion = {"pat", "tap", "pan", "nap", "team", "tree", "meat"};
        List<List<String>> groupedAnagrams = Arrays
                .stream(anagramQuestion)
                .collect(Collectors.groupingBy(
                        s -> {
                            char[] chars = s.toCharArray();
                            Arrays.sort(chars);
                            return new String(chars);
                        }
                ))
                .entrySet()
                .stream()
                .map(e -> e.getValue())
                .toList();
    }
}
