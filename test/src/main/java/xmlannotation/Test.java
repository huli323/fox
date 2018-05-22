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
        ClassA classA = new ClassA("huli", 23, "男");
        ClassA classB = new ClassA("ff", 2, "女");
        List<ClassA> cs = new ArrayList<>();
        cs.add(classA);
        cs.add(classB);
        RootClass rootClass = new RootClass();
        rootClass.setClassA(cs);
        try {
            System.out.println(objToXml(rootClass, "UTF-8"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<root>\n" +
                "    <childs>\n" +
                "        <Name>huli</Name>\n" +
                "        <Age>23</Age>\n" +
                "        <a>男</a>\n" +
                "    </childs>\n" +
                "    <child>\n" +
                "        <Name>ff</Name>\n" +
                "        <Age>2</Age>\n" +
                "        <gender>女</gender>\n" +
                "    </child>\n" +
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
