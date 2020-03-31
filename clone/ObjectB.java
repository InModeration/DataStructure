package clone;

public class ObjectB implements Cloneable {
    int value;
    ObjectA oa = new ObjectA(100);
    ObjectC oc = new ObjectC(500);

    public ObjectB(int value){
        this.value = value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public Object clone()
    {
        ObjectB ob = null;
        try{
            ob = (ObjectB)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        //将ObjectB中的ObjectC域进行clone处理
        ob.oc = (ObjectC)oc.clone();
        return ob;
    }
}
