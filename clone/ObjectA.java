package clone;

public class ObjectA {
    int value;

    public ObjectA(int value)
    {
        this.value = value;
    }

    public void doubleValue()
    {
        value *= 2;
    }

    public String toString()
    {
        return Integer.toString(value);
    }
}
