package optionalTest;

import java.util.Optional;

public class Test {
    public static void main(String[] args)
    {
        //创建Optional的实例
        User user = null;
        String mail = null;
        //Optional<User> emptyUser = Optional.empty();
        //emptyUser.get();
        //NoSuchElementException

        //Optional<User> opt_1 = Optional.of(user);
        //opt_1.get();
        //NullPointerException

        //Optional.of -- 当传入的对象为null时，无论是否使用get方法都会报错
        //Optional.ofNullable -- 当传入对象为null时，不会报错，当get方法时报错
        //所以在不清楚传入的对象是否为空时，使用ofNullable来导入。
        Optional<User> opt_1 = Optional.ofNullable(user);
        user = new User("531449146@qq.com","38");
        Optional<User> opt_2 = Optional.ofNullable(user);
        Optional<String> opt_3 = Optional.ofNullable(mail);
        System.out.println("is opt_2 empty?   " + (opt_2.isPresent()? "no, and the mail of the user is " + opt_2.get().getMail() : "yes"));
        System.out.println("is opt_3 empty?   " + (opt_3.isPresent()? "no" + opt_3.get() : "yes"));

        //ifPresent -- do something when the optional object is not empty
        opt_2.ifPresent(u -> u.setMail("55555@qq.com"));
        System.out.println(opt_2.get().getMail());

        /*
         *  method of get values -- Optional对象的方法，返回其包装的对象或者是其他
         *  orElse          --  对象为空时返回传入的默认值
         *  orElseGet   （need a supplier）  --   对象为空时执行对应的supplier函数接口，并返回其执行结果
         *  orElseThrow
         */
        //当Optional为空时，orElse与orElseGet都会调用里面的内容
        User user_1 = opt_1.orElse(User.creatNewUser());
        User user_2 = opt_1.orElseGet(() -> User.creatNewUser());

        //当Optional不空时，orElseGet同样会调用lambda表达式
        User user_3 = opt_2.orElse(User.creatNewUser());
        User user_4 = opt_2.orElseGet(() -> User.creatNewUser());

        /*
         *  转换Optional中对象的方法
         *  map
         *  flatMap
         *  filter
         */

        //map方法
        String e_mail = opt_1.map(u -> u.getMail()).orElse("531449146@163.com");
        System.out.println(e_mail);
        //flatMap方法,解除Optional对象包装的对象
        String e_mail_2 = opt_2.flatMap(u -> u.getMailOptional()).orElse("531449146@163.com");
        System.out.println(e_mail_2);
        //filter方法，根据指定条件检测过滤Optional对象
        //if true -- return the Optional object
        //if false -- return a null Optional object
        Optional<User> test_opt_1 = opt_1.filter(u -> u.getMail() != null && u.getMail().contains("@"));
        System.out.println("is opt_1 legal? " + (test_opt_1.isPresent() ? "yes" : "no"));

        Optional<User> test_opt_2 = opt_2.filter(u -> u.getMail() != null && u.getMail().contains("@"));
        System.out.println("is opt_2 legal? " + (test_opt_2.isPresent() ? "yes,and it mail is  " + test_opt_2.get().getMail() : "no"));
    }
}
