package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class Calculate_bill extends JFrame implements ActionListener {

    JLabel Nametext,addresstext;
    Choice meternocho,monthcho;
    TextField UnitText;
    JButton submit,cancel;

    Calculate_bill(){
        super("Electricity Bill");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214,195,247));
        add(panel);
        JLabel Heading = new JLabel("Calculate Electricity Bill");
        Heading.setBounds(70,10,300,20);
        Heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(Heading);
        JLabel meterno= new JLabel("Meter Number");
        meterno.setBounds(50,80,100,20);
        panel.add(meterno);

        meternocho = new Choice();

        try{

            Database d= new Database();
            ResultSet resultSet= d.statement.executeQuery("select * from new_customer");

            while (resultSet.next()){

                meternocho.add(resultSet.getString("meter_no"));
            }

        }
        catch (Exception E){
            E.printStackTrace();
        }
        meternocho.setBounds(180,80,100,20);
        panel.add(meternocho);

        JLabel name= new JLabel("Name");
        name.setBounds(50,120,100,20);
        panel.add(name);

        Nametext= new JLabel("");
        Nametext.setBounds(180,120,150,20);
        panel.add(Nametext);


        JLabel address= new JLabel("Address");
        address.setBounds(50,160,100,20);
        panel.add(address);

        addresstext= new JLabel("");
        addresstext.setBounds(180,160,150,20);
        panel.add(addresstext);

        try{

            Database d= new Database() ;
            ResultSet resultSet= d.statement.executeQuery("select * from new_customer where meter_no='"+meternocho.getSelectedItem()+"'");

            while (resultSet.next()){
                Nametext.setText(resultSet.getString("name"));
                addresstext.setText(resultSet.getString("address"));

            }

        }



catch (Exception E){
            E.printStackTrace();
}


meternocho.addItemListener(new ItemListener() {
    @Override
    public void itemStateChanged(ItemEvent e) {
        try{

            Database d= new Database() ;
            ResultSet resultSet= d.statement.executeQuery("select * from new_customer where meter_no='"+meternocho.getSelectedItem()+"'");

            while (resultSet.next()){
                Nametext.setText(resultSet.getString("name"));
                addresstext.setText(resultSet.getString("address"));

            }

        }



        catch (Exception E){
            E.printStackTrace();
        }
    }
});


        JLabel unit_consume= new JLabel("Unit Consumtion");
        unit_consume.setBounds(50,200,100,20);
        panel.add(unit_consume);

        UnitText = new TextField();
        UnitText.setBounds(180,200,150,20);
        panel.add(UnitText);

        JLabel month = new JLabel("Month");
        month.setBounds(50,240,100,20);
        panel.add(month);

        monthcho=new Choice();
        monthcho.add("January");
        monthcho.add("February");
        monthcho.add("March");
        monthcho.add("April");
        monthcho.add("May");
        monthcho.add("June");
        monthcho.add("July");
        monthcho.add("August");
        monthcho.add("September");
        monthcho.add("October");
        monthcho.add("November");
        monthcho.add("December");
        monthcho.setBounds(180,240,150,20);
        panel.add(monthcho);


        submit= new JButton("Submit");
        submit.setBounds(80,300,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);


        cancel= new JButton("Cancel");
        cancel.setBounds(220,300,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon imageIcon= new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image image= imageIcon.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1= new ImageIcon(image);
        JLabel Label= new JLabel(imageIcon1);
        add(Label,"East");



        setSize(650,400);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
if (e.getSource()==submit){
    String smeterno= meternocho.getSelectedItem();
    String sunit= UnitText.getText();
    String smonth= monthcho.getSelectedItem();

    int totalbill=0;
    int units=Integer.parseInt(sunit);
    String query_tax="select * from tax";

    try{
        Database d= new Database();
        ResultSet resultSet =d.statement.executeQuery(query_tax);
        while (resultSet.next())
        {
            totalbill+=units*Integer.parseInt(resultSet.getString("cost_per_unit"));
            totalbill+=Integer.parseInt(resultSet.getString("meter_rent"));
            totalbill+=Integer.parseInt(resultSet.getString("service_charge"));
            totalbill+=Integer.parseInt(resultSet.getString("service_tax"));

            totalbill+=Integer.parseInt(resultSet.getString("swacch_bharat"));

            totalbill+=Integer.parseInt(resultSet.getString("fixed_tax"));



        }

    }catch (Exception E)
    {
        E.printStackTrace();
    }
    String query_total_bill="insert into bill values('"+smeterno+"' ,'"+smonth+"' , '"+sunit+"' , '"+totalbill+"' , 'Not Paid'    )";
try{
    Database d= new Database();

    d.statement.executeUpdate(query_total_bill);

    JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");
    setVisible(false);
}
catch (Exception E){

}
}
else {
    setVisible(false);
}
    }

    public static void main(String[] args) {
        new Calculate_bill();
    }
}
