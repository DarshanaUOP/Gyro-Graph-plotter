import com.fazecast.jSerialComm.SerialPort;
import javafx.scene.chart.XYChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Scanner;

class GraphDraw extends JFrame {

    static SerialPort choosenPort;
    static String[] line;
    static String selectedOption="2";

    private JPanel panBack,panArd;
    private JLabel lblArduino,lblRotX,lblRotY,lblRotZ,
            lblRotation,
                lblRMaxDataX,lblRMaxDataY,lblRMaxDataZ,
            lblAcc,
                lblAccMaxDataX,lblAccMaxDataY,lblAccMaxDataZ,
    lblgForceZ,
            lblNamePlate;
    private JButton bConn,bClear;
    private JComboBox comPort;
    private JRadioButton radBtRotation,radBtAcceleration;
    private ButtonGroup radBtGroup;
    XYChart chart;
    GridBagConstraints conn = new GridBagConstraints();


    public GraphDraw(){

        Font lableFont = new Font(null,Font.BOLD,17);
        Color lableFontColor = Color.GREEN;

        panBack = new JPanel(new GridBagLayout());
        panBack.setVisible(true);
        panBack.setEnabled(true);
        panBack.setBackground(Color.black);
        add(panBack);

        panArd = new JPanel(new GridBagLayout());
        panArd.setVisible(true);
        panArd.setEnabled(true);
        panArd.setBackground(Color.black);
        conn.gridx = 0;
        conn.gridy = 0;
        conn.gridwidth = 4;
        conn.gridheight = 1;
        panBack.add(panArd,conn);

        lblArduino = new JLabel("Arduino on ");
        lblArduino.setVisible(true);
        lblArduino.setForeground(lableFontColor);
        lblArduino.setFont(lableFont);
        conn.gridx = 0;
        conn.gridy = 0;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        panArd.add(lblArduino,conn);

        bClear = new JButton("Clear");
        bClear.setEnabled(false);
        conn.gridx = 4;
        conn.gridy = 0;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        panArd.add(bClear,conn);

        lblNamePlate = new JLabel("Created by Darshana Ariyarathna on Monday, December 11, 2017 12:56:42 AM | darshana.uop@gmail.com | +94774901245");
        lblNamePlate.setForeground(Color.green);
        conn.gridx = 5;
        conn.gridy = 0;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        panArd.add(lblNamePlate,conn);

        comPort = new JComboBox();
        comPort.setVisible(true);
        conn.gridx = 1;
        conn.gridy = 0;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        panArd.add(comPort,conn);

        bConn = new JButton("Start");
        bConn.setEnabled(true);
        conn.gridx = 2;
        conn.gridy = 0;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        panArd.add(bConn,conn);

        //Getting com ports;
        SerialPort[] portNames = SerialPort.getCommPorts();
        for (int i = 0 ;i<portNames.length;i++)
            comPort.addItem(portNames[i].getSystemPortName());

        //Create graph ROTX
        XYSeries seriesX = new XYSeries("ROT X");
        XYSeriesCollection dataset = new XYSeriesCollection(seriesX);
        JFreeChart chart =ChartFactory.createXYLineChart("X","Time","Angle", dataset);
        conn.gridx = 0;
        conn.gridy = 1;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(new ChartPanel(chart),conn);

        //Create graph ROTY
        XYSeries seriesY = new XYSeries("ROT Y");
        XYSeriesCollection datasetY = new XYSeriesCollection(seriesY);
        JFreeChart chartY =ChartFactory.createXYLineChart("Y","Time","Angle", datasetY);
        conn.gridx = 1;
        conn.gridy = 1;
        conn.gridwidth = 2;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(new ChartPanel(chartY),conn);

        //Create graph ROTZ
        XYSeries seriesZ = new XYSeries("ROT Z");
        XYSeriesCollection datasetZ = new XYSeriesCollection(seriesZ);
        JFreeChart chartZ =ChartFactory.createXYLineChart("Z","Time","Angle", datasetZ);
        chartZ.setAntiAlias(true);
        conn.gridx = 0;
        conn.gridy = 2;
        conn.gridwidth = 1;
        conn.gridheight = 13;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(new ChartPanel(chartZ),conn);


        lblRotX = new JLabel("X | Rotate : 0 | Gravity 0");
        lblRotX.setFont(lableFont);
        lblRotX.setForeground(lableFontColor);
        conn.gridx = 1;
        conn.gridy = 3;
        conn.gridwidth = 2;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblRotX,conn);

