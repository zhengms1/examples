
import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class MQMonitorBasic {

private static int openOptions = CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED + CMQC.MQOO_OUTPUT
                       + CMQC.MQGMO_NO_WAIT + CMQC.MQGMO_ACCEPT_TRUNCATED_MSG;
    private static Properties properties = new Properties();
    private static MQQueueManager queueManager;
    private static MQQueue queue;
    private static String queueName = "undefined";
    private static String queueManagerName = "undefined";
    private static Logger LOG = LoggerFactory.getLogger(MQMonitorBasic.class);

    public MQMonitorBasic(String environment) {
        try {
            LOG.info("Loading Properites for " + environment);
            loadProperties(environment);
            connectToQueueManager();
        } catch (Exception e) {
            LOG.error("Unable to Load Properties for " + environment);
        }
    }

    private void loadProperties(String environment) throws IOException {
        properties.load(MQMonitorBasic.class.getResourceAsStream("mq-monitor.properties"));
        displayProperties();
        MQEnvironment.hostname = properties.getProperty(environment + ".hostName");
        MQEnvironment.port = Integer.parseInt(properties.getProperty(environment + ".port"));
        MQEnvironment.channel = properties.getProperty(environment + ".channelName");
        MQEnvironment.userID = properties.getProperty(environment + ".mqUserID");
        MQEnvironment.password = properties.getProperty(environment + ".mqPwd");
        queueName = properties.getProperty(environment + ".queueName");
        queueManagerName = properties.getProperty(environment + ".queueManagerName");
    }

    private void connectToQueueManager() throws MQException {
        queueManager = new MQQueueManager( queueManagerName );
        if (queueManager.isConnected()) LOG.info("Connected: " + queueManager.getName());
    }

    private static int checkQueueDepth() throws MQException {
        queue = queueManager.accessQueue(queueName,openOptions);
        return queue.getCurrentDepth();
  }

  public void terminate() throws MQException {
      queue.close();
      queueManager.disconnect();
  }

    public static int reportQueueDepth() {
        int queueDepth = -1 ;
        try {
            return checkQueueDepth();
        } catch (MQException e) {
            LOG.error("MQ Connection Error. Reason Code: " + e.getReason());
        }
        return queueDepth;
    }

    private static void displayProperties() {
        Enumeration em = properties.keys();
        while (em.hasMoreElements()) {
            String key=(String)em.nextElement();
            System.out.println(key + " = " + properties.get(key));
        }
    }

    public int getMaxDepth() {
        try {
            return queue.getMaximumDepth();
        } catch (MQException e) {
            LOG.error("Error Getting Queue Max Queue Depth. Reason Code: " + e.reasonCode);
        }
        return -1;
    }
    public void sendMessage(String msg) {
        MQMessage message = new MQMessage();
        try {
            message.messageType = 8;
            try {
                message.writeString(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            queue.put(message);
        } catch (MQException e) {
            LOG.error("Error Sending Message. Reason Code: " + e.reasonCode);
        }
    }
//    public void purgeQueue() {
//        MQMessage message;
//        try {
//            while (queue.getCurrentDepth()>0) {
//                message = new MQMessage();
//                try {
//                    queue.get(message);
//                } catch (MQException e) {
//                    if (e.completionCode == 1 && e.reasonCode == MQException.MQRC_TRUNCATED_MSG_ACCEPTED) {
//                        LOG.info("Queue has been depleted");
//                    } else {
//                        if (e.completionCode == 2 && e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE) {
//                            LOG.info("Queue has been depleted");
//                        } else {
//                            LOG.error("Error trying to purge Queue. Reason Code: " + e.reasonCode);
//                        }
//                    }
//                }
//            }
//        } catch (MQException e) {
//            LOG.error("Unable to determine queue Depth.");
//        }
//    }

}
