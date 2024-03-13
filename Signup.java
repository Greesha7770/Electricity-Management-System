package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.BitSet;

public class Signup extends JFrame  implements ActionListener {
Choice LoginAsCho;

JButton Create , Back;
TextField meter, EmployerText,UserNameText,NameText,PassWordText;

    Signup(){



        super("Signup View");

        getContentPane().setBackground(new Color(187, 210, 245));
        JLabel created =new JLabel("Create Account As");
        created.setBounds(60,50,125,20);
        add(created);

        LoginAsCho = new Choice();
        LoginAsCho.add("Admin");
        LoginAsCho.add("Customer");
        LoginAsCho.setBounds(250,50,120,20);
        add(LoginAsCho);

        JLabel meterNo =new JLabel("Meter Number");
        meterNo.setBounds(60,100,100,20);
        meterNo.setVisible(false);
        add(meterNo);

        meter= new TextField();
        meter.setBounds(250,100,120,20);
        meter.setVisible(false);
        add(meter);

        JLabel Employer =new JLabel("Employer Id");
        Employer.setBounds(60,100,100,20);
        Employer.setVisible(true);
        add(Employer);

       EmployerText= new TextField();
        EmployerText.setBounds(250,100,120,20);
        EmployerText.setVisible(true);
        add(EmployerText);


        JLabel UserName= new JLabel("UserName");
        UserName.setBounds(60,146,100,20);
        add(UserName);


        UserNameText= new TextField();
        UserNameText.setBounds(250,146,120,20);
        add(UserNameText);


        JLabel Name= new JLabel("Name");
        Name.setBounds(60,190,100,20);
        add(Name);

        NameText = new TextField("");
        NameText.setBounds(250,192,120,20);
        add(NameText);

        meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    Database d= new Database();

                    ResultSet resultSet= d.statement.executeQuery(" select * from signup where meterno = '"+meter.getText()+"'");
                    if (resultSet.next()){
                        NameText.setText(resultSet.getString("name"));
                    }
                }
                catch (Exception E){
                    E.printStackTrace();
                }
            }
        });


        JLabel PassWord= new JLabel("PassWord");
        PassWord.setBounds(60,238,100,20);
        add(PassWord);

        PassWordText = new TextField();
        PassWordText.setBounds(250,240,120,20);
        add(PassWordText);



        LoginAsCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String User=LoginAsCho.getSelectedItem();
                if (User.equals("Customer")){

                    Employer.setVisible(false);
                    EmployerText.setVisible(false);
                    meter.setVisible(true);
                    meterNo.setVisible(true);
                    NameText.setEditable(false);
                }

                else{
                    Employer.setVisible(true);
                    EmployerText.setVisible(true);
                    meter.setVisible(false);
                    meterNo.setVisible(false);

                }
            }
        });



        Create =new JButton("Create");
        Create.setBounds(78,301,110,22);
        Create.setBackground(new Color(66,127,219));
        Create.setForeground(Color.white);
        Create.addActionListener(this);
        add(Create);

        Back =new JButton("Back");
        Back.setBounds(240,299,110,23);
        Back.setForeground(Color.white);
        Back.addActionListener(this);
        Back.setBackground(new Color(66,127,219));
        add(Back);

        ImageIcon BoyIcon= new ImageIcon(ClassLoader.getSystemResource("Icon/boy.png"));
        Image Boyimg=BoyIcon.getImage().getScaledInstance(270,275,Image.SCALE_DEFAULT);
        ImageIcon BoyIcon2=new ImageIcon(Boyimg);
        JLabel Boy=new JLabel(BoyIcon2);
        Boy.setBounds(410,45,270,275);
        add(Boy);

        setSize(800,500);
        setLocation(500,200);

        setLayout(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==Create){
            String sloginAs=LoginAsCho.getSelectedItem();
            String susername=UserNameText.getText();
            String sname=NameText.getText();
            String sPassword=PassWordText.getText();
            String smeter= meter.getText();
            try {
                Database d= new Database();
                String query=null;
                if (LoginAsCho.equals("Admin")){
                    query="insert into Signup values ('"+smeter+"','"+susername+"','"+sname+"','"+ sPassword+"','"+sloginAs+"')";

                }

                else{
                    query="update signup set username = '"+susername+"' ,  password = '"+sPassword+"' , usertype = '"+sloginAs+"' where meterno = '"+smeter+"' ";
                }

                d.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account Created Successfully");
                setVisible(false);
                new Login();
            }
            catch (Exception E){
               E.printStackTrace();
            }
            
        } else if (e.getSource()==Back) {
            setVisible(false);
            new Login();

            
        }

    }

    public static void main(String[] args) {
       new Signup();


    }
}
