package cc;
import cc.utils.CCMessageProcessor;
import common.SocketClient;
import common.SocketServer;
import javax.swing.JTextField;

/**
 * Main class for the Control Center. Also functions as the GUI for the user analyze information and execute commands.
 * @author Filipe Pires (85122) and João Alegria (85048)
 */
public class ControlCenter extends javax.swing.JFrame implements UiAndMainControlsCC{

    /**
     * Communication client to send messages to FI's server.
     */
    private SocketClient fiClient;
    /**
     * Communication server to receive messages from FI's client.
     */
    private SocketServer ccServer;
    /**
     * Communication thread to process CC's server messages.
     */
    private Thread serverThread;

    /**
     * Auxiliary array to manage swing interface text fields.
     */
    private JTextField[] storehouseTextFields;
    /**
     * Auxiliary array to manage swing interface text fields.
     */
    private JTextField[] standingAreaTextFields;
    /**
     * Auxiliary bidimensional array to manage swing interface text fields.
     */
    private JTextField[][] pathTextFields;
    /**
     * Auxiliary array to manage swing interface text fields.
     */
    private JTextField[] granaryTextFields;

    /**
     * Number of farmers available during the simulation.
     */
    public static final int teamSize = 5;
    /**
     * Length/size of the path (number of positions farmers need to cross).
     */
    public static final int pathSize = 10;
    /**
     * Maximum response delay.
     */
    public static final int maxDelay = 100;

