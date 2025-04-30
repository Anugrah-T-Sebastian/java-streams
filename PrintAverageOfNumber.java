import java.util.Arrays;
import java.util.List;

public class PrintAverageOfNumber {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 31, 4);
        double avg =  list.stream().mapToInt(e -> e).average().getAsDouble();
        System.out.println("Average is: " + avg);
    }
}
