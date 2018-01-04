package jpa.service;

import jpa.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpademo");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        Employee employee = new Employee("fox", 12345, "b");

        manager.persist(employee);
        manager.getTransaction().commit();

        manager.close();
        factory.close();

    }
}
