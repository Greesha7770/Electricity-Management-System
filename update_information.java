package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.awt.geom.RectangularShape;
import java.sql.ResultSet;

public class update_information extends JFrame implements ActionListener {
    JLabel nameText;
    JLabel name;
    JTextField addressText,cityText,stateText,emailText,phoneText;
    String meter;
    JButton update,cancel;
    update_information(String meter){

        super("UPDATE INFORMATION");
   this.meter=meter;
   setBounds(400,150,777,450);
   getContentPane().setBackground(new Color(229,255,227));


   JLabel heading = new JLabel("Update Customer Information");
   heading.setBounds(50,10,400,40);
   heading.setFont(new Font("serif",Font.BOLD,20));
   add(heading);


         name = new JLabel("Name");
        name.setBounds(30,70,100,20);

        add(name);

       nameText = new JLabel("");
        nameText.setBounds(150,70,200,20);
        add(nameText);


        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30,110,150,20);
        add(meterNo);

        JLabel meterText = new JLabel("");
        meterText.setBounds(150,110,100,20);
        add(meterText);

        JLabel address= new JLabel("Address");
        address.setBounds(30,150,200,20);
        add(address);

         addressText= new JTextField();
        addressText.setBounds(150,150,200,20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(30,190,100,20);
        add(city);

        cityText = new JTextField();
        cityText.setBounds(150,190,200,20);
        add(cityText);

        JLabel state= new JLabel("State");
        state.setBounds(30,230,100,20);
        add(state);


        stateText = new JTextField();
        stateText.setBounds(150,230,200,20);
        add(stateText);


        JLabel email= new JLabel("Email");
        email.setBounds(30,270,100,20);
        add(email);


        emailText = new JTextField();
        emailText.setBounds(150,270,200,20);
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(30,310,100,20);
        add(phone);


        phoneText = new JTextField();
        phoneText.setBounds(150,310,200,20);
        add(phoneText);

        try{

            Database d= new Database();
            ResultSet resultSet= d.statement.executeQuery("select * from new_customer where meter_no = '"+meter+"'");
            if (resultSet.next()){

                nameText.setText(resultSet.getString("name"));
                meterText.setText(resultSet.getString("meter_no"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone"));

            }


        }
        catch(Exception E){
            E.printStackTrace();
        }


        update= new JButton("Update");
        update.setForeground(Color.white);
        update.setBackground(new Color(33,106,145));
        update.setBounds(50,360,120,25);
        update.addActionListener(this);
        add(update);



        cancel= new JButton("Cancel");
        cancel.setBackground(new Color(33,106,145));
        cancel.setForeground(Color.white);
        cancel.setBounds(200,360,120,25);
        cancel.addActionListener(this);
        add(cancel);


        ImageIcon a1=new ImageIcon(ClassLoader.getSystemResource("Icon/update.png"));
        Image a2=a1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(a2);
        JLabel img= new JLabel(i3);
        img.setBounds(360,0,400,410);
        add(img);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==update){


            String saddress= addressText.getText();
            String scity=cityText.getText();
            String sstate=stateText.getText();
            String semail=emailText.getText();
            String sphone=phoneText.getText();


            try{
                Database d= new Database();
d.statement.executeUpdate("update new_customer set address = '"+saddress+"', city = '"+scity+"' , state = '"+ sstate+"' , email = '"+semail+"'  , phone = '"+sphone+"' where meter_no='"+meter+"' ");

            JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
            setVisible(false);
            }

            catch(Exception E){

E.printStackTrace();

            }
        }

        else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {

       new  update_information("");
    }

}
