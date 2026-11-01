package Samsung;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void test(List<Integer> list) {
        list.add(1);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        test(list);
        System.out.println(list);
    }
}
