package xmlannotation;

import javax.xml.bind.*;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Test {
    public static void main(String[] args) {
        ClassA classA = new ClassA("huli", 23, "男");
        RootClass rootClass = new RootClass(classA);

        try {
            System.out.println(objToXml(classA, "UTF-8"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<classA>\n" +
                "    <name>huli</name>\n" +
                "    <age>23</age>\n" +
                "    <gender>男</gender>\n" +
                "</classA>";
        ClassA aa = new ClassA();
        try {
            aa = xmlToObj(xml, aa.getClass());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println(aa.getAge());
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
