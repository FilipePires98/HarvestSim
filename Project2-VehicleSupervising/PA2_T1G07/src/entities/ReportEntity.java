package entities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import kafkaUtils.Consumer;
import kafkaUtils.EntityAction;
import message.Message;
import message.MessageDeserializer;

/**
 * Class for the Report Entity for the car supervising system.
 * This entity reads data from the ReportTopic, presents the received messages in its GUI and writes them to REPORT.TXT.
 * 
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public class ReportEntity extends JFrame implements EntityAction<Integer,Message>{
    
    /**
     * Minimum size of the consumer group definable by the user.
     */
    private final int MINGROUPSIZE = 1;
    /**
     * Maximum size of the consumer group definable by the user.
     */
    private final int MAXGROUPSIZE = 10;
    /**
     * Name of the topic that the entity reads data from.
     */
    private String topicName="ReportTopic";
    /**
     * Name of the consumer group.
     */
    private String groupName="ReportTopicGroup";
    /**
     * Consumer properties (bootstrap.servers, group.id, key.deserializer, value.deserializer, etc.).
     */
    private Properties props = new Properties();
    
    /**
     * Writer responsible for IO interactions with the file REPORT.TXT.
     */
    private FileWriter file;
    /**
     * Array of consumers dedicated to this entity (of size MAXGROUPSIZE).
     */
    private Consumer[] consumers = new Consumer[MAXGROUPSIZE];
    /**
     * Array of consumer threads, each dedicated to a consumer instance, (of size MAXGROUPSIZE).
     */
    private Thread[] consumerThreads = new Thread[MAXGROUPSIZE];
    /**
     * Number of active consumers working for the entity, definable by the user.
     */
    private int activeConsumers = 3;
    
    /**
     * Cache containing the number of times each message has been processed (to allow consumer coordination).
     */
    private Map<Integer,Integer> processedMessages = new HashMap<Integer, Integer>();

    private int reprocessed=0;
    private List<Integer> knownMessages=new ArrayList<Integer>();

    /**
     * Creates new form ReportEntity and requests consumer initialization.
     */
    public ReportEntity() {
        this.setTitle("Report Entity");
        initComponents();
        
        try {
            this.file = new FileWriter("src/data/REPORT.TXT");
        } catch (IOException ex) {
            Logger.getLogger(ReportEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        startConsumers();
    }
    
    /**
     * Initializes consumers.
     */
    private void startConsumers() {                                      
        props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put("value.deserializer", MessageDeserializer.class.getName());
        props.put("enable.auto-commit", false);
        String[] tmp = new String[]{topicName};
        Consumer<Integer, Message> consumer;
        for(int i=0; i<(Integer)nConsumers.getValue(); i++) {
            consumer = new Consumer<Integer,Message>(i, props, tmp, this);
            Thread t = new Thread(consumer);
            t.start();
            consumers[i] = consumer;
            consumerThreads[i] = t;
        }
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        consumersLabel = new javax.swing.JLabel();
        nConsumers = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        logs = new javax.swing.JTextArea();
        reportAndReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(500, 360));

        consumersLabel.setText("# of Consumers:");

        nConsumers.setModel(new SpinnerNumberModel(activeConsumers,MINGROUPSIZE,MAXGROUPSIZE,1));
        nConsumers.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nConsumersStateChanged(evt);
            }
        });

        logs.setColumns(20);
        logs.setRows(5);
        jScrollPane1.setViewportView(logs);

        reportAndReset.setText("ReportAndReset");
        reportAndReset.setAutoscrolls(true);
        reportAndReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportAndResetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(consumersLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nConsumers, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                        .addComponent(reportAndReset)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consumersLabel)
                    .addComponent(nConsumers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportAndReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method through which the user defines the number of active consumers.
     * 
     * @param evt change event triggered, not used in our context
     */
    private void nConsumersStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nConsumersStateChanged

        if((Integer)nConsumers.getValue() == activeConsumers) {
            return;
        }

        if((Integer)nConsumers.getValue() > activeConsumers) {
            String[] tmp = new String[]{topicName};
            Consumer<Integer, Message> consumer = new Consumer<Integer,Message>(consumers.length,props, tmp, this);
            Thread t = new Thread(consumer);
            t.start();
            consumers[activeConsumers] = consumer;
            consumerThreads[activeConsumers] = t;
        } else {
            consumers[(Integer)nConsumers.getValue()].shutdown();
        }
        activeConsumers = (Integer)nConsumers.getValue();

        String line = " consumers are listening to " + topicName;
        System.out.println("[Batch] " + activeConsumers + line);
        this.logs.append(activeConsumers + line + "\n");
    }//GEN-LAST:event_nConsumersStateChanged

    /**
     * Prints to the GUI's console the total number of processed messages of each type and resets the respective counters.
     * 
     * @param evt mouse event triggered, not used in our context
     */
    private void reportAndResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportAndResetMouseClicked
        String tmp ="";
        int total=0;
        for(int key : processedMessages.keySet()){
            total+=processedMessages.get(key);
            switch(key){
                case 0:
                tmp+="Heartbeat: "+processedMessages.get(key)+"; ";
                break;
                case 1:
                tmp+="Speed: "+processedMessages.get(key)+"; ";
                break;
                case 2:
                tmp+="Status: "+processedMessages.get(key)+"; ";
                break;
            }
        }
        tmp+="Reprocessed: "+reprocessed+"; ";
        tmp+="Total: "+total+"\n";

        processedMessages.clear();
        reprocessed=0;
        knownMessages.clear();
        
        logs.append(tmp);
        logs.setCaretPosition(logs.getDocument().getLength());
    }//GEN-LAST:event_reportAndResetMouseClicked

    /**
     * Report entity's main method, responsible for creating and displaying the GUI.
     * Arguments are not needed.
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.out.println("[Report] Running...");
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CollectEntity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CollectEntity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CollectEntity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CollectEntity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportEntity().setVisible(true);
            }
        });
    }

    /**
     * Processes messages from the ReportTopic.
     * 
     * @param consumerId identifier of the consumer processing the current message
     * @param topic Kafka topic to which the message belongs to
     * @param key message unique key
     * @param value message value, actual message content with a format defined a priori
     */
    @Override
    public void processMessage(int consumerId,String topic, Integer key, Message value) {
        try {
            String tmp  = value.toString();
            file.write(tmp+"\n");
            file.flush();
//            printedLines++;
            this.logs.append("["+key+"][Consumer: "+consumerId+"] "+ tmp + "\n");
            logs.setCaretPosition(logs.getDocument().getLength());
//            System.out.println("[REPORT] Processed message: "+tmp);
            
            if(processedMessages.containsKey(value.getType())){
                processedMessages.put(value.getType(), processedMessages.get(value.getType())+1);
            }else{
                processedMessages.put(value.getType(), 1);
            }
            
            if(knownMessages.contains(key)){
                reprocessed++;
            }else{
                knownMessages.add(key);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ReportEntity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel consumersLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logs;
    private javax.swing.JSpinner nConsumers;
    private javax.swing.JButton reportAndReset;
    // End of variables declaration//GEN-END:variables
}
