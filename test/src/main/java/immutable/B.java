package immutable;

import java.io.Serializable;

public class B implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public B(String name) {

        this.name = name;
    }
}
