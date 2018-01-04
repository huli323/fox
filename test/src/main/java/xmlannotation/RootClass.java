package xmlannotation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootClass {
    @XmlElement(name="child")
    private ClassA classA;

    public RootClass() {
    }

    public RootClass(ClassA classA) {

        this.classA = classA;
    }

    public void setClassA(ClassA classA) {

        this.classA = classA;
    }

    public ClassA getClassA() {

        return classA;
    }
}
