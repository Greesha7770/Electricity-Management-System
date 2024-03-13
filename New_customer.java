package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class New_customer extends JFrame implements ActionListener {

    JLabel heading,CustomerName,meterText,meterNo,address,city,state,email,phone;
    TextField nameText,addressText,cityText,stateText,emailText,phoneText;
    JButton Next,Cancel;
    String meterNumber;

    New_customer(){
super("New Customer");

JPanel panel= new JPanel();
panel.setLayout(null);
panel.setBackground(new Color(253, 219, 123));
add(panel);

heading = new JLabel("New Customer");
heading.setBounds(100,10,200,20);
heading.setFont(new Font("Tahoma",Font.BOLD,20));
panel.add(heading);

CustomerName= new JLabel(("Customer Name"));
CustomerName.setBounds(50,80,100,20);
panel.add(CustomerName);

nameText=new TextField();
nameText.setBounds(180,80,150,20);
panel.add(nameText);

meterNo = new JLabel("Meter Number");
meterNo.setBounds(50,120,100,20);
panel.add(meterNo);

meterText=new JLabel("");
meterText.setBounds(180,120,150,20);
panel.add(meterText);

Random rand= new Random();
long number= rand.nextLong() % 1000000;
meterText.setText(""+Math.abs(number));



address= new JLabel("Address");
address.setBounds(50,160,100,20);
panel.add(address);

addressText=new TextField("");
addressText.setBounds(180,160,150,20);
panel.add(addressText);

city= new JLabel("City");
city.setBounds(50,200,100,20);
panel.add(city);

cityText=new TextField("");
cityText.setBounds(180,200,150,20);
panel.add(cityText);

state= new JLabel("State");
state.setBounds(50,240,100,20);
panel.add(state);

stateText=new TextField("");
stateText.setBounds(180,240,150,20);
panel.add(stateText);



email= new JLabel("Email");
email.setBounds(50,280,100,20);
panel.add(email);

emailText=new TextField("");
emailText.setBounds(180,280,150,20);
panel.add(emailText);



phone= new JLabel("Phone Number");
phone.setBounds(50,320,100,20);
panel.add(phone);

phoneText=new TextField("");
phoneText.setBounds(180,320,150,20);
panel.add(phoneText);

Next= new JButton("Next");
Next.setBounds(120,390,100,25);
Next.setBackground(Color.BLACK);
Next.setForeground(Color.white);
Next.addActionListener(this);
panel.add(Next);

Cancel= new JButton("Cancel");
Cancel.setBounds(240,390,100,25);
Cancel.setBackground(Color.BLACK);
Cancel.setForeground(Color.white);
Cancel.addActionListener(this);
panel.add(Cancel);

setLayout(new BorderLayout());
add(panel,"Center");

ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("Icon/boy.png"));
Image i2=i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
ImageIcon i3= new ImageIcon(i2);
JLabel img= new JLabel(i3);
add(img,"West");
setSize(700,500);
setLocation(400,170);

setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==Next)
        {
            String sname=nameText.getText();
            String smeter=meterText.getText();
            String saddress= addressText.getText();
            String scity= cityText.getText();
            String sstate=stateText.getText();
            String semail=emailText.getText();
            String sphone=phoneText.getText();

            String query_customer="insert into new_customer values('"+sname+"' , '"+smeter+"', '"+saddress+"', '"+scity+"','"+sstate+"', '"+semail+"','"+sphone+"' )";
            String query_signup="insert into signup values('"+smeter+"','','"+sname+"','','')";

            try{
                Database d= new Database();
                d.statement.executeUpdate(query_customer);
                d.statement.executeUpdate(query_signup);
                JOptionPane.showMessageDialog(null,"Customer Details added successfully");
                setVisible(false);
                new meterinfo(smeter);

            }

            catch (Exception e1){
e1.printStackTrace();
            }

        }else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
new New_customer();
    }
}
