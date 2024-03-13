package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class meterinfo extends JFrame implements ActionListener {
JLabel heading,Meterno,meterNumText,MeterTyp,PhaseCode,day,note;
JButton submit;
Choice MeterLoccho, meterTypcho,PhaseCodecho,BillTypcho;
String meternumber;


    meterinfo(String meternumber ){
       // super("Meter Information");
        this.meternumber= meternumber;

        JPanel panel= new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(253, 219, 123));
        add(panel);


        heading = new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);


        Meterno = new JLabel("Meter Number");
        Meterno.setBounds(40,74,130,20);
        Meterno.setFont(new Font("Tahome",Font.PLAIN,20));
        panel.add(Meterno);


        meterNumText=new JLabel(meternumber);
        meterNumText.setBounds(180,80,150,20);
        panel.add(meterNumText);

        JLabel meterloc= new JLabel("Meter Number");
        meterloc.setBounds(50,120,100,20);
        panel.add(meterloc);

        MeterLoccho= new Choice();
        MeterLoccho.add("Outside");
        MeterLoccho.add("Inside");
        MeterLoccho.setBounds(180,120,150,20);
        panel.add(MeterLoccho);


        MeterTyp=new JLabel("Meter Type");
        MeterTyp.setBounds(50,160,100,20);
        panel.add(MeterTyp);

        meterTypcho=new Choice();
        meterTypcho.add("Electric Meter");
        meterTypcho.add("Solar Meter");
        meterTypcho.add("Smart Meter");
        meterTypcho.setBounds(180,160,150,20);
        panel.add(meterTypcho);

        PhaseCode = new JLabel("Phase Code");
        PhaseCode.setBounds(50,200,100,20);
        panel.add(PhaseCode);

        PhaseCodecho=new Choice();
        PhaseCodecho.add("011");
        PhaseCodecho.add("022");
        PhaseCodecho.add("033");
        PhaseCodecho.add("044");
        PhaseCodecho.add("055");
        PhaseCodecho.add("066");
        PhaseCodecho.add("077");
        PhaseCodecho.add("088");
        PhaseCodecho.add("099");
        PhaseCodecho.setBounds(180,200,150,20);
        panel.add(PhaseCodecho);

        JLabel BillTyp= new JLabel("Bill Type");
        BillTyp.setBounds(50,240,100,20);
        panel.add(BillTyp);
        BillTypcho = new Choice();
        BillTypcho.add("Normal");
        BillTypcho.add("Industrial");
        BillTypcho.setBounds(180,240,150,20);
        panel.add(BillTypcho);

        day= new JLabel("30 Days Billing Time...  ");
        day.setBounds(50,280,150,20);
        panel.add(day);

        note= new JLabel("Note:-");
        note.setBounds(50,320,100,20);
        panel.add(note);


        JLabel not1= new JLabel("By default bill is calculated for 30 days only");
        not1.setBounds(50,340,300,20);
        panel.add(not1);

        submit=new JButton("Submit");
        submit.setBounds(220,390,100,20);
        submit.setForeground(Color.black);
        submit.setBackground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);
        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon I1 =new ImageIcon(ClassLoader.getSystemResource("Icon/details.png"));
        Image i2= I1.getImage().getScaledInstance(227,200,Image.SCALE_DEFAULT);
        ImageIcon I= new ImageIcon(i2);
        JLabel imgLabel= new JLabel(I);
        add(imgLabel,"East");

        setSize(700,500);
        setLocation(400,170);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
if (e.getSource()==submit) {
    String smeternum = meternumber;
    String smeterLoc = MeterLoccho.getSelectedItem();
    String smetertype = meterTypcho.getSelectedItem();
    String sphasecode = PhaseCodecho.getSelectedItem();
    String sbilltype=BillTypcho.getSelectedItem();
    String sday="30";

    String query_meterinfo="insert into meter_info values('"+smeternum+"' ,'"+smeterLoc+"' , '"+smetertype+"' , '"+sphasecode+"' , '"+sbilltype+"' , '"+sday+"'   )";
try {

    Database d = new Database();
    d.statement.executeUpdate(query_meterinfo);
    JOptionPane.showMessageDialog(null,"Meter information submitted successfully ");
    setVisible(false);

}

catch (Exception E){
   E.printStackTrace();
}
}else {
    setVisible(false);
}
    }

    public static void main(String[] args) {
        new meterinfo("");
    }
}
