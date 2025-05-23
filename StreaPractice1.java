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
        String longestString = Arrays
            .stream(commonString.split(" "))
            .max(Comparator.comparing(s -> s.length()))
            .get();
        //2. Remove duplicates from the string and return in the same order
        List<String> distinctWords = Arrays
            .stream(commonString.split(" "))
            .distinct()
            .collect(Collectors.toList());

        //3. Find the word that has the second highest length
        String topTwoStrings = Arrays
            .stream(commonString.split(" "))
            .sorted(Comparator.comparing((String s) -> s.length()).reversed())
            .limit(2)
            .skip(1)
            .findFirst()
            .get();
        System.out.println("topTwoStrings: " + topTwoStrings);

        //4. Find the 2nd highest word length in the given sentence
        Integer secondLongestStringLength = Arrays
            .stream(commonString.split(" "))
            .map(s -> s.length())
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .findFirst()
            .get();

        //5. Given a sentence, find the occurrence of each word
        Map<String, Long> stringCountMap = Arrays
            .stream(commonString.split(" "))
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        //6. Given a sentence, find the words with a specified number of vowels
        List<String> filteredStrings = Arrays
            .stream(commonString.split(" "))
            .filter((String s) -> s.replaceAll("[^aeiouAEIOU]", "").length() == 2)
            .collect(Collectors.toList());

        //7. Divide given integer list into lists of even and odd numbers
        List<List<Integer>> sortedNumberGroups = Arrays
            .stream(commonNumArr)
            .boxed()
            .collect(Collectors.groupingBy(x -> x % 2, Collectors.toList()))
            .values()
            .stream()
            .collect(Collectors.toList());

        //8. Given a word, find the occurrence of each character
        Map<String, Long> characterFrequencyMap = Arrays
            .stream(commonString.split(""))
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        //9. Arrange the numbers in Descending/Ascending Order
        List<Integer> reversedNumberList = Arrays
            .stream(commonNumArr)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        //10. Given an array, find the sum of unique elements
        Integer sumOfDistinctNumbers = Arrays
            .stream(commonNumArr)
            .boxed()
            .distinct()
            .reduce(0, (a, b) -> a + b);

        //11. Given a string, find the first non-repeated character
        String firstUniqueChar = Arrays
            .stream(commonString.split(""))
            .filter(s -> commonString.indexOf(s) == commonString.lastIndexOf(s))
            .findFirst()
            .get();

        //12. Given a string, find the first repeated character
        String firstDuplicateChar = Arrays
            .stream(commonString.split(""))
            .filter(s -> commonString.indexOf(s) != commonString.lastIndexOf(s))
            .findFirst()
            .get();

        //13. Given an array of integers, group the numbers by the range
        Map<Integer, List<Integer>> groupedByHundreds = Arrays
            .stream(commonNumArr)
            .mapToObj(x -> x)
            .collect(Collectors.groupingBy(x -> x / 100, () -> new TreeMap<>(), Collectors.toList()));

        //14. Given a list of strings, create a list that contains only integers
        List<Integer> filteredIntegersList = Arrays
            .stream(mixedList)
            .filter(x -> {
                    try {
                        Integer.parseInt(x);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
            })
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        //15. Find the products of the first two elements in an array
        Integer limitedProduct = Arrays
            .stream(commonNumArr)
            .limit(2)
            .boxed()
            .reduce(1, (a, b) -> a * b);
        
        //16. Group /Pair anagrams from a list of Strings
        List<List<String>> anagramGroups = Arrays
            .stream(commonString.split(" "))
            .collect(Collectors.groupingBy(s -> {
                char[] charArr = s.toCharArray();
                Arrays.sort(charArr);
                return charArr.toString();
            }))
            .values()
            .stream()
            .collect(Collectors.toList());

        System.out.println("anagramGroups: " + anagramGroups);

        //17. Write a stream program to multiply alternative numbers in an array
        int productOfEvenIndexedElements = IntStream
            .range(0, commonNumArr.length)
            .filter(x -> x % 2 == 0)
            .map(x -> commonNumArr[x])
            .reduce(1, (a, b) -> a * b);

        //18. Write a program to multiply 1st and last element, 2nd and 2nd last element
        List<Integer> multipliedResults = IntStream
            .range(0, commonNumArr.length)
            .map(x -> commonNumArr[x] * commonNumArr[commonNumArr.length - x - 1])
            .boxed()
            .collect(Collectors.toList());

        //19. Write a stream program to move all zero’s to beginning of array
        List<Integer> zerosMovedToLeft = Arrays
            .stream(commonNumArr)
            .boxed()
            .collect(Collectors.groupingBy(x -> x != 0))
            .values()
            .stream()
            .flatMap(x -> x.stream())
            .collect(Collectors.toList());

        System.out.println("zerosMovedToLeft: " + zerosMovedToLeft);

        //20. In a given array of integers, return true if it contains distinct values
        Boolean distinctCheck = (numberList1
            .stream()
            .distinct()
            .count() == numberList1.size());

        //21. Given the string[] group the strings based on the middle character
        List<List<String>> groupedStringsByMidChar = Arrays
            .stream(commonString.split(" "))
            .collect(Collectors.groupingBy(x -> x.charAt(x.length() / 2)))
            .values()
            .stream()
            .collect(Collectors.toList());
            
        //22. Find the sum of all the elements in a list
        int totalSum = numberList1
            .stream()
            .mapToInt(x -> x)
            .sum();

        //23. Sort a list of strings in alphabetical order
        List<String> sortedNameList = nameList
            .stream()
            .sorted()
            .collect(Collectors.toList());

        //24. Convert a list of integers to a list of their squares
        List<Integer> squaredNumbers = numberList1
            .stream()
            .map(x -> x * x)
            .collect(Collectors.toList());

        //25. Find and print the distinct odd numbers
        numberList1
            .stream()
            .filter(x -> x % 2 != 0)
            .collect(Collectors.toList());

        //26. Find the union of two lists of integers
        List<Integer> combinedNumberList = Stream
            .concat(numberList1.stream(), numberList2.stream())
            .collect(Collectors.toList());

        //27 Find the kth smallest element in a list of integers
        Integer thirdSmallestInteger = numberList1
            .stream()
            .sorted()
            .skip(2)
            .findFirst()
            .get();

        //28. Remove all non-numeric characters from a list
        List<Integer> extractedIntegers = alphanumericList
            .stream()
            .map(s -> {
                String numericString = Arrays
                    .stream(s.split(""))
                    .filter(c -> {
                        try {
                            Integer.parseInt(c);
                            return true;
                        } catch (Exception e) {
                            return false;
                        }
                    })
                    .collect(Collectors.joining());
                return Integer.parseInt(numericString);
            })
            .collect(Collectors.toList());

        // extractedIntegers = alphanumericList
        //     .stream()
        //     .map(s -> Integer.parseInt(s.replace("^[0-9]+", "")))
        //     .collect(Collectors.toList());

        //29. Find and print strings containing only digits
        List<String> nonNumericStrings = Arrays
            .stream(mixedList)
            .filter(s -> s.replaceAll("^[0-9]+", "").length() == s.length())
            .collect(Collectors.toList());
        
        //30. Convert a list of strings to uppercase
        List<String> uppercaseNameList = nameList
            .stream()
            .map(s -> s.toUpperCase())
            .collect(Collectors.toList());

        //31. Calculate the average of all the numbers
        numberList1
            .stream()
            .mapToInt(x -> x)
            .average()
            .getAsDouble();

        //32. Find the intersection of two lists using Java streams
        List<Integer> intersectionList = numberList1
            .stream()
            .filter(x -> numberList2.contains(x))
            .collect(Collectors.toList());

        //33. Find the occurrence of domains using Java streams
        Map<String, Long> emailDomainCount = employees
            .stream()
            .map(employee -> employee.getEmail().get(0).substring(employee.getEmail().get(0).indexOf("@")))
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        //34. Generate the first 10 numbers of the Fibonacci Sequence
        List<Integer> fibonacciSequence = Stream
            .iterate(new int[] {1, 1}, arr -> new int[] {arr[1], arr[0] + arr[1]})
            .limit(10)
            .map(arr -> arr[0])
            .collect(Collectors.toList());

        //35. Convert list of integers to a list of their squares
        List<Integer> squaredNumberList = numberList1
            .stream()
            .map(x -> x * x)
            .collect(Collectors.toList());

        //36. Transform Person object stream into a single string
        String employeeNamesJoined = employees
            .stream()
            .map(o -> o.getName().toUpperCase())
            .collect(Collectors.joining("|"));

        //37 Group list of strings by their first character and count the number of strings
        Map<Character, List<String>> groupedNamesByFirstCharacter = nameList
            .stream()
            .collect(Collectors.groupingBy(x -> x.charAt(0)));
            
        //38. Convert a list to a map
        numberList1
            .stream()
            .map(x-> {
                System.out.println("TEST:3");
                return x;
            });
        List<Integer> test = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        test
            .stream()
            .filter(x -> {
                System.out.println("TEST:0");
                return x % 5 == 0;
            })
            .map(x -> {
                System.out.println("TEST: 1");
                return x; // Ensure the lambda returns a value
            })
            .findFirst();
        //39 Multiply array elements
        //40. Can we reuse a stream in Java?
        //41 Convert a list of string to uppercase and then concatenate
        //41. Difference between map and flatMap
        //43. Concatenate 2 streams
        //44. Given a person list, fetch the list of name
        // -whose age is greater than 30
        // -name should be unique
        // -names should be in sorted order
        //45. Print distinct numbers which starts with "1" in descending order
        //46. Return comparison of a Person object based on first name and then last name
    }
}
