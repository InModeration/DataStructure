package clone;

public class ObjectC implements Cloneable {
    int value;
    public ObjectC(int value){
        this.value = value;
    }

    public void doubleValue()
    {
        value *= 2;
    }

    public Object clone(){
        ObjectC oc = null;
        try{
            oc = (ObjectC)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return oc;
    }

    public String toString()
    {
        return Integer.toString(value);
    }
}
