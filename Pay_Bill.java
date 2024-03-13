package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RectangularShape;
import java.sql.ResultSet;

public class Pay_Bill  extends JFrame  implements ActionListener {

    JButton Cancle,Pay,Back;
    String meter;
    Choice searchmonthcho;
    Pay_Bill(String meter ){
        this.meter=meter;



        setSize(900,600);
        setLocation(300,150);
        setLayout(null);


        JLabel heading = new JLabel("PAY BILL");
        heading.setBounds(120,6,400,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        add(heading);

        JLabel meternumber= new JLabel("Meter Number");
        meternumber.setBounds(35,80,200,20);
        add(meternumber);

        JLabel meternumberText= new JLabel("");
        meternumberText.setBounds(300,80,200,20);
        add(meternumberText);


        JLabel name= new JLabel("Name");
        name.setBounds(35,140,200,20);
        add(name);

        JLabel nameText= new JLabel("");
        nameText.setBounds(300,140,200,20);
        add(nameText);

        JLabel month= new JLabel("Month");
        month.setBounds(35,200,200,20);
        add(month);


        searchmonthcho=new Choice();

        searchmonthcho.add("January");
        searchmonthcho.add("February");
        searchmonthcho.add("March");
        searchmonthcho.add("April");
        searchmonthcho.add("May");
        searchmonthcho.add("June");
        searchmonthcho.add("July");
        searchmonthcho.add("August");
        searchmonthcho.add("September");
        searchmonthcho.add("October");
        searchmonthcho.add("November");
        searchmonthcho.add("December");

        searchmonthcho.setBounds(300,200,200,20);
        add(searchmonthcho);


        JLabel unit= new JLabel("Unit");
        unit.setBounds(35,260,200,20);
        add(unit);

        JLabel unitText= new JLabel("");
        unitText.setBounds(300,260,200,20);
        add(unitText);


        JLabel totalBill= new JLabel("Total Bill");
        totalBill.setBounds(35,320,200,20);
        add(totalBill);

        JLabel totalBillText= new JLabel("");
        totalBillText.setBounds(300,320,200,20);
        add(totalBillText);

        JLabel status= new JLabel("Status");
        status.setBounds(35,380,200,20);
        add(status);

        JLabel statusText= new JLabel("");
        statusText.setBounds(300,380,200,20);
        statusText.setForeground(Color.RED);
        add(statusText);



        try {
            Database d= new Database();
            ResultSet resultSet= d.statement.executeQuery("select * from new_customer where meter_no= '"+meter+"'");
            while (resultSet.next()){
                meternumberText.setText(meter);
                nameText.setText(resultSet.getString("name"));

            }
        }
catch (Exception E){
            E.printStackTrace();
}


        searchmonthcho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                try {
                    Database d= new Database();
                    ResultSet resultSet= d.statement.executeQuery("select * from bill  where meter_no = '"+meter+"' AND month = '"+searchmonthcho.getSelectedItem()+"'" );
                    while (resultSet.next()){
                        unitText.setText(resultSet.getString("unit"));
                        totalBillText.setText(resultSet.getString("total_bill"));
                        statusText.setText(resultSet.getString("status"));
                    }

                }
                catch (Exception E){
                    E.printStackTrace();
                }

            }
        });



        Pay=new JButton("Pay");
        Pay.setBackground( Color.BLACK);
        Pay.setForeground(Color.white);
        Pay.setBounds(100,460,100,25);
        Pay.addActionListener(this);
        add(Pay);

        Back= new JButton("Back");
        Back.setBackground(Color.black);
        Back.setForeground(Color.white);
        Back.setBounds(240,459,100,25);
        Back.addActionListener(this);
        add(Back);

        //getContentPane().setBackground(Color.white);

        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("Icon/paybill.png"));
        Image i2=i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);


        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
if (e.getSource()==Pay){

    try
    {
        Database d= new Database();
        d.statement.executeUpdate("update bill set status= 'Paid' where meter_no = '"+meter+"' and month = '"+searchmonthcho.getSelectedItem()+"'");
    }
    catch (Exception E){
        E.printStackTrace();
    }
    setVisible(false);
    new Paytm(meter);
}
else {
    setVisible(false);
}
    }

    public static void main(String[] args) {
        new Pay_Bill("");
    }

}
