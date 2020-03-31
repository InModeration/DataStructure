package convertingStreams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertStream {

    /*
     *  some ways to convert the stream
     */
    public  static void main(String[] args) {
        int maxSize = 10;
        /*
         *  Stream<T> distinct()
         *  产生并返回一个不含重复元素的新流对象
         */
        Stream<Integer> beforeDistinct = Stream.of(1, 1, 2, 2, 3, 5);
        Stream<Integer> afterDistinct = beforeDistinct.distinct();
        //show("Before Distinct", beforeDistinct);
        show("After Distinct", afterDistinct);

        Stream<String> beforeDistinct2 = Stream.of("Mart", "Mary", "Mart");
       //show("Before Dinstinct", beforeDistinct);
        Stream<String> afterDistinct2 = beforeDistinct2.distinct();
        show("After Distinct", afterDistinct2);

        /*
         *  Stream<T> sorted()
         *  Stream<T> sorted(Comparator<? super T> comparator)
         *  产生并返回一个流，其元素是当前流中按照一定顺序排列的结果
         *  第一个方法要求传入的元素实现了Comparable接口的类的实例
         *  eg. Double
         */
        Stream<Double> beforeSorted = Stream.generate(Math::random).limit(maxSize);
        //show("Before Sorted", beforeSorted);
        Stream<Double> afterSorted = beforeSorted.sorted();
        show("After Sorted", afterSorted);

        Stream<Integer> beforeSorted2 = Stream.of(1, 6, 8, 3, 2);
        //show("Before Sorted", beforeSorted);
        Stream<Integer> afterSorted2 = beforeSorted2.sorted();
        show("After Sorted", afterSorted2);

        /*
         *  Stream<T> peek(Consumer<? super T> action)
         *  产生一个流，其中元素与与当前流的元素同
         *  在获取每个元素时，将其传递给action
         */
        List<Double> list = new ArrayList<>();
        Object[] actions = Stream.iterate(1.0, (n) -> n * 2.0).peek(e -> list.add(e))
                .limit(maxSize).toArray();
        Iterator it = list.listIterator();
        while(it.hasNext())
            System.out.println("Element : " + it.next());
    }

    private static <T> void show(String title ,Stream<T> stream)
    {
        List<T> source = stream.collect(Collectors.toList());
        System.out.println(title + " :  ");
        for(int i = 0; i < source.size(); i++)
        {
            if(i > 0)
                System.out.print(", ");
            System.out.print(source.get(i));
        }
        System.out.println();
        stream.close();
    }
}
