package immutable;

import java.io.*;

public class A implements Serializable {
    private B b;

    public void setB(B b) {
        this.b = b;
    }

    public B getB() {

        return b;
    }

    public A(B b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "A{" +
                "b=" + b +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bo);
            os.writeObject(this);

            ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bo.toByteArray()));
            obj = is.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
