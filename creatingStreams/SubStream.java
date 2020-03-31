package creatingStreams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 *  抽取子流
 *  连接流
 */
public class SubStream {

    public static void main(String[] args) {
        /*
         *  抽取子流
         */
        //limit(n) -- 返回一个新流，当前流的前n个元素作为其中的元素(若当前流中的元素少于n个,则在流结束时结束)
        int maxSize = 20;
        Stream<Integer> limitTest = Stream.iterate(1, (n) -> n * 2).limit(maxSize);
        show("limit", limitTest);
        limitTest.close();

        //skip(n) -- 跳过前n个元素，利用剩余的元素创建一个新的流
       Stream<Double> skipTest = Stream.of(1.0, 2.0, 3.0, 7.0).skip(1);
       show("skip", skipTest);
       skipTest.close();

       /*
        *   连接流
        *   利用静态的Stream.concat方法对两个子流进行合并
        *   显然，传入的第一个子流不能是无限流，否则永远无法连接上第二个流
        */
       Stream<String> firstElement = Stream.of("hello");
       Stream<String> secondElement = Stream.of("world");
       Stream<String> concatStream = Stream.concat(firstElement, secondElement);
       show("concat", concatStream);
       concatStream.close();

    }

    private static <T> void show(String title, Stream<T> stream)
    {
        //convert the stream to the a list
        List<T> source = stream.collect(Collectors.toList());
        System.out.println(title + "  : ");
        for (int i = 0; i < source.size(); i++)
        {
            if (i > 0)
                System.out.print(", ");
            if (i < source.size())
                 System.out.print(source.get(i));
        }
        System.out.println();
    }
}
