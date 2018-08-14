package xmlannotation;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootClass {

    @XmlElement(name="requestBody")
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "RootClass{" +
                "body=" + body +
                '}';
    }
}
