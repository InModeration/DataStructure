package creatingStreams;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    different ways to create streams
 */
public class CreatingStreams {

    /*
    to show the stream
     */
    public static <T> void show(String title, Stream<T> stream, String info)
    {
        int SIZE = 10;      //to limit the size of stream
        //limit(n) -- 新建一个流，保留当前流的前n个元素
        List<T> source = stream.limit(SIZE).collect(Collectors.toList());
        System.out.println(title +  "(" + info + ")  : ");
        for(int i = 0; i < source.size(); i++)
        {
            if(i > 0)
                System.out.print(", ");
            if(i < source.size())
                System.out.print(source.get(i));
            else
                System.out.print(".....");
        }
        System.out.println();
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) throws IOException
    {
        //to make a file and input some informations then get its path
        File file = new File("E:\\学习\\java\\creatingStream.txt");
        if(!file.exists())
            file.createNewFile();
        Path path = Paths.get(file.getPath());

        /*
         *  static <T> Stream<T> of(T ... values)
         *  可以将数组转换为流对象
         *  of方法具有可变长参数，则可以构建任意长度的流
         */
        String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        //split方法可以将字符串分割为多个字符串构成的数组,然后将所得的数组作为结果返回
        Stream<String> words = Stream.of(content.split("\\PL+"));
        show("words", words, "创建文件再获得字符串数组");
        words.close();                                                    //Remember to close the Stream!

        /*
         * 直接传入数组元素获得流
         */
        Stream<String> songs = Stream.of("last", "night", "I", "lost", "all", "my", "patience");
        show("songs", songs, "直接传入数组元素");
        songs.close();

        /*
         *  static <T> Stream<T> empty()
         *  产生并返回一个不含任何元素的流对象
         */
        Stream<Object> empty = Stream.empty();
        show("empty", empty, "利用Stream的静态方法创建一个不含任何元素的流");
        empty.close();

        /*
         *  static <T> Stream<T> generate(Supplier<T> s)
         *  创建一个无限流：通过反复调用s函数实现的
         */
        Stream<String> generate = Stream.generate(() -> "pear and xx");
        show("generate", generate, "调用Stream的静态方法generate创建无限流对象");
        generate.close();

        Stream<Double> anotherGenerate = Stream.generate(Math::random);
        show("anotherGenerate", anotherGenerate, "调用Stream的静态方法generate创建无限流");
        anotherGenerate.close();

        /*
         *  static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
         *  创建一个无限流：元素包含种子（seed）、在种子上调用f函数产生的值、所得值上再调用f产生的值····
         */
        Stream<Integer> iterate = Stream.iterate(1, (seed) -> seed * 2);
        show("iterate", iterate, "调用Stream的静态方法iterate创建无限流对象");
        iterate.close();

        /*
         *  Class Pattern
         *  Stream<String> splitAsStream(CharSequence input)
         *  产生并返回一个流，其中的元素是输入中由该模式界定的部分
         *  可以按照某个正则表达式来分割一个CharSequence对象
         */
        Stream<String> anotherWords = Pattern.compile("\\PL+").splitAsStream(content);
        show("anotherWords", anotherWords, "Pattern类的splitAsStream方法产生的流");

        /*
         *  Class Files
         *  static Stream<String> lines(Path path)
         *  static Stream<String> lines(Path path, Charset cs)
         *  产生并返回一个流对象，其中的对象是指定文件中的行
         *  该文件的字符集为UTF8 或者指定的字符集
         */
        Stream<String> lines = Files.lines(path);
        show("lines", lines, "Files类的静态方法产生行为对象的流");
        lines.close();

    }
}
