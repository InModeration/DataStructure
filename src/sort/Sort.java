package sort;

import java.util.Random;

/*
 *  排序方法类
 *  定义多种静态排序方法
 */
public class Sort {
    public static int temp[] = new int[10];
    /*
     *  三种时间复杂度为 n^2 的排序方法
     *  默认从小到大的顺序
     */

    /*
     *  插入排序(对数组的前n个元素进行排序)
     *  i从第二个元素开始遍历，前i - 1 个元素已经排序完成
     *  将其插入至前i - 1个元素当中，利用j与j - 1比较，将该元素“下沉”至合适的位置
     */
    public static void insertSort(int data[], int n)
    {
        if (n > data.length)
            throw new ArrayIndexOutOfBoundsException("排序个数大于数组元素个数");
        else {
            for (int i = 1; i < n; i++)
                for (int j = i; j > 0; j--)
                    swap(data, j - 1, j);
            //to test the method if has run
            System.out.println("insertSort has run");
        }
    }

    /*
     *  选择排序(对数组的前n个元素进行排序)
     *  共n次选择，第i次（0<i<=n)选择即选出数组中第i小的元素
     *  第i次循环时，前i - 1个元素已完成排序
     *  声明lowIndex用于记录剩下的元素中最小元素的下标，对i后的元素进行遍历
     *  选出最小元素，并记录其下标为lowIndex,内层循环结束后，交换i与lowIndex
     */
    public static void selectSort(int data[], int n)
    {
        if (n > data.length)
            throw new ArrayIndexOutOfBoundsException("排序个数大于数组元素个数");
        else {
            for (int i = 0; i < n; i++){
                int lowIndex = i;            //记录最小变元的下标
                for (int j = i; j < n; j++)
                    if (data[j] < data[lowIndex])
                        lowIndex = j;
            swap(data, i, lowIndex, true);
            }
            System.out.println("selectSort has run");
        }
    }

    /*
     *  冒泡排序(对前n个元素进行排序)
     *  第i次排序时，前面的元素已有序，并且是最终顺序，即0位为最小元素，1位为第二小元素···
     *  从最后一个元素开始，将后面的元素两两比较，把剩下元素中的最小元素排到i位上
     */
    public static void bubbleSort(int data[], int n)
    {
        if (n > data.length)
            throw new ArrayIndexOutOfBoundsException("排序个数大于数组元素个数");
        else {
            for (int i = 0; i < n; i++)
                for (int j = n - 1; j > i; j--)
                    swap(data, j - 1, j);
        }
        System.out.println("bubbleSort has run");
    }

    /*
     *  希尔排序(复杂度小于n^2)(对前n个元素进行排序)
     *  将元素分为多个小组，再对每个小组中的元素进行插入排序
     *  缩小分组数，重复上一步骤
     *  最后一次排序是局部有序的插入排序
     */
    public static void shellSort(int data[], int n)
    {
        int incre = n/2;            //定义每次排序的间隔
        int virtualIncre;
        while (incre >= 1) {

            //用于最后一次排序(incre = 1)的情况
            if (incre == 1)
                virtualIncre = 0;
            else virtualIncre = incre;

            //先按间隔incre将元素分组
            //对于data[0] data[1] ··· data[incre - 1] 一定不存在于同一组，且data[i] = data[i + incre}
            //故所有元素被分为incre组
            for (int j = 0; j < incre; j++)
            {
                //再对每一组的元素进行插入排序
                //data[j] data[j + incre] data[j + incre + incre] ····
                /*
                 *  假如有7个元素，间隔为3：
                 *  当 x = 1 ; 则 x + incre = 4;
                 *  x + incre + incre = 7 -- 此时越界了
                 *  则x循环的条件应该是 (x + incre < n) = (x < n - incre)
                 *  y循环的条件是y大于一组中最小下标 即 y > j
                 *
                 *  当incre = 1 时， x 应该小于 n 而不是 n - incre
                 */
                for (int x = j; x < n - virtualIncre; x += incre)
                    for (int y = x; y > j; y -= incre)
                        if (data[y - incre] > data[y])
                            swap(data, y - incre, y, true);
            }
            //缩小间隔， 继续排序
            incre /= 2;
        }
        System.out.println("shellSort has run");
    }

    /**
     *
     * 归并排序 （对下标 left - right 的数组元素排序）
     * 递归分解并合并排序
     * @param data  进行排序操作的数组
     * @param temp  用于存放排序元素的临时数组
     * @param left  数组的左下标
     * @param right 数组的右下标
     */
    public static void mergeSort(int data[], int temp[], int left, int right)
    {
        //若左右下标相同，即只有一个元素，停止递归划分
        if (left == right)  return;

        //当前数组的中间下标，用于表示左子数组和右子数组的下标
        int mid = (left + right) / 2;

        //递归分解 -- 直到成为一个一个的元素
        mergeSort(data, temp, left, mid);
        mergeSort(data, temp, mid + 1, right);

        //将data中的元素复制到temp中
        for (int i = left; i <= right; i++)
            temp[i] = data[i];

        //对于下标 ： 左数组 left - mid  右数组 mid + 1 - right
        int i = left, j = mid + 1;
        for (int curr = left; curr <= right; curr++) {
            if (i == mid + 1)                       //左边的数组已经遍历完成，全部装入data里
                data[curr] = temp[j++];
            else if (j > right)                     //右边的数组已经遍历完成，全部装入data里
                data[curr] = temp[i++];
            else {                                  //当左右数组都还在遍历过程中时
                //若左边数组的当前元素小于右边数组的当前元素，将左边数组的当前元素放入data
                //并使左边的指针+1
                if (temp[i] < temp[j])
                    data[curr] = temp[i++];
                //若右边数组的元素小，执行上述类似的操作
                else data[curr] = temp[j++];
            }
        }
    }


    public static void quickSort()
    {

    }

    //比较+交换
    private static void swap(int data[], int i, int j)
    {
        int temp;
        //如果第一个变元大于第二个变元，互相交换值
        if (data[i] > data[j]) {
            temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
    }

    //交换
    private static void swap(int data[], int i, int j, boolean judge)
    {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args){
        /*int data[] = new int[10000000];
        int temp[] = new int[10000000];
        for (int i = 0; i < 10000000; i++)
        {
            long time = System.currentTimeMillis();
            Random r = new Random(time);
            data[i] = r.nextInt();
        }

        //insertSort(data, 15);
        //selectSort(data, 10);
        //bubbleSort(data, 5);
        //shellSort(data, 10);

        long start = System.currentTimeMillis();
        mergeSort(data, temp, 0, 9999999);
        //insertSort(data,  10000);
        long end = System.currentTimeMillis();
        System.out.println("排序完成，耗时:");
        System.out.println(end - start + " ms ");*/
        int data[] = {-5, -26, 5, 1, 8, 3, 0, 16, 4, -7, 5, 1, 14, 16, 16, -20};
        insertSort(data, data.length);
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i] + "  ");
    }
}
