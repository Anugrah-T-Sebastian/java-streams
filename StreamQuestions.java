import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamQuestions {

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
       


        //1. Given a sentence find the word that has the highest length
        String str = "I am evil";
        String maxLength =  Arrays
                                .stream(str.split(" "))
                                .max(Comparator.comparing(s -> s.length()))
                                .orElseThrow(() -> new RuntimeException("Error in question 1"));
        System.out.println("Longest word: " + maxLength);

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
        String[] strings = {"ewe", "jji", "jhj", "kwk", "aha"};
        List<List<String>> groupedStringsByMiddleChar = Arrays
            .stream(strings)
            .collect(Collectors.groupingBy((String x) -> x.charAt(x.length() / 2)))
            .values()
            .stream()
            .collect(Collectors.toList());

        System.err.println("Strings grouped by middle character: " + groupedStringsByMiddleChar);

        //22. Find the sum of all the elements in a list
        Integer aggregateSum = Arrays
            .stream(commonNumArr)
            .reduce(0, (a, b) -> a + b);
        System.out.println("Agregate sum: "+ aggregateSum);

        //23. Sort a list of strings in alphabetical order
        List<String> sortedStringArray = Arrays
            .stream(commonString.split(" "))
            .sorted()
            .collect(Collectors.toList());

        System.out.println("Sorted string array: " + sortedStringArray);

        //24. Convert a list of integers to a list of their squares
        List<Integer> squaredNumber = Arrays
            .stream(commonNumArr)
            .boxed()
            .map(i -> i * i)
            .collect(Collectors.toList());
        
        System.err.println("Squared number: " + squaredNumber);

        //25. Find and print the distinct odd numbers
        List<Integer> oddDistinctNumbers = Arrays
            .stream(commonNumArr)
            .boxed()
            .filter(e -> e%2 != 0)
            .distinct()
            .collect(Collectors.toList());

        System.out.println("Odd distinct number: " + oddDistinctNumbers);

        //26. Find the union of two lists of integers
        List<Integer> listUnion = Stream
            .concat(numberList1.stream(),numberList2.stream())
            .collect(Collectors.toList());

        System.out.println("Union of lists: "+ listUnion);

        //27 Find the kth smallest element in a list of integers
        long kth = 3;
        Integer thirdSmallestNumber = numberList1
            .stream()
            .sorted()
            .skip(kth)
            .findFirst()
            .get();
        System.out.println("Third smallest number: "+ thirdSmallestNumber);

        //28. Remove all non-numeric characters from a list
        List<Integer> numericValuesList = alphanumericList
            .stream()
            .map(e -> {
                String numericValue = Arrays
                        .stream(e.split(""))
                        .filter(i -> {
                            try {
                                Integer.parseInt(i);
                                return true;
                            }
                            catch(Exception ex) {
                                return false;
                            }
                        })
                        .collect(Collectors.joining());
                return Integer.parseInt(numericValue);
            })
            .collect(Collectors.toList());
        System.out.println("Numeric characters from list: "+ numericValuesList);
        
        numericValuesList = alphanumericList
            .stream()
            .map(e -> Integer.parseInt(e.replaceAll("[^0-9]+", "")))
            .collect(Collectors.toList());
        System.out.println("Numeric characters from list: "+ numericValuesList);

        //29. Find and print strings containing only digits
        List<Integer> filteredNumericList = Arrays
            .stream(mixedList)
            .filter(e -> e.replaceAll("[^0-9]+", "").length() == e.length())
            .map(e -> Integer.parseInt(e))
            .collect(Collectors.toList());

        System.out.println("String containing only digits: "+ filteredNumericList);
        
        filteredNumericList = Arrays
            .stream(mixedList)
            .filter(e -> e.matches("[0-9]+"))
            .map(e -> Integer.parseInt(e))
            .collect(Collectors.toList());
        System.out.println("String containing only digits: "+ filteredNumericList);

        //30. Convert a list of strings to uppercase
        List<String> upperCaseStringsList = Arrays
            .stream(commonString.split(" "))
            .map((String s) -> s.toUpperCase(Locale.US))
            .collect(Collectors.toList());
        System.out.println("Upper case word: "+ upperCaseStringsList);

        //31. Calculate the average of all the numbers
        double asDouble = Arrays
            .stream(commonNumArr)
            .average()
            .getAsDouble();
        System.out.println("Average: "+ asDouble);

        //32. Find the intersection of two lists using Java streams
        List<Integer> commonNumbersList = numberList1
            .stream()
            .filter(x -> numberList2.contains(x))
            .collect(Collectors.toList());
        System.out.println("Intersectiom of lists: "+ commonNumbersList);

        //33. Find the occurrence of domains using Java streams
        Map<String, Long> emailDomainCount = employees
            .stream()
            .map(e -> e.getEmail().get(0).substring(e.getEmail().get(0).indexOf("@")))
            .collect(Collectors.groupingBy(d -> d, Collectors.counting()));
        System.out.println("Domain count: "+ emailDomainCount);

        //34. Generate the first 10 numbers of the Fibonacci Sequence
        List<Integer> fibonacciSequence = Stream
            .iterate(new int[] {0, 1}, f -> new int[] {f[1], f[0] + f[1]})
            .limit(10)
            .map(f -> f[0])
            .collect(Collectors.toList());
        System.out.println("Fibonacci: " + fibonacciSequence);

        //35. Convert list of integers to a list of their squares
        List<Integer> squaredNumberList = numberList1
            .stream()
            .map(x -> x * x)
            .collect(Collectors.toList());
        System.out.println("Square elements of the list: " + squaredNumberList);

        //36. Transform Person object stream into a single string
        String employeeNamesString = employees
            .stream()
            .map(person -> person.getName().toUpperCase())
            .collect(Collectors.joining("|"))
            .toString();
        System.out.println("Employee names string: " + employeeNamesString);

        //37 Group list of strings by their first character and count the number of strings
        Map<Character, Long> groupedCountByFirstChar = Arrays
            .stream(commonString.split(" "))
            .collect(Collectors.groupingBy(s -> s.charAt(0), Collectors.counting()));
        System.out.println("Grouped by first character: " + groupedCountByFirstChar);

        //38. Convert a list to a map
        Map<String, List<StreamQuestions.Employee>> groupedEmployees = employees
            .stream()
            .collect(Collectors.groupingBy(e -> e.getName()));
        System.out.println("Grouped employees: " + groupedEmployees);

        //39 Multiply array elements
        int totalProduct = Arrays
            .stream(commonNumArr)
            .reduce(1, (a, b) -> a * b);
        System.out.println("Total product: " + totalProduct);
        
        //40. Can we reuse a stream in Java?
        Supplier<Stream<String>> nameStream = () -> nameList.stream();
        nameStream.get().forEach(System.out::println);
        nameStream.get().count();

        //41 Convert a list of string to uppercase and then concatenate
        String uppercaseNames = nameList
            .stream()
            .map(e -> e.toUpperCase())
            .collect(Collectors.joining(" "));
        System.out.println("Upper case names: " + uppercaseNames);

        uppercaseNames = nameList
            .stream()
            .map(e -> e.toUpperCase())
            .reduce((s1, s2) -> s1 + " " + s2)
            .orElse("");
        System.out.println("Upper case names: " + uppercaseNames);

        //41. Difference between map and flatMap
        //map() transforms each element of the stream into another element. It is a one-to-one mapping
        List<List<String>> mapEmails = employees
            .stream()
            .map(e -> e.getEmail())
            .collect(Collectors.toList());
        
        //flatMap() tranforms + flattens the resulting streams into one stream, usually when dealing with collections of collections
        List<String> flattenedEmails = employees
            .stream()
            .flatMap(e -> e.getEmail().stream())
            .collect(Collectors.toList());
        System.out.println("Mapped emails:" + mapEmails);
        System.out.println("Flat-Mapped emails:" + flattenedEmails);

        //43. Concatenate 2 streams
        Stream<String> stream1 = Stream.of("Java", "Python");
        Stream<String> stream2 = Stream.of("C++", "C#");
        List<String> combinedStream = Stream
            .concat(stream1, stream2)
            .collect(Collectors.toList());
        
        //44. Given a person list, fetch the list of name
        // -whose age is greater than 30
        // -name should be unique
        // -names should be in sorted order
        List<StreamQuestions.Employee> filterdEmployees = employees
            .stream()
            .filter(e -> e.getAge() >= 30)
            .distinct()
            .sorted(Comparator.comparing(e -> e.getName()))
            .collect(Collectors.toList());
        System.out.println("Employee with age >= 30: " + filterdEmployees);
    }
}
