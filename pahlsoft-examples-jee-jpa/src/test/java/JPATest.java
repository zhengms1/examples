import com.pahlsoft.examples.ee.jpa.EventsEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.FileReader;

public class JPATest {

    EntityManagerFactory emf;
    EntityManager em;
    short eventNumber = 0;
    String sqlScript;

    @BeforeClass
    public void init(){
        System.out.println("Initializing Tests...");
        emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = emf.createEntityManager();

        try {
            sqlScript = readFile("src/main/config/create-database-derby.sql");
        } catch (Exception e) {
            System.out.println("Could not find sql file");
        }
        em.getTransaction().begin();
        Query query = em.createNativeQuery(sqlScript);
        query.executeUpdate();
        em.getTransaction().commit();

    }

    @Test(priority = 1)
    public void insertTestEvent() {
        EventsEntity eventEntity = new EventsEntity();
        eventEntity.setDescription("Event Created by TestNG");
        eventEntity.setStatus("2");
        eventEntity.setTitle("Event Created by TestNG");
        //eventEntity.setEventId();
        em.getTransaction().begin();
        em.persist(eventEntity);
        em.getTransaction().commit();
        eventNumber = eventEntity.getEventId();
        System.out.println("Created Event: " + eventNumber);

    }

    @Test(priority = 2)
    public void selectTestEvent() {
        em.getTransaction().begin();
        Query query = em.createQuery("select e from EventsEntity e where e.eventId=" + eventNumber);
        EventsEntity event =  (EventsEntity) query.getSingleResult();
        System.out.println("Found Event: " + event.getEventId());
        em.getTransaction().commit();
        Assert.assertEquals(eventNumber, event.getEventId());

    }

    @Test(priority = 3)
    public void deleteTestEvent() {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM EventsEntity s WHERE s.eventId = :eventID ");
        query.setParameter("eventID",eventNumber);
        int deleted = query.executeUpdate();
        Assert.assertEquals(1,deleted);
        em.getTransaction().commit();
        System.out.println("Deleted Event: " + eventNumber);

    }

    private String readFile(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}