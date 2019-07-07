package jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpademo");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

//        Employee employee = manager.find(Employee.class, 1);
//        System.out.println(employee);

        Query query = manager.createQuery("select UPPER(e.ename) from Employee e");
        List<String> list = query.getResultList();
        for (String str : list) {
            System.out.println(str);
        }
    }
}
