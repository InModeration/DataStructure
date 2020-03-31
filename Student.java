package DataStructure;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Student{
    private String name;
    private Double Chin;
    private Double Math;
    private Double Engl;

    public Student(String name, Double Chin, Double Math, Double Engl)
    {
        this.name = name;
        this.Chin = Chin;
        this.Math = Math;
        this.Engl = Engl;
    }
    //set name
    public void setName(String name)
    {
        this.name = name;
    }

    //set chinese grade
    public void setChin(Double Chin)
    {
        this.Chin = Chin;
    }

    //set math grade
    public void setMath(Double Math)
    {
        this.Math = Math;
    }

    //set english grade
    public void setEngl(Double Engl)
    {
        this.Engl = Engl;
    }

    //get the total grade
    public Double getTotal()
    {
        return Chin + Math + Engl;
    }

    //get the information of a student
    public String getInfo()
    {
        return "学生姓名：" + this.name + "， 语文成绩： " + this.Chin + "， 数学成绩： "
        + this.Math + "， 英语成绩： " + this.Engl + "， 总分： " + this.getTotal();
    }

    /*
     *  two static methods
     */
    //to check the input of grade
    private static Double geGrade(Double grade, Scanner gradeScan) {
        while (gradeScan.hasNext()){
            if (gradeScan.hasNextDouble()){
                grade = gradeScan.nextDouble();
                break;
            }
            else {
                System.out.println("输入有误，请重新输入：");
                gradeScan = new Scanner(System.in);
            }
        }
        return grade;
    }
    //to write the information of student to the file
    private static void writeFile(String fileName, String info) throws IOException {
        FileWriter content = null;
        BufferedWriter writer = null;
        try{
            //catch IOException
            content = new FileWriter(fileName, true);
            writer = new BufferedWriter(content);
            writer.write(info);
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            writer.newLine();
            writer.close();
            content.close();
        }
    }

    //main
    public static void main(String[] args) throws IOException {
        //define an ArrayList to store students
        ArrayList<Student> students = new ArrayList<>();
        //the number of students
        int num = 0;
        //define the filename
        String fileName = null;

        //读取学生总数
        System.out.println("请输入学生总数：");
        Scanner numScan = new Scanner(System.in);
        while(numScan.hasNext()) {
            if (numScan.hasNextInt()) {
                num = numScan.nextInt();
                if (num < 0){
                    System.out.println("输入有误，请重新输入：");
                    numScan = new Scanner(System.in);
                }
                else if (num == 0){
                    System.out.println("无学生");
                    return;
                }
                else break;
            } else {
                System.out.println("输入有误，请重新输入：");
                numScan = new Scanner(System.in);
            }
        }
        System.out.println("**请输入这" + num + "个同学的信息**");
        System.out.println();
        for (int i = 1; i <= num; i++)
        {
            String name = null;
            Double Chin = null;
            Double Math = null;
            Double Engl = null;

            System.out.println("请输入第" + i + "个同学的姓名");
            Scanner nameScan = new Scanner(System.in);
            if (nameScan.hasNext())
                name = nameScan.next();

            System.out.println("请输入第" + i + "个同学的语文成绩");
            Scanner chinScan = new Scanner(System.in);
            Chin = geGrade(Chin, chinScan);

            System.out.println("请输入第" + i + "个同学的数学成绩");
            Scanner mathScan = new Scanner(System.in);
            Math = geGrade(Math, mathScan);

            System.out.println("请输入第" + i + "个同学的英语成绩");
            Scanner englScan = new Scanner(System.in);
            Engl = geGrade(Engl, englScan);

            //store the information to the list
            students.add(new Student(name, Chin, Math, Engl));
        }

        //sorted by total grade
        Collections.sort(students, new GradeCompare());

        //input the aim address of the file
        System.out.println("请输入要储存的文件地址（可自动新建）：");
        Scanner fileScan = new Scanner(System.in);
        if (fileScan.hasNext())
            fileName = fileScan.next();

        //利用迭代器将信息写入文件中
        writeFile(fileName, "此次录入学生信息共" + num + "条： ");
        Iterator<Student> it = students.listIterator();
        while (it.hasNext()){
            writeFile(fileName, it.next().getInfo());

            //控制台显示
            //System.out.println(it.next().getInfo());
        }
        System.out.println("成功录入！");
    }
}

/*
 *  to implement the grade compare
 *  form highest to lowest
 */
class GradeCompare implements Comparator<Student>
{
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getTotal() > o2.getTotal())
            return -1;
        else return 1;
    }
}
