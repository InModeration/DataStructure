package clone;

public class Test {
    public static void main(String[] args){
        //ShadowClone
        /*
         *  result:
         *  before clone:
         *  the value of ob_1 : 10
         *  ob_1's ObjectA's value :100
         *  ------------------
         *  after clone:
         *  the value of ob_1 :10
         *  ob_1's ObjectA's value :100
         *  ------------------
         *  the value of ob_2 :20
         *  ob_2's ObjectA's value :200
         *
         *  ob_2是ob_1的clone对象：
         *  对于该对象的int域，实现了真正的clone，即内存重新分配了地址给ob_2的int域
         *  当ob_2的int域改变时，ob_1的int域不会改变
         *
         *  对于该对象的ObjectA域，并未实现真正的clone
         *  当ob_2的Object域发生改变时，ob_1的Object也发生了相同的改变
         *  两个对象的Object域在内存中指向同一地址
         */
        ObjectB ob_1 = new ObjectB(10);
        System.out.println("before clone:");
        System.out.println("the value of ob_1 : " + ob_1.value);
        System.out.println("ob_1's ObjectA's value :" + ob_1.oa);
        ObjectB ob_2 = (ObjectB)ob_1.clone();
        ob_2.oa.doubleValue();
        ob_2.setValue(20);
        System.out.println("------------------");
        System.out.println("after clone:");
        System.out.println("the value of ob_1 :" + ob_1.value);
        System.out.println("ob_1's ObjectA's value :" + ob_1.oa);
        System.out.println("------------------");
        System.out.println("the value of ob_2 :" + ob_2.value);
        System.out.println("ob_2's ObjectA's value :" + ob_2.oa);
        System.out.println();

        //Deep Clone
        /*
         *  before clone:
         *  the value of ob :1000
         *  ob's ObjectC's value ：500
         *  ------------------
         *  after clone:
         *  the value of ob:1000
         *  ob's ObjectC's value :500
         *  ------------------
         *  the value of ob_clone :2000
         *  ob_clone's ObjectC's value:1000
         *
         *  对于ObjectB中的ObjectC域，其实现了Cloneable接口
         *  并且在ObjectB中的clone方法中，给出了ObjectC的clone表达式
         *  ob与ob_clone两个对象中的ObjectC域为内存中的不同地址
         *  互不影响
         */
        ObjectB ob = new ObjectB(1000);
        System.out.println("before clone:");
        System.out.println("the value of ob :" + ob.value);
        System.out.println("ob's ObjectC's value ：" + ob.oc);
        System.out.println("------------------");
        ObjectB ob_clone = (ObjectB)ob.clone();
        ob_clone.setValue(2000);
        ob_clone.oc.doubleValue();
        System.out.println("after clone:");
        System.out.println("the value of ob:" + ob.value);
        System.out.println("ob's ObjectC's value :" + ob.oc);
        System.out.println("------------------");
        System.out.println("the value of ob_clone :" + ob_clone.value);
        System.out.println("ob_clone's ObjectC's value:" + ob_clone.oc);

        /*
         * 对于类中的自定义类域，可以根据需要确定是否需要进行深度克隆
         * 深克隆和浅克隆结合可以解决一些问题
         */
    }
}
