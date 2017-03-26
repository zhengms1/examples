import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MQMonitorBasicTest {

//    @Test
//    public void purgeQueue() {
//        MQMonitorBasic mqMonitorBasic = new MQMonitorBasic("dev");
//        Assert.assertEquals(0,mqMonitorBasic.reportQueueDepth());
//
//    }

    @Test
    public void checkforEmptyQueue() {
        MQMonitorBasic mqMonitorBasic = new MQMonitorBasic("dev");
        Assert.assertEquals(0,mqMonitorBasic.reportQueueDepth());
    }

    @Test
    public void checkForOneMessage() {
        MQMonitorBasic mqMonitorBasic = new MQMonitorBasic("dev");
        mqMonitorBasic.sendMessage("UnitTest");
        Assert.assertEquals(1,mqMonitorBasic.reportQueueDepth());
    }

    @Test
    public void checkMaxDepth() {
        MQMonitorBasic mqMonitorBasic = new MQMonitorBasic("dev");
        Assert.assertEquals(5000,mqMonitorBasic.getMaxDepth());
    }
}
