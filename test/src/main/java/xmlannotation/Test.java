package xmlannotation;

import javax.xml.bind.*;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ClassA classA = new ClassA();
        classA.setAge(11);
        classA.setName("fox");
        ClassA classB = new ClassA("ff", 2, "女");
        List<ClassA> cs = new ArrayList<>();
        cs.add(classA);
        cs.add(classB);

        Body body = new Body();
        body.setName("哈哈哈");

        RootClass rootClass = new RootClass();
        rootClass.setBody(body);
        try {
            System.out.println(objToXml(rootClass, "utf-8"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<root>\n" +
                "    <requestBody>\n" +
                "        <height>175.0</height>\n" +
                "    </requestBody>\n" +
                "</root>";
        RootClass root = new RootClass();
        try {
            root = xmlToObj(xml, root.getClass());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println(root);
    }

    static String objToXml(Object obj, String encoding) throws JAXBException {
        String result;

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);

        result = writer.toString();

        return result;
    }

    static <T> T xmlToObj(String xml, Class<T> t) throws JAXBException {
        T obj = null;
        JAXBContext context = JAXBContext.newInstance(t);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        obj = (T) unmarshaller.unmarshal(new StringReader(xml));

        return obj;
    }
}
