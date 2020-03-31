package convertingStreams;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class ReductStreams {
    /*
     *  流的约简
     *  一种终结操作(terminal operation)
     *  将流约简为可以在程序中使用的非流值
     */
    public static void main(String[] args) throws IOException
    {
        /*
         * count: return the number of elements of the stream
         */
        File file =  new File("E:\\学习\\java\\creatingStream.txt");
        Path path = Paths.get(file.getPath());
        String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        // convert the String to the Stream
        Stream<String> txt = Stream.of(content.split("\\PL+"));
        System.out.println("The number of elements is " + txt.count());
        txt.close();

        /*
         *  Optional<T> max(min)(Comparator<? super T> comparator)
         *  产生这个流的最大（小）元素，使用由比较器给定的比较方式
         *  若流为空，则返回一个空的Optional对象
         */
        Optional<Integer> max = Stream.of(6, 8, 10, 1).max(Integer::compareTo);
        System.out.println("max = " + max.orElse(1));

        /*
         *  Optional<T> findFirst()
         *  产生流中第一个元素
         *  Optional<T> findAny()
         *  产生流中的任意一个元素
         */
        Optional<String> findFirst = Stream.of("xxusedtobeahero").parallel().filter(string -> string.startsWith("h"))
                .findFirst();
        System.out.println(findFirst.orElse("no such"));
        //orElse -- 若不存在Optional<T>的返回值，返回一个指定的变量


    }
}