    /**
     * Class construction. Initializes swing components and starts sockets.
     */
    public ControlCenter() {
        this.setTitle("Control Center");
        initComponents();
        groupTextFields();
        this.ccServer = new SocketServer(6666, new CCMessageProcessor(this));
        this.serverThread = new Thread(ccServer);
        this.serverThread.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        cobsLabel = new javax.swing.JLabel();
        numCornCobs = new javax.swing.JSpinner();
        farmersLabel = new javax.swing.JLabel();
        numFarmers = new javax.swing.JSpinner();
        timeoutLabel = new javax.swing.JLabel();
        timeout = new javax.swing.JSpinner();
        stepLabel = new javax.swing.JLabel();
        maxStep = new javax.swing.JSpinner();
        prepareBtn = new javax.swing.JButton();
        startBtn = new javax.swing.JButton();
        collectBtn = new javax.swing.JButton();
        returnBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        cobsLabel1 = new javax.swing.JLabel();
        granaryPanel = new javax.swing.JPanel();
        granaryLabel = new javax.swing.JLabel();
        g4 = new javax.swing.JTextField();
        g5 = new javax.swing.JTextField();
        g1 = new javax.swing.JTextField();
        g2 = new javax.swing.JTextField();
        g3 = new javax.swing.JTextField();
        granaryCornCobs = new javax.swing.JTextField();
        granaryCornCobsLabel = new javax.swing.JLabel();
        gc = new javax.swing.JTextField();
        pathPanel = new javax.swing.JPanel();
        PathLabel = new javax.swing.JLabel();
        p01_1 = new javax.swing.JTextField();
        p01_2 = new javax.swing.JTextField();
        p01_3 = new javax.swing.JTextField();
        p01_4 = new javax.swing.JTextField();
        p01_5 = new javax.swing.JTextField();
        p02_1 = new javax.swing.JTextField();
        p02_2 = new javax.swing.JTextField();
        p02_3 = new javax.swing.JTextField();
        p02_4 = new javax.swing.JTextField();
        p02_5 = new javax.swing.JTextField();
        p03_1 = new javax.swing.JTextField();
        p03_2 = new javax.swing.JTextField();
        p03_3 = new javax.swing.JTextField();
        p03_4 = new javax.swing.JTextField();
        p03_5 = new javax.swing.JTextField();
        p04_1 = new javax.swing.JTextField();
        p04_2 = new javax.swing.JTextField();
        p04_3 = new javax.swing.JTextField();
        p04_4 = new javax.swing.JTextField();
        p04_5 = new javax.swing.JTextField();
        p05_1 = new javax.swing.JTextField();
        p05_2 = new javax.swing.JTextField();
        p05_3 = new javax.swing.JTextField();
        p05_4 = new javax.swing.JTextField();
        p05_5 = new javax.swing.JTextField();
        p06_1 = new javax.swing.JTextField();
        p06_2 = new javax.swing.JTextField();
        p06_3 = new javax.swing.JTextField();
        p06_4 = new javax.swing.JTextField();
        p06_5 = new javax.swing.JTextField();
        p07_1 = new javax.swing.JTextField();
        p07_2 = new javax.swing.JTextField();
        p07_3 = new javax.swing.JTextField();
        p07_4 = new javax.swing.JTextField();
        p07_5 = new javax.swing.JTextField();
        p08_1 = new javax.swing.JTextField();
        p08_2 = new javax.swing.JTextField();
        p08_3 = new javax.swing.JTextField();
        p08_4 = new javax.swing.JTextField();
        p08_5 = new javax.swing.JTextField();
        p09_1 = new javax.swing.JTextField();
        p09_2 = new javax.swing.JTextField();
        p09_3 = new javax.swing.JTextField();
        p09_4 = new javax.swing.JTextField();
        p09_5 = new javax.swing.JTextField();
        p10_1 = new javax.swing.JTextField();
        p10_2 = new javax.swing.JTextField();
        p10_3 = new javax.swing.JTextField();
        p10_4 = new javax.swing.JTextField();
        p10_5 = new javax.swing.JTextField();
        standingAreaPanel = new javax.swing.JPanel();
        standingAreaLabel = new javax.swing.JLabel();
        sa1 = new javax.swing.JTextField();
        sa2 = new javax.swing.JTextField();
        sa3 = new javax.swing.JTextField();
        sa4 = new javax.swing.JTextField();
        sa5 = new javax.swing.JTextField();
        storehousePanel = new javax.swing.JPanel();
        storehouseLabel = new javax.swing.JLabel();
        sh1 = new javax.swing.JTextField();
        sh3 = new javax.swing.JTextField();
        sh2 = new javax.swing.JTextField();
        sh5 = new javax.swing.JTextField();
        sh4 = new javax.swing.JTextField();
        storehouseCornCobsLabel = new javax.swing.JLabel();
        storehouseCornCobs = new javax.swing.JTextField();
        ss = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setText("Control Center");

        cobsLabel.setText("Number of Cobs to Collect:");

        numCornCobs.setModel(new javax.swing.SpinnerNumberModel(10, 0, 50, 1));
        numCornCobs.setEnabled(false);

        farmersLabel.setText("Number of Farmers:");

        numFarmers.setModel(new javax.swing.SpinnerNumberModel(5, 2, 5, 1));
        numFarmers.setEnabled(false);

        timeoutLabel.setText("Timeout (ms):");

        timeout.setModel(new javax.swing.SpinnerNumberModel(500, 0, 1000, 50));
        timeout.setEnabled(false);

        stepLabel.setText("Max. Step:");

        maxStep.setModel(new javax.swing.SpinnerNumberModel(1, 1, 2, 1));
        maxStep.setEnabled(false);

        prepareBtn.setText("Prepare");
        prepareBtn.setEnabled(false);
        prepareBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prepareBtnMouseClicked(evt);
            }
        });

        startBtn.setText("Start");
        startBtn.setEnabled(false);
        startBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startBtnMouseClicked(evt);
            }
        });

        collectBtn.setText("Collect");
        collectBtn.setEnabled(false);
        collectBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                collectBtnMouseClicked(evt);
            }
        });

        returnBtn.setText("Return");
        returnBtn.setEnabled(false);
        returnBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnBtnMouseClicked(evt);
            }
        });

        stopBtn.setText("Stop");
        stopBtn.setEnabled(false);
        stopBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopBtnMouseClicked(evt);
            }
        });

        exitBtn.setText("Exit");
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitBtnMouseClicked(evt);
            }
        });

        cobsLabel1.setText("/50");

        granaryPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        granaryLabel.setText("Granary");

        g4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        g4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        g4.setEnabled(false);

        g5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        g5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        g5.setEnabled(false);

        g1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        g1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        g1.setEnabled(false);

        g2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        g2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        g2.setEnabled(false);

        g3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        g3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        g3.setEnabled(false);

        granaryCornCobs.setText("50");
        granaryCornCobs.setCaretColor(new java.awt.Color(0, 0, 0));
        granaryCornCobs.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        granaryCornCobs.setEnabled(false);

        granaryCornCobsLabel.setText("Corn Cobs");

        gc.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        gc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        gc.setEnabled(false);

        javax.swing.GroupLayout granaryPanelLayout = new javax.swing.GroupLayout(granaryPanel);
        granaryPanel.setLayout(granaryPanelLayout);
        granaryPanelLayout.setHorizontalGroup(
            granaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(granaryPanelLayout.createSequentialGroup()
                .addGroup(granaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(granaryPanelLayout.createSequentialGroup()
                        .addGroup(granaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(granaryPanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(granaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(g4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(granaryPanelLayout.createSequentialGroup()
                                        .addComponent(g3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(gc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(granaryPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(granaryLabel)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(granaryPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(granaryCornCobsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(granaryCornCobs, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                .addContainerGap())
        );
        granaryPanelLayout.setVerticalGroup(
            granaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, granaryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(granaryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(granaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(granaryCornCobsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, granaryPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(granaryCornCobs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(g1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(g2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(granaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(g3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(g4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(g5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pathPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        PathLabel.setText("Path");

        p01_1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p01_1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p01_1.setEnabled(false);

        p01_2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p01_2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p01_2.setEnabled(false);

        p01_3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p01_3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p01_3.setEnabled(false);

        p01_4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p01_4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p01_4.setEnabled(false);

        p01_5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p01_5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p01_5.setEnabled(false);

        p02_1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p02_1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p02_1.setEnabled(false);

        p02_2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p02_2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p02_2.setEnabled(false);

        p02_3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p02_3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p02_3.setEnabled(false);

        p02_4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p02_4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p02_4.setEnabled(false);

        p02_5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p02_5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p02_5.setEnabled(false);

        p03_1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p03_1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p03_1.setEnabled(false);

        p03_2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p03_2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p03_2.setEnabled(false);

        p03_3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p03_3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p03_3.setEnabled(false);

        p03_4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p03_4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p03_4.setEnabled(false);

        p03_5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p03_5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p03_5.setEnabled(false);

        p04_1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p04_1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p04_1.setEnabled(false);

        p04_2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p04_2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p04_2.setEnabled(false);

        p04_3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p04_3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p04_3.setEnabled(false);

        p04_4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p04_4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p04_4.setEnabled(false);

        p04_5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p04_5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p04_5.setEnabled(false);

        p05_1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p05_1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p05_1.setEnabled(false);

        p05_2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p05_2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p05_2.setEnabled(false);

        p05_3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p05_3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p05_3.setEnabled(false);

        p05_4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p05_4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p05_4.setEnabled(false);

        p05_5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p05_5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p05_5.setEnabled(false);

        p06_1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p06_1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p06_1.setEnabled(false);

        p06_2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p06_2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p06_2.setEnabled(false);

        p06_3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p06_3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p06_3.setEnabled(false);

        p06_4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p06_4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p06_4.setEnabled(false);

        p06_5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p06_5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p06_5.setEnabled(false);

        p07_1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p07_1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p07_1.setEnabled(false);

        p07_2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p07_2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p07_2.setEnabled(false);

        p07_3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p07_3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p07_3.setEnabled(false);

        p07_4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p07_4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p07_4.setEnabled(false);

        p07_5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p07_5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p07_5.setEnabled(false);

        p08_1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p08_1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p08_1.setEnabled(false);

        p08_2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p08_2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p08_2.setEnabled(false);

        p08_3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p08_3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p08_3.setEnabled(false);

        p08_4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p08_4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p08_4.setEnabled(false);

        p08_5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p08_5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p08_5.setEnabled(false);

        p09_1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p09_1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p09_1.setEnabled(false);

        p09_2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p09_2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p09_2.setEnabled(false);

        p09_3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p09_3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p09_3.setEnabled(false);

        p09_4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p09_4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p09_4.setEnabled(false);

        p09_5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p09_5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p09_5.setEnabled(false);

        p10_1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p10_1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p10_1.setEnabled(false);

        p10_2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p10_2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p10_2.setEnabled(false);

        p10_3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p10_3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p10_3.setEnabled(false);

        p10_4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p10_4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p10_4.setEnabled(false);

        p10_5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        p10_5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        p10_5.setEnabled(false);

        javax.swing.GroupLayout pathPanelLayout = new javax.swing.GroupLayout(pathPanel);
        pathPanel.setLayout(pathPanelLayout);
        pathPanelLayout.setHorizontalGroup(
            pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pathPanelLayout.createSequentialGroup()
                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pathPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p01_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p01_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p01_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p01_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p01_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p02_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p02_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p02_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p02_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p02_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p03_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p03_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p03_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p03_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p03_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p04_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p04_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p04_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p04_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p04_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p05_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p05_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p05_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p05_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p05_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p06_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p06_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p06_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p06_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p06_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p07_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p07_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p07_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p07_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p07_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p08_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p08_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p08_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p08_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p08_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p09_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p09_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p09_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p09_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p09_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p10_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p10_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p10_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p10_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p10_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pathPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PathLabel)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pathPanelLayout.setVerticalGroup(
            pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pathPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PathLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pathPanelLayout.createSequentialGroup()
                            .addComponent(p10_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p10_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p10_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p10_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p10_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pathPanelLayout.createSequentialGroup()
                            .addComponent(p09_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p09_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p09_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p09_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p09_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pathPanelLayout.createSequentialGroup()
                            .addComponent(p08_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p08_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p08_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p08_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p08_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pathPanelLayout.createSequentialGroup()
                            .addComponent(p07_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p07_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p07_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p07_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p07_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pathPanelLayout.createSequentialGroup()
                            .addComponent(p06_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p06_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p06_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p06_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p06_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pathPanelLayout.createSequentialGroup()
                            .addComponent(p05_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p05_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p05_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p05_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p05_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pathPanelLayout.createSequentialGroup()
                            .addComponent(p04_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p04_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p04_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p04_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p04_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pathPanelLayout.createSequentialGroup()
                            .addComponent(p03_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p03_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p03_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p03_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p03_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pathPanelLayout.createSequentialGroup()
                            .addComponent(p02_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p02_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p02_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p02_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p02_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pathPanelLayout.createSequentialGroup()
                            .addComponent(p01_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p01_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p01_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p01_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(p01_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        standingAreaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        standingAreaLabel.setText("Standing Area");

        sa1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sa1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sa1.setEnabled(false);

        sa2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sa2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sa2.setEnabled(false);

        sa3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sa3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sa3.setEnabled(false);

        sa4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sa4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sa4.setEnabled(false);

        sa5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sa5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sa5.setEnabled(false);

        javax.swing.GroupLayout standingAreaPanelLayout = new javax.swing.GroupLayout(standingAreaPanel);
        standingAreaPanel.setLayout(standingAreaPanelLayout);
        standingAreaPanelLayout.setHorizontalGroup(
            standingAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(standingAreaPanelLayout.createSequentialGroup()
                .addGroup(standingAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(standingAreaPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(standingAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sa4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sa5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sa3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sa1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(standingAreaPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(standingAreaLabel)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        standingAreaPanelLayout.setVerticalGroup(
            standingAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(standingAreaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(standingAreaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(sa1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sa3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sa4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sa5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        storehousePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        storehouseLabel.setText("Storehouse");

        sh1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sh1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sh1.setEnabled(false);

        sh3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sh3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sh3.setEnabled(false);

        sh2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sh2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sh2.setEnabled(false);

        sh5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sh5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sh5.setEnabled(false);

        sh4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sh4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sh4.setEnabled(false);

        storehouseCornCobsLabel.setText("Corn Cobs");

        storehouseCornCobs.setText("0");
        storehouseCornCobs.setCaretColor(new java.awt.Color(0, 0, 0));
        storehouseCornCobs.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        storehouseCornCobs.setEnabled(false);

        ss.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ss.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ss.setEnabled(false);

        javax.swing.GroupLayout storehousePanelLayout = new javax.swing.GroupLayout(storehousePanel);
        storehousePanel.setLayout(storehousePanelLayout);
        storehousePanelLayout.setHorizontalGroup(
            storehousePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storehousePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(storehousePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storehouseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(storehousePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(ss, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(storehousePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sh4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sh5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sh2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sh3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sh1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(storehousePanelLayout.createSequentialGroup()
                        .addComponent(storehouseCornCobsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(storehouseCornCobs, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
                .addContainerGap())
        );
        storehousePanelLayout.setVerticalGroup(
            storehousePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storehousePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(storehouseLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(storehousePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(storehousePanelLayout.createSequentialGroup()
                        .addComponent(storehouseCornCobsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, storehousePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(storehouseCornCobs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addComponent(sh1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sh2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(storehousePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sh3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ss, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sh4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sh5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(farmersLabel)
                                    .addComponent(timeoutLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(timeout, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numFarmers, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(prepareBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(collectBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(returnBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cobsLabel)
                            .addComponent(stepLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(stopBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numCornCobs, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maxStep, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(exitBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cobsLabel1)
                        .addGap(186, 186, 186))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(storehousePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(standingAreaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pathPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(granaryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storehousePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(granaryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pathPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(standingAreaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(farmersLabel)
                    .addComponent(cobsLabel)
                    .addComponent(numFarmers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numCornCobs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cobsLabel1))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeoutLabel)
                    .addComponent(stepLabel)
                    .addComponent(timeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxStep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitBtn)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(prepareBtn)
                        .addComponent(startBtn)
                        .addComponent(collectBtn)
                        .addComponent(returnBtn)
                        .addComponent(stopBtn)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Event handler for the Prepare Button.
     * @param evt event caught by swing (not used).
     */
    private void prepareBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prepareBtnMouseClicked
        if(!this.prepareBtn.isEnabled()) {
            return;
        }
        System.out.println("[CC] Preparation requested.");

        // Update UI
        this.stopBtn.setEnabled(true);
        this.prepareBtn.setEnabled(false);
        this.numFarmers.setEnabled(false);
        this.numCornCobs.setEnabled(false);
        this.timeout.setEnabled(false);
        this.maxStep.setEnabled(false);

        // Send message to FI to place farmer IDs in respective Storehouse positions
        fiClient.send("prepareOrder;" + this.numFarmers.getValue() + ";" + this.numCornCobs.getValue() + ";"
                + this.maxStep.getValue() + ";" + this.timeout.getValue());
    }//GEN-LAST:event_prepareBtnMouseClicked

    /**
     * Event handler for the Start Button.
     * @param evt event caught by swing (not used).
     */
    private void startBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startBtnMouseClicked
        if(!this.startBtn.isEnabled()) {
            return;
        }
        System.out.println("[CC] Start requested.");

        // Update UI
        this.startBtn.setEnabled(false);

        // Send message to FI to update farmer positions (move to Path and then Granary)
        fiClient.send("startHarvestOrder");
    }//GEN-LAST:event_startBtnMouseClicked

    /**
     * Event handler for the Collect Button.
     * @param evt event caught by swing (not used).
     */
    private void collectBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_collectBtnMouseClicked
        if(!this.collectBtn.isEnabled()) {
            return;
        }
        System.out.println("[CC] Corn collection requested.");

        // Update UI
        this.collectBtn.setEnabled(false);

        // Send message to FI for farmers to grab corn cobs
        fiClient.send("collectOrder");
    }//GEN-LAST:event_collectBtnMouseClicked

    /**
     * Event handler for the Return Button.
     * @param evt event caught by swing (not used).
     */
    private void returnBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnBtnMouseClicked
        if(!this.returnBtn.isEnabled()) {
            return;
        }
        System.out.println("[CC] Return to storehouse requested.");

        // Update UI
        this.returnBtn.setEnabled(false);

        // Send message to FI to update farmer positions (move to Storehouse and then
        // deliver corn cobs)
        fiClient.send("returnOrder");
    }//GEN-LAST:event_returnBtnMouseClicked

    /**
     * Event handler for the Stop Button.
     * @param evt event caught by swing (not used).
     */
    private void stopBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopBtnMouseClicked
        if(!this.stopBtn.isEnabled()) {
            return;
        }
        System.out.println("[CC] Work stop requested.");

        // Update UI
        this.stopBtn.setEnabled(false);
        this.prepareBtn.setEnabled(false);
        this.startBtn.setEnabled(false);
        this.collectBtn.setEnabled(false);
        this.returnBtn.setEnabled(false);

        // Send message to FI for farmers to immediately stop what they are doing and go
        // back to the Storehouse
        fiClient.send("stopHarvestOrder");
    }//GEN-LAST:event_stopBtnMouseClicked

    /**
     * Event handler for the Exit Button.
     * @param evt event caught by swing (not used).
     */
    private void exitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMouseClicked
        if(!this.exitBtn.isEnabled()) {
            return;
        }
        System.out.println("[CC] Simulation shutdown requested.");

        // Send message to FI for farmers to kill themselves, close the sockets and end
        // the processes and UIs
        fiClient.send("endSimulationOrder");
    }//GEN-LAST:event_exitBtnMouseClicked

    /**
     * Main function responsible for enabling the Control Center GUI.
     * @param args the command line arguments.
     */
    public static void main(String args[]) {
        
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
            java.util.logging.Logger.getLogger(ControlCenter.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControlCenter.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControlCenter.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControlCenter.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ControlCenter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PathLabel;
    private javax.swing.JLabel cobsLabel;
    private javax.swing.JLabel cobsLabel1;
    private javax.swing.JButton collectBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel farmersLabel;
    private javax.swing.JTextField g1;
    private javax.swing.JTextField g2;
    private javax.swing.JTextField g3;
    private javax.swing.JTextField g4;
    private javax.swing.JTextField g5;
    private javax.swing.JTextField gc;
    private javax.swing.JTextField granaryCornCobs;
    private javax.swing.JLabel granaryCornCobsLabel;
    private javax.swing.JLabel granaryLabel;
    private javax.swing.JPanel granaryPanel;
    private javax.swing.JSpinner maxStep;
    private javax.swing.JSpinner numCornCobs;
    private javax.swing.JSpinner numFarmers;
    private javax.swing.JTextField p01_1;
    private javax.swing.JTextField p01_2;
    private javax.swing.JTextField p01_3;
    private javax.swing.JTextField p01_4;
    private javax.swing.JTextField p01_5;
    private javax.swing.JTextField p02_1;
    private javax.swing.JTextField p02_2;
    private javax.swing.JTextField p02_3;
    private javax.swing.JTextField p02_4;
    private javax.swing.JTextField p02_5;
    private javax.swing.JTextField p03_1;
    private javax.swing.JTextField p03_2;
    private javax.swing.JTextField p03_3;
    private javax.swing.JTextField p03_4;
    private javax.swing.JTextField p03_5;
    private javax.swing.JTextField p04_1;
    private javax.swing.JTextField p04_2;
    private javax.swing.JTextField p04_3;
    private javax.swing.JTextField p04_4;
    private javax.swing.JTextField p04_5;
    private javax.swing.JTextField p05_1;
    private javax.swing.JTextField p05_2;
    private javax.swing.JTextField p05_3;
    private javax.swing.JTextField p05_4;
    private javax.swing.JTextField p05_5;
    private javax.swing.JTextField p06_1;
    private javax.swing.JTextField p06_2;
    private javax.swing.JTextField p06_3;
    private javax.swing.JTextField p06_4;
    private javax.swing.JTextField p06_5;
    private javax.swing.JTextField p07_1;
    private javax.swing.JTextField p07_2;
    private javax.swing.JTextField p07_3;
    private javax.swing.JTextField p07_4;
    private javax.swing.JTextField p07_5;
    private javax.swing.JTextField p08_1;
    private javax.swing.JTextField p08_2;
    private javax.swing.JTextField p08_3;
    private javax.swing.JTextField p08_4;
    private javax.swing.JTextField p08_5;
    private javax.swing.JTextField p09_1;
    private javax.swing.JTextField p09_2;
    private javax.swing.JTextField p09_3;
    private javax.swing.JTextField p09_4;
    private javax.swing.JTextField p09_5;
    private javax.swing.JTextField p10_1;
    private javax.swing.JTextField p10_2;
    private javax.swing.JTextField p10_3;
    private javax.swing.JTextField p10_4;
    private javax.swing.JTextField p10_5;
    private javax.swing.JPanel pathPanel;
    private javax.swing.JButton prepareBtn;
    private javax.swing.JButton returnBtn;
    private javax.swing.JTextField sa1;
    private javax.swing.JTextField sa2;
    private javax.swing.JTextField sa3;
    private javax.swing.JTextField sa4;
    private javax.swing.JTextField sa5;
    private javax.swing.JTextField sh1;
    private javax.swing.JTextField sh2;
    private javax.swing.JTextField sh3;
    private javax.swing.JTextField sh4;
    private javax.swing.JTextField sh5;
    private javax.swing.JTextField ss;
    private javax.swing.JLabel standingAreaLabel;
    private javax.swing.JPanel standingAreaPanel;
    private javax.swing.JButton startBtn;
    private javax.swing.JLabel stepLabel;
    private javax.swing.JButton stopBtn;
    private javax.swing.JTextField storehouseCornCobs;
    private javax.swing.JLabel storehouseCornCobsLabel;
    private javax.swing.JLabel storehouseLabel;
    private javax.swing.JPanel storehousePanel;
    private javax.swing.JSpinner timeout;
    private javax.swing.JLabel timeoutLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * Auxiliary method to organize the text fields (positions) of all areas.
     */
    private void groupTextFields() {
        storehouseTextFields = new JTextField[teamSize];
        storehouseTextFields[0] = sh1;
        storehouseTextFields[1] = sh2;
        storehouseTextFields[2] = sh3;
        storehouseTextFields[3] = sh4;
        storehouseTextFields[4] = sh5;

        standingAreaTextFields = new JTextField[teamSize];
        standingAreaTextFields[0] = sa1;
        standingAreaTextFields[1] = sa2;
        standingAreaTextFields[2] = sa3;
        standingAreaTextFields[3] = sa4;
        standingAreaTextFields[4] = sa5;

        pathTextFields = new JTextField[pathSize][teamSize];
        pathTextFields[0][0] = p01_1;
        pathTextFields[0][1] = p01_2;
        pathTextFields[0][2] = p01_3;
        pathTextFields[0][3] = p01_4;
        pathTextFields[0][4] = p01_5;
        pathTextFields[1][0] = p02_1;
        pathTextFields[1][1] = p02_2;
        pathTextFields[1][2] = p02_3;
        pathTextFields[1][3] = p02_4;
        pathTextFields[1][4] = p02_5;
        pathTextFields[2][0] = p03_1;
        pathTextFields[2][1] = p03_2;
        pathTextFields[2][2] = p03_3;
        pathTextFields[2][3] = p03_4;
        pathTextFields[2][4] = p03_5;
        pathTextFields[3][0] = p04_1;
        pathTextFields[3][1] = p04_2;
        pathTextFields[3][2] = p04_3;
        pathTextFields[3][3] = p04_4;
        pathTextFields[3][4] = p04_5;
        pathTextFields[4][0] = p05_1;
        pathTextFields[4][1] = p05_2;
        pathTextFields[4][2] = p05_3;
        pathTextFields[4][3] = p05_4;
        pathTextFields[4][4] = p05_5;
        pathTextFields[5][0] = p06_1;
        pathTextFields[5][1] = p06_2;
        pathTextFields[5][2] = p06_3;
        pathTextFields[5][3] = p06_4;
        pathTextFields[5][4] = p06_5;
        pathTextFields[6][0] = p07_1;
        pathTextFields[6][1] = p07_2;
        pathTextFields[6][2] = p07_3;
        pathTextFields[6][3] = p07_4;
        pathTextFields[6][4] = p07_5;
        pathTextFields[7][0] = p08_1;
        pathTextFields[7][1] = p08_2;
        pathTextFields[7][2] = p08_3;
        pathTextFields[7][3] = p08_4;
        pathTextFields[7][4] = p08_5;
        pathTextFields[8][0] = p09_1;
        pathTextFields[8][1] = p09_2;
        pathTextFields[8][2] = p09_3;
        pathTextFields[8][3] = p09_4;
        pathTextFields[8][4] = p09_5;
        pathTextFields[9][0] = p10_1;
        pathTextFields[9][1] = p10_2;
        pathTextFields[9][2] = p10_3;
        pathTextFields[9][3] = p10_4;
        pathTextFields[9][4] = p10_5;

        granaryTextFields = new JTextField[teamSize];
        granaryTextFields[0] = g1;
        granaryTextFields[1] = g2;
        granaryTextFields[2] = g3;
        granaryTextFields[3] = g4;
        granaryTextFields[4] = g5;
    }
    
    /**
     * Initializes the farm infrastructure socket client.
     */
    @Override
    public void initFIClient() {
        this.fiClient = new SocketClient("localhost", 7777);
        this.fiClient.send("waitSimulationReady");
    }
    
    /**
     * Closes the farm infrastructure socket client.
     */
    @Override
    public void closeSocketClient() {
        this.fiClient.close();
    }

    /**
     * Closes the Control Center GUI and consequently the process.
     */
    @Override
    public void close() {
        this.setVisible(false);
        this.dispose();
        System.out.println("Control Center exited with success!");
    }
    
    /**
     * Enables the prepare button and the inputs so that the user can order a new run. 
     * Only happens when all farmers are waiting at the Storehouse.
     */
    public void enablePrepareBtn() {
        this.stopBtn.setEnabled(false);
        this.prepareBtn.setEnabled(true);
        this.startBtn.setEnabled(false);
        this.collectBtn.setEnabled(false);
        this.returnBtn.setEnabled(false);
        this.numFarmers.setEnabled(true);
        this.numCornCobs.setEnabled(true);
        this.timeout.setEnabled(true);
        this.maxStep.setEnabled(true);
    }

    /**
     * Enables the start button.
     * Only happens when all selected farmers are in the standing area.
     */
    @Override
    public void enableStartBtn() {
        this.startBtn.setEnabled(true);
        this.prepareBtn.setEnabled(false);
        this.collectBtn.setEnabled(false);
        this.returnBtn.setEnabled(false);
    }

    /**
     * Enables the collect button.
     * Only happens when all selected farmers are in the granary.
     */
    @Override
    public void enableCollectBtn() {
        this.collectBtn.setEnabled(true);
        this.startBtn.setEnabled(false);
        this.prepareBtn.setEnabled(false);
        this.returnBtn.setEnabled(false);
    }

    /**
     * Enables return button.
     * Only happens when all selected farmers have collected the cobs.
     */
    @Override
    public void enableReturnBtn() {
        this.returnBtn.setEnabled(true);
        this.startBtn.setEnabled(false);
        this.collectBtn.setEnabled(false);
        this.prepareBtn.setEnabled(false);
    }

    /**
     * Presents the farmer id in the respective position of the Storehouse.
     * @param farmerId int identifying the farmer entering the position.
     * @param position int identifying the Storehouse position.
     */
    @Override
    public void presentFarmerInStorehouse(int farmerId, int position) {
        clearAll(farmerId);
        storehouseTextFields[position].setText("" + farmerId);
    }

    /**
     * Clears the Storehouse of a specific farmer id.
     * @param farmerId int identifying the farmer to clear from the Storehouse.
     */
    private void clearStorehouse(int farmerId) {
        for (int i = 0; i < teamSize; i++) {
            if (!(storehouseTextFields[i].getText()).equals("")
                    && Integer.valueOf(storehouseTextFields[i].getText()) == farmerId) {
                storehouseTextFields[i].setText("");
                return;
            }
        }
    }

    /**
     * Presents the farmer id in the respective position of the Standing Area.
     * @param farmerId int identifying the farmer entering the position.
     * @param position int identifying the Standing area position.
     */
    @Override
    public void presentFarmerInStandingArea(int farmerId, int position) {
        clearAll(farmerId);
        standingAreaTextFields[position].setText("" + farmerId);
    }

    /**
     * Clears the Standing area of a specific farmer id.
     * @param farmerId int identifying the farmer to clear from the Standing area.
     */
    private void clearStandingArea(int farmerId) {
        for (int i = 0; i < teamSize; i++) {
            if (!(standingAreaTextFields[i].getText()).equals("")
                    && Integer.valueOf(standingAreaTextFields[i].getText()) == farmerId) {
                standingAreaTextFields[i].setText("");
                return;
            }
        }
    }

    /**
     * Presents the farmer id in the respective position of the Path Area.
     * @param farmerId int identifying the farmer entering the position.
     * @param position int identifying the Path area line position.
     * @param column int identifying the Path area column position.
     */
    @Override
    public void presentFarmerInPath(int farmerId, int position, int column) {
        clearAll(farmerId);
        pathTextFields[column][position].setText("" + farmerId);
    }

    /**
     * Clears the Path area of a specific farmer id.
     * @param farmerId int identifying the farmer to clear from the Path area.
     */
    private void clearPath(int farmerId) {
        for (int c = 0; c < this.pathSize; c++) {
            for (int p = 0; p < this.teamSize; p++) {
                int currentInt;
                try {
                    currentInt = Integer.valueOf(pathTextFields[c][p].getText());
                } catch (NumberFormatException ex) {
                    currentInt = -1;
                }
                if (currentInt == farmerId) {
                    pathTextFields[c][p].setText("");
                    return;
                }
            }
        }
    }

    /**
     * Presents the farmer id in the respective position of the Granary Area.
     * @param farmerId int identifying the farmer entering the position 
     * @param position int identifying the Granary area position
     */
    @Override
    public void presentFarmerInGranary(int farmerId, int position) {
        clearAll(farmerId);
        granaryTextFields[position].setText("" + farmerId);
    }

    /**
     * Clears the Granary area of a specific farmer id.
     * @param farmerId int identifying the farmer to clear from the Granary area.
     */
    private void clearGranary(int farmerId) {
        for (int i = 0; i < teamSize; i++) {
            if (!(granaryTextFields[i].getText()).equals("")
                    && Integer.valueOf(granaryTextFields[i].getText()) == farmerId) {
                granaryTextFields[i].setText("");
                return;
            }
        }
    }

    /**
     * Presents the farmer id in the respective position of the Collecting Area.
     * @param farmerId int identifying the farmer entering the position. 
     */
    @Override
    public void presentCollectingFarmer(int farmerId) {
        clearAll(farmerId);
        gc.setText("" + farmerId);
    }

    /**
     * Clears the Collecting area of a specific farmer id.
     * @param farmerId int identifying the farmer to clear from the Collecting area.
     */
    private void clearCollectionArea(int farmerId) {
        if (!(gc.getText()).equals("") && Integer.valueOf(gc.getText()) == farmerId) {
            gc.setText("");
            return;
        }
    }

    /**
     * Presents the farmer id in the respective position of the Storing Area.
     * @param farmerId int identifying the farmer entering the position. 
     */
    @Override
    public void presentStoringFarmer(int farmerId) {
        clearAll(farmerId);
        ss.setText("" + farmerId);
    }

    /**
     * Clears the Storing area of a specific farmer id.
     * @param farmerId int identifying the farmer to clear from the Storing area.
     */
    private void clearStoringArea(int farmerId) {
        if (!(ss.getText()).equals("") && Integer.valueOf(ss.getText()) == farmerId) {
            ss.setText("");
            return;
        }
    }

    /**
     * Clears all the area of a specific farmer id.
     * @param farmerId int identifying the farmer to clear all the areas.
     */
    private void clearAll(int farmerId) {
        clearStorehouse(farmerId);
        clearStandingArea(farmerId);
        clearPath(farmerId);
        clearGranary(farmerId);
        clearCollectionArea(farmerId);
        clearStoringArea(farmerId);
    }

    /**
     * Updates the corn cobs number in the Granary area.
     * @param actualNumber int representing the current corn cobs number.
     */
    @Override
    public void updateGranaryCornCobs(int actualNumber) {
        granaryCornCobs.setText(String.valueOf(actualNumber));
    }

    /**
     * Updates the corn cobs number in the Storehouse area.
     * @param actualNumber int representing the current corn cobs number.
     */
    @Override
    public void updateStorehouseCornCobs(int actualNumber) {
        storehouseCornCobs.setText(String.valueOf(actualNumber));
    }
}
