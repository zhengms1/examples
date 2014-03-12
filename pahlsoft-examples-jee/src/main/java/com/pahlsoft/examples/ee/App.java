package com.pahlsoft.examples.ee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App {
    public static void main(String [] args) {
        System.out.println("Yo");
        EventEntity event = new EventEntity(1211,"EventTitle","EventDescription",12);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventData");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(event);
        tx.commit();


    }
}
