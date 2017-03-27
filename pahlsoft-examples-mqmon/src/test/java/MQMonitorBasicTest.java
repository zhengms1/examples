import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MQMonitorBasicTest {

    private static MQMonitorBasic mqMonitorBasic;

    @Before
    public void init() {
       mqMonitorBasic = new MQMonitorBasic("dev");
    }
    @Test
    public void sendMessage() {
        int initialValue = mqMonitorBasic.reportQueueDepth();
        mqMonitorBasic.sendMessage("Test 1");
        int secondaryValue = mqMonitorBasic.reportQueueDepth();
        Assert.assertTrue(initialValue<secondaryValue);
    }

    @Test
    public void purgeQueue() {
        mqMonitorBasic.purgeQueue();
        Assert.assertEquals(0,mqMonitorBasic.reportQueueDepth());
    }

    @Test
    public void purgeQueue10() {
        for (int i = 0; i<10; i++) {
            mqMonitorBasic.sendMessage("Test "+ i);
        }
        mqMonitorBasic.purgeQueue();
        Assert.assertEquals(0,mqMonitorBasic.reportQueueDepth());
    }


//    @Test
//    public void checkforEmptyQueue() {
//        mqMonitorBasic.purgeQueue();
//        Assert.assertEquals(0,mqMonitorBasic.reportQueueDepth());
//    }

//    @Test
//    public void checkForOneMessage() {
//        mqMonitorBasic.sendMessage("UnitTest");
//        Assert.assertEquals(1,mqMonitorBasic.reportQueueDepth());
//    }
//
    @Test
    public void checkMaxDepth() {
        Assert.assertEquals(5000,mqMonitorBasic.getMaxDepth());
    }
}
