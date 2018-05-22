package xmlannotation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "childs")
@XmlAccessorType(XmlAccessType.FIELD)
public class Childs {
    @XmlElement(name = "classA")
    private List<ClassA> classA;

    public List<ClassA> getClassA() {
        return classA;
    }

    public void setClassA(List<ClassA> classA) {
        this.classA = classA;
    }
}
