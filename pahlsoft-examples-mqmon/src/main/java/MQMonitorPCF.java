//import com.ibm.mq.MQException;
//import com.ibm.mq.commonservices.CommonServicesException;
//import com.ibm.mq.constants.CMQC;
//import com.ibm.mq.constants.CMQCFC;
//import com.ibm.mq.pcf.PCFMessage;
//import com.ibm.mq.pcf.PCFMessageAgent;
//import com.ibm.msg.client.wmq.v6.base.internal.MQEnvironment;
//import com.ibm.msg.client.wmq.v6.base.internal.MQQueue;
//import com.ibm.msg.client.wmq.v6.base.internal.MQQueueManager;


import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;


public class MQMonitorPCF {

//    private static int openOptions = CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED;
//    private static Properties properties = new Properties();
//    private static String environment = "";
//    private static MQQueueManager queueManager;
//    private static MQQueue queue;
//
//    public static void main(String[] args) throws Exception {
//        properties.load(MQMonitorPCF.class.getResourceAsStream("mq-monitor.properties"));
//
//        displayProperties();
//        checkArgs(args);
//
//        MQEnvironment.hostname = properties.getProperty(environment + ".hostName");
//        MQEnvironment.port = Integer.parseInt(properties.getProperty(environment + ".port"));
//        MQEnvironment.channel = properties.getProperty(environment + ".channelName");
//        MQEnvironment.userID = properties.getProperty(environment + ".mqUserID");
//        MQEnvironment.password = properties.getProperty(environment + ".mqPwd");
//         queueManager = new MQQueueManager( properties.getProperty(environment + ".queueManagerName"));
//        String timeDateStamp = new Date().toString();
//         queue = queueManager.accessQueue(properties.getProperty(environment + ".queueName"),openOptions);
//         //queueManager.getAttributeString();
//
//        checkStats(properties.getProperty(environment + ".queueName"),
//                   properties.getProperty(environment + ".queueName"));
//
//         System.out.println(timeDateStamp + "|QueueMgr=" + properties.getProperty(environment + ".queueManagerName")
//                                         + "|QueueName=" + properties.getProperty(environment + ".queueName")
//                                         + "|CurrentQueueDepth=" + queue.getCurrentDepth()
//                                         + "|MaximumQueueDepth= " + queue.getDescription());
//
//        queue.close();
//        queueManager.disconnect();
//
//    }
//
//    private static void displayProperties() {
//        Enumeration em = properties.keys();
//        while (em.hasMoreElements()) {
//            String key=(String)em.nextElement();
//            System.out.println(key + " = " + properties.get(key));
//        }
//    }
//
//    private static void checkArgs(String[] args) {
//        if (args.length > 0) {
//            environment = args[0];
//        } else {
//            System.out.println("Please specify Environment Code");
//            System.out.println("Usage: ");
//            System.out.println("      java -jar *.jar [dev|qa|cat|prod]");
//            System.exit(1);
//        }
//    }
//
//    private static int checkStats(String queueManger, String queueName) throws CommonServicesException, MQException, IOException {
//
//        PCFMessageAgent pcfAgent = new PCFMessageAgent(queueManger);
//        PCFMessage pcfMessage = new PCFMessage(CMQCFC.MQCMD_INQUIRE_Q_STATUS);
//        pcfMessage.addParameter(CMQC.MQCA_Q_NAME, queueName);
//        pcfMessage.addParameter(CMQCFC.MQIACF_Q_STATUS_TYPE, CMQCFC.MQIACF_Q_STATUS);
//        pcfMessage.addParameter(CMQCFC.MQIACF_Q_STATUS_ATTRS, new int[] {
//                CMQC.MQCA_Q_NAME, CMQC.MQIA_CURRENT_Q_DEPTH,
//                CMQCFC.MQCACF_LAST_GET_DATE, CMQCFC.MQCACF_LAST_GET_TIME,
//                CMQCFC.MQCACF_LAST_PUT_DATE, CMQCFC.MQCACF_LAST_PUT_TIME,
//                CMQCFC.MQIACF_OLDEST_MSG_AGE, CMQC.MQIA_OPEN_INPUT_COUNT,
//                CMQC.MQIA_OPEN_OUTPUT_COUNT, CMQCFC.MQIACF_UNCOMMITTED_MSGS });
//
//        PCFMessage[] pcfResponse = pcfAgent.send(pcfMessage);
//            return pcfResponse.length;
//    }

}
