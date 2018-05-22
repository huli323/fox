package xmlannotation;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootClass {
//    @XmlElement(name="child")
//    private ClassA classA;

    @XmlElement(name="child")
    @XmlElementWrapper(name = "childs")
    private List<ClassA> classA;

    @XmlElement(name="className")
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public RootClass() {
    }

    public List<ClassA> getClassA() {
        return classA;
    }

    public void setClassA(List<ClassA> classA) {
        this.classA = classA;
    }

    @Override
    public String toString() {
        return "RootClass{" +
                "classA=" + classA +
                ", className='" + className + '\'' +
                '}';
    }
}
