import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SFrame extends JFrame{
    private Server serv;
    Container cp;

//    private JPanel jpn1 = new JPanel();
    private JTextArea jta =new JTextArea();
    private JScrollPane jsp = new JScrollPane(jta);


    private JPanel jpn2 =new JPanel();
    JButton jbtns[] = new JButton[9];


    private JPanel jpn3 =new JPanel();
    private JLabel jlab1 =new JLabel("Server IP:");
    private JLabel jlab2 = new JLabel("10.51.3.69");
    private JLabel jlab3 =new JLabel("Port:");
    private JTextField jtfLeft = new JTextField();
    private JButton jbtnStart = new JButton("Start");
    private JButton jbtnStop = new JButton("Stop");
    private JButton jbtnExit = new JButton("Exit");

    private JPanel jpn4 = new JPanel();
    private JTextField jtfDown =new JTextField();
    private JButton jbtnSent = new JButton("Sent");


    public SFrame(){
        this.setTitle("Server");
        initComp();
    }
    private void initComp(){

        this.setBounds(300,200,550,500);
        cp=this.getContentPane();
        cp.setLayout(new BorderLayout());
//        jpn1.add(jsp);
        jta.setEditable(false);
        cp.add(jsp,BorderLayout.WEST);
        jsp.setPreferredSize(new Dimension(100,200));

        jpn2.setLayout(new GridLayout(3,3));
        for(int i=0;i<9;i++) {
            jbtns[i]=new JButton();
            jbtns[i].setOpaque(true);
            jbtns[i].setEnabled(false);
            jbtns[i].setBackground(Color.CYAN);
            jpn2.add(jbtns[i]);
        }
        cp.add(jpn2,BorderLayout.CENTER);

        jpn3.setLayout(new GridLayout(7,1));
        jpn3.add(jlab1);
        jpn3.add(jlab2);
        jpn3.add(jlab3);
        jpn3.add(jtfLeft);
        jpn3.add(jbtnStart);
        jpn3.add(jbtnStop);
        jbtnExit.setOpaque(true);
        jbtnExit.setBackground(Color.red);
        jpn3.add(jbtnExit);
        cp.add(jpn3,BorderLayout.EAST);

        jpn4.setLayout(new BorderLayout());
        jpn4.add(jtfDown,BorderLayout.CENTER);
        jpn4.add(jbtnSent,BorderLayout.EAST);
        cp.add(jpn4,BorderLayout.SOUTH);

        jbtnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                serv = new Server(SFrame.this);
                serv.start();
                jta.append("Waiting connect in ...\n");
                ((JButton)ae.getSource()).setEnabled(false);
            }
        });
        jbtnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        jbtnSent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                serv.send2client(jtfDown.getText()+"\n");
                jtfDown.setText("");
            }
        });

    }
    public void addMsg(String inStr){
        jta.append("Client:"+inStr+"\n");
    }
    public static void main(String argv[]){
        SFrame sFrm = new SFrame();
        sFrm.setVisible(true);
    }
}
