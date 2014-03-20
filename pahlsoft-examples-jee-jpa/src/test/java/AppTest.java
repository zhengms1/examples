import com.pahlsoft.examples.ee.jpa.EventsEntity;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.Iterator;

public class AppTest {

    @Test
    public void testJpa() {
        System.out.println("Starting App...");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        //Query query = em.createQuery("select e from EventsEntity e where e.eventId=2 ");
        Query query = em.createQuery("select e from EventsEntity e ");

        Iterator itr = query.getResultList().iterator();

        while (itr.hasNext()) {
            EventsEntity event = (EventsEntity) itr.next();
            System.out.println("Found Event: " + event.getDescription());
        }

    }
}