        lblRotY = new JLabel("Y | Rotate : 0 | Gravity 0");
        lblRotY.setFont(lableFont);
        lblRotY.setForeground(lableFontColor);
        conn.gridx = 1;
        conn.gridy = 4;
        conn.gridwidth = 2;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblRotY,conn);

        lblRotZ = new JLabel("Z | Rotate : 0 | Gravity 0");
        lblRotZ.setFont(lableFont);
        lblRotZ.setForeground(lableFontColor);
        conn.gridx = 1;
        conn.gridy = 5;
        conn.gridwidth = 2;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblRotZ,conn);

        radBtRotation = new JRadioButton("Show Rotation",false);
        radBtRotation.setFont(lableFont);
        radBtRotation.setBackground(Color.black);
        radBtRotation.setForeground(Color.WHITE);
        conn.gridx = 1;
        conn.gridy = 2;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(radBtRotation,conn);

        radBtAcceleration = new JRadioButton("Show Gravity",true);
        radBtAcceleration.setFont(lableFont);
        radBtAcceleration.setForeground(Color.WHITE);
        radBtAcceleration.setBackground(Color.black);
        conn.gridx = 2;
        conn.gridy = 2;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(radBtAcceleration,conn);

        radBtGroup = new ButtonGroup();
        radBtGroup.add(radBtRotation);
        radBtGroup.add(radBtAcceleration);

        lblRotation = new JLabel("Rotation Data");
        lblRotation.setBackground(Color.black);
        lblRotation.setForeground(Color.white);
        lblRotation.setFont(lableFont);
        conn.gridx = 1;
        conn.gridy = 6;
        conn.gridwidth = 2;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblRotation,conn);

        lblRMaxDataX=new JLabel("X | Max 0 | Min 0");
        lblRMaxDataX.setBackground(Color.black);
        lblRMaxDataX.setForeground(Color.green);
        lblRMaxDataX.setFont(lableFont);
        conn.gridx = 2;
        conn.gridy = 7;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblRMaxDataX,conn);

        lblRMaxDataY=new JLabel("Y | Max 0 | Min 0");
        lblRMaxDataY.setBackground(Color.black);
        lblRMaxDataY.setForeground(Color.green);
        lblRMaxDataY.setFont(lableFont);
        conn.gridx = 2;
        conn.gridy = 8;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblRMaxDataY,conn);

        lblRMaxDataZ=new JLabel("Z | Max 0 | Min 0");
        lblRMaxDataZ.setBackground(Color.black);
        lblRMaxDataZ.setForeground(Color.green);
        lblRMaxDataZ.setFont(lableFont);
        conn.gridx = 2;
        conn.gridy = 9;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblRMaxDataZ,conn);

        lblAcc = new JLabel("Gravity Data");
        lblAcc.setBackground(Color.black);
        lblAcc.setForeground(Color.white);
        lblAcc.setFont(lableFont);
        conn.gridx = 1;
        conn.gridy = 10;
        conn.gridwidth = 2;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblAcc,conn);

        lblAccMaxDataX=new JLabel("X | Max 0 | Min 0");
        lblAccMaxDataX.setBackground(Color.black);
        lblAccMaxDataX.setForeground(Color.green);
        lblAccMaxDataX.setFont(lableFont);
        conn.gridx = 2;
        conn.gridy = 11;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblAccMaxDataX,conn);

        lblAccMaxDataY=new JLabel("Y | Max 0 | Min 0");
        lblAccMaxDataY.setBackground(Color.black);
        lblAccMaxDataY.setForeground(Color.green);
        lblAccMaxDataY.setFont(lableFont);
        conn.gridx = 2;
        conn.gridy = 12;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblAccMaxDataY,conn);

        lblAccMaxDataZ=new JLabel("Z | Max 0 | Min 0");
        lblAccMaxDataZ.setBackground(Color.black);
        lblAccMaxDataZ.setForeground(Color.green);
        lblAccMaxDataZ.setFont(lableFont);
        conn.gridx = 2;
        conn.gridy = 13;
        conn.gridwidth = 1;
        conn.gridheight = 1;
        conn.fill = GridBagConstraints.BOTH;
        panBack.add(lblAccMaxDataZ,conn);

        radBtRotation.addItemListener(new HandlerClass("1") {
        });
        radBtAcceleration.addItemListener(new HandlerClass("2") {
        });

        bConn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bConn.getText().equals("Start")){
                    try {
                        choosenPort = SerialPort.getCommPort(comPort.getSelectedItem().toString());
                        choosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
                    }catch(Exception e1){
                        JOptionPane.showMessageDialog(null,"Error in connect to Arduino \n"+e1.getMessage(),"Communication Error",JOptionPane.PLAIN_MESSAGE);
                    }
                    if (choosenPort.openPort()){
                        bConn.setText("Stop");
                        comPort.setEnabled(false);

                    }
                    Thread thread = new Thread(){
                        @Override
                        public void run(){
                            Scanner scanner = new Scanner(choosenPort.getInputStream());
                            int t=0;
                            double x=0,x1=0,y=0,y1=0,z=0,z1=0;

                            double rotXmin=0,rotYmin=0,rotZmin=0;
                            double rotXmax=0,rotYmax=0,rotZmax=0;

                            double accXmin=0,accYmin=0,accZmin=0;
                            double accXmax=0,accYmax=0,accZmax=0;

                            while (scanner.hasNextLine()){
                                line = scanner.nextLine().split(",");
                                try{
                                    x = Double.valueOf(line[0]);
                                    y = Double.valueOf(line[1]);
                                    z = Double.valueOf(line[2]);
                                    x1 = Double.valueOf(line[3]);
                                    y1 = Double.valueOf(line[4]);
                                    z1 = Double.valueOf(line[5]);

                                    if (selectedOption=="1") {

                                        seriesX.add(t++, x);
                                        seriesY.add(t++, y);
                                        seriesZ.add(t++, z);

                                    }else if (selectedOption=="2"){

                                        seriesX.add(t++, x1);
                                        seriesY.add(t++, y1);
                                        seriesZ.add(t++, z1);
                                    }
                                    lblRotX.setText("X | Rotate : "+x+" | Gravity "+x1);
                                    lblRotY.setText("Y | Rotate : "+y+" | Gravity "+y1);
                                    lblRotZ.setText("Z | Rotate : "+z+" | Gravity "+z1);

                                    if (x<= rotXmin){rotXmin =x;}
                                    if (y<= rotYmin){rotYmin =y;}
                                    if (z<= rotZmin){rotZmin =z;}

                                    if (x>= rotXmax){rotXmax =x;}
                                    if (y>= rotYmax){rotYmax =y;}
                                    if (z>= rotZmax){rotZmax =z;}

                                    lblRMaxDataX.setText("X | Max "+rotXmax+" | Min "+rotXmin);
                                    lblRMaxDataY.setText("Y | Max "+rotYmax+" | Min "+rotYmin);
                                    lblRMaxDataZ.setText("Z | Max "+rotZmax+" | Min "+rotZmin);

                                    if (x1<= accXmin){accXmin =x1;}
                                    if (y1<= accYmin){accYmin =y1;}
                                    if (z1<= accZmin){accZmin =z1;}

                                    if (x1>= accXmax){accXmax =x1;}
                                    if (y1>= accYmax){accYmax =y1;}
                                    if (z1>= accZmax){accZmax =z1;}

                                    lblAccMaxDataX.setText("X | Max "+accXmax+" | Min "+accXmin);
                                    lblAccMaxDataY.setText("Y | Max "+accYmax+" | Min "+accYmin);
                                    lblAccMaxDataZ.setText("Z | Max "+accZmax+" | Min "+accZmin);


                                }catch(Exception e){}
                            }
                        }
                    };
                    thread.start();
                }else if (bConn.getText().equals("Stop")){

                    choosenPort.closePort();
                    bClear.setEnabled(true);
                    bConn.setEnabled(false);
                    bConn.setText("Start");
                }
            }
        });
        bClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seriesX.clear();
                seriesY.clear();
                seriesZ.clear();

                bConn.setEnabled(true);
                bClear.setEnabled(false);
                comPort.setEnabled(true);

                lblRotX.setText("X | Rotate : 0 | Gravity 0");
                lblRotY.setText("Y | Rotate : 0 | Gravity 0");
                lblRotZ.setText("Z | Rotate : 0 | Gravity 0");

                lblRMaxDataX.setText("X | Max 0 | Min 0");
                lblRMaxDataY.setText("Y | Max 0 | Min 0");
                lblRMaxDataZ.setText("Z | Max 0 | Min 0");

                lblAccMaxDataX.setText("X | Max 0 | Min 0");
                lblAccMaxDataY.setText("Y | Max 0 | Min 0");
                lblAccMaxDataZ.setText("Z | Max 0 | Min 0");
            }
        });
    }
    private class HandlerClass implements ItemListener{
        private String selsected;

        public HandlerClass(String slItem){
            selsected=slItem;
        }
        @Override
        public void itemStateChanged(ItemEvent e){
            selectedOption=selsected;
            //System.out.println(selsected);
        }
    }
}