import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamQuestions {
    public static void main(String[] args) {

        String commonString = "I am learning streams API in Java in Java Environment";
        int[] commonNumArr = {1, 2 , 4, 7, 8, 6, 1, 0, 2, 3, 6, 20, 30, 100, 78, 69, 0};


        //1. Given a sentence find the word that has the highest length
        String str = "I am evil";
        String maxLength =  Arrays
                                .stream(str.split(" "))
                                .max(Comparator.comparing(s -> s.length()))
                                .orElseThrow(() -> new RuntimeException("Error in question 1"));

        //2. Remove duplicates from the string and return in the same order
        String str2 = "dajnjadskasd";
        String distinctCharString = Arrays
            .stream(str2.split(""))
            .distinct()
            .collect(Collectors.joining());
        System.out.println(distinctCharString);

        //3. Find the word that has the second highest length
        int n = 2;
        String str3 = "I am learning streams API in Java";
        String nthLargestWord = Arrays
        .stream(str3.split(" "))
        .sorted(Comparator.comparing((String s) -> s.length()).reversed())
        .skip(n - 1)
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Problem in question 3"));
        System.out.println("Nth largest word: "+nthLargestWord);
        
        //4. Find the 2nd highest word length in the given sentence
        String str4 = "I am learning streams API in Java in Java Environment";
        int secondHighestWordLength = Arrays
            .stream(str4.split(" "))
            .map(x -> x.length())
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .findFirst()
            .get();

        System.out.println("Second longest word length:" + secondHighestWordLength);

        //5. Given a sentence, find the occurrence of each word
        Map<String, Long> ans = Arrays
            .stream(str4.split(" "))
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println("Word count: " + ans);

        //6. Given a sentence, find the words with a specified number of vowels
        List<String> wordsWithTwoVowels = Arrays
            .stream(commonString.split(" "))
            .filter((String s) -> s.replaceAll("[^aeiouAEIOU]", "").length() == 2)
            .collect(Collectors.toList());
        System.out.println("Words with 2 vowels:" + wordsWithTwoVowels);

        //7. Divide given integer list into lists of even and odd numbers
        List<List<Integer>> checkedList = IntStream
            .rangeClosed(1, 10)
            .boxed()
            .collect(Collectors.groupingBy(x -> x % 2 == 0, Collectors.toList())) //<- Map<Boolean, List<Integer>>
            .entrySet()
            .stream()
            .map(x->x.getValue())
            .collect(Collectors.toList());
        System.out.println("Odd-Even lists:" + checkedList);

        //8. Given a word, find the occurrence of each character
        String word = "Hello";
        Map<String, Long> wordCount = Arrays
            .stream(word.split(""))
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println("Character count: " + wordCount);

        //9. Arrange the numbers in Descending/Ascending Order
        
        List<Integer> sortedArray = Arrays
            .stream(commonNumArr)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        System.out.println("Sorted array: " + sortedArray);

        //10. Given an array, find the sum of unique elements
        int sumUniqueElement = Arrays
            .stream(commonNumArr)
            .distinct()
            .reduce(0, (a, e ) -> a + e);
        System.out.println("Sum of unique elements: "+ sumUniqueElement);

        //11. Given a string, find the first non-repeated character
        String firstNonRepeatedCharacter = Arrays
            .stream(commonString.split(""))
            .filter(c -> commonString.indexOf(c) == commonString.lastIndexOf(c))
            .findFirst()
            .get();
        System.out.println("First non-repeated character: "+ firstNonRepeatedCharacter);

        firstNonRepeatedCharacter = Arrays
            .stream(commonString.split(""))
            // .mapToObj(c -> (char)c)
            .collect(Collectors.groupingBy(x -> x, LinkedHashMap::new, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(e -> e.getValue() == 1)
            .map(e -> e.getKey())
            .findFirst()
            .get();
        System.out.println("First non-repeated character: "+ firstNonRepeatedCharacter);
        
        //12. Given a string, find the first repeated character
        String firstRepeatingCharacter = Arrays
        .stream(commonString.split(""))
        .filter(c -> commonString.indexOf(c) != commonString.lastIndexOf(c))
        .findFirst()
        .get();
        System.out.println("First repeated character: "+ firstRepeatingCharacter);

        //13. Given an array of integers, group the numbers by the range
        Map<Integer, List<Integer>> rangedElements = Arrays
            .stream(commonNumArr)
            .boxed()
            .collect(Collectors.groupingBy(
                                        x -> x / 10 * 10,
                                        () -> new TreeMap<Integer, List<Integer>>(),
                                        Collectors.toList()));

        System.out.println("Ranged elements: " + rangedElements);


        //14. Given a list of strings, create a list that contains only integers
        String[] mixedList = {"abc", "123", "456", "xyz"};
        List<Integer> integerList = Arrays
            .stream(mixedList)
            .filter(s -> {
                try {
                    Integer.parseInt(s);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            })
            .map(Integer::parseInt)
            .toList();

        System.out.println("Integer list: "+ integerList);

        //15. Find the products of the first two elements in an array
        Integer reduceProduct = Arrays
            .stream(commonNumArr)
            .boxed()
            .limit(2)
            .reduce(1, (a, b) -> a * b);
        
        System.out.println("Product of first 2 element: " + reduceProduct);

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

        System.out.println("Grouped anagrams: " + groupedAnagrams);

        //17. Write a stream program to multiply alternative numbers in an array
        int productAlternateNumber = IntStream
                .range(0, commonNumArr.length)
                .filter(i -> i % 2 == 0)
                .map(i -> commonNumArr[i])
                .reduce(1, (x, y) -> x*y);
        System.out.println("Product of alternate numbers: " + productAlternateNumber);

        //18. Write a program to multiply 1st and last element, 2nd and 2nd last element
        List<Integer> mirrorProductList = IntStream
                .range(0, (commonNumArr.length + 1) / 2)
                .map(i -> commonNumArr[i] * commonNumArr[commonNumArr.length - i - 1])
                .boxed()
                .collect(Collectors.toList());

        System.out.println("Product of mirror elements: " + mirrorProductList);

        //19. Write a stream program to move all zero’s to beginning of array
        List<Integer> zerosFirst = Arrays
            .stream(commonNumArr)
            .boxed()
            .collect(Collectors.groupingBy(x -> x == 0))
            .values()
            .stream()
            .sorted(Comparator.comparing(list -> !list.get(0).equals(0)))
            .flatMap(e -> e.stream())
            .collect(Collectors.toList());

        System.out.println("Zeros First: " + zerosFirst);
        
        zerosFirst = Arrays
            .stream(commonNumArr)
            .boxed()
            .collect(Collectors.groupingBy(x -> x == 0))
            .entrySet()
            .stream()
            .sorted(Comparator.comparing(e -> !e.getKey()))
            .flatMap(e -> e.getValue().stream())
            .collect(Collectors.toList());
        
        zerosFirst = Arrays
            .stream(commonNumArr)
            .boxed()
            .filter(x -> x == 0)
            .collect(Collectors.toList());

        zerosFirst
                .addAll(
                    Arrays
                        .stream(commonNumArr)
                        .boxed()
                        .filter(x -> x != 0)
                        .collect(Collectors.toList())
                );
        System.out.println("Zeros First: " + zerosFirst);

        //20. In a given array of integers, return true if it contains distinct values
        boolean hasDistinctValues = Arrays
            .stream(commonNumArr)
            .boxed()
            .collect(Collectors.toSet())
            .size() == commonNumArr.length;
        System.out.println("Contains distinct values: " + hasDistinctValues);

        //21. Given the string[] group the strings based on the middle character
    }
}
