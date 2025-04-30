import java.util.Arrays;
import java.util.List;

public class SquareOfNumber {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 55, 99, 465);

        double avg = list.stream()
                .map(e -> e * e)
                .mapToInt(e -> e)
                .filter(e -> e > 100)
                .average()
                .getAsDouble();

        System.out.println("Average of square of elements greater than 100:" + avg);
    }
}
