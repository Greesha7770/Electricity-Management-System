package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Customer_details extends JFrame implements  ActionListener  {
    Choice searchMetercho,searchNamecho;
    JTable  table;
    JButton Search,Print,Close;

    Customer_details(){
super("Customer  Details");
        getContentPane().setBackground(new Color(192,186,254));

setSize(700,500);
setLocation(400,200);
setLayout(null);

JLabel searchmeter=new JLabel("Search By Meter Number");
searchmeter.setBounds(20,20,150,20);
add(searchmeter);

searchMetercho= new Choice();
searchMetercho.setBounds(180,20,150,20);
add(searchMetercho);


try{
    Database d = new Database();
    ResultSet resultSet =d.statement.executeQuery("select * from new_customer");
    while (resultSet.next()){
        searchMetercho.add(resultSet.getString("meter_no"));
    }

}
catch (Exception E){
    E.printStackTrace();

}

        JLabel searchName=new JLabel("Search By Name");
        searchName.setBounds(400,20,100,20);
        add(searchName);

        searchNamecho= new Choice();
        searchNamecho.setBounds(520,20,150,20);
        add(searchNamecho);

        try{
            Database d = new Database();
            ResultSet resultSet =d.statement.executeQuery("select * from new_customer");
            while (resultSet.next()){
                searchNamecho.add(resultSet.getString("name"));
            }

        }
        catch (Exception E){
            E.printStackTrace();

        }

        table = new JTable();

        try{
            Database d = new Database();
            ResultSet resultSet =d.statement.executeQuery("select * from new_customer");

            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }
        catch (Exception E){
            E.printStackTrace();

        }
        JScrollPane scrollPane= new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.white);
        add(scrollPane);


        Search=new JButton("Search");
        Search.setBackground(Color.white);
        Search.setBounds(20,70,80,20);
        Search.addActionListener(this);
        add(Search);

        Print= new JButton("Print");
        Print.setBackground(Color.white);
        Print.setBounds(120,70,80,20);
        Print.addActionListener(this);
        add(Print);

        Close= new JButton("Close");
        Close.setBackground(Color.white);
        Close.setBounds(600,70,80,20);
        Close.addActionListener(this);
        add(Close);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==Search){
            String query_Search="select * from new_customer where meter_no = '"+ searchMetercho.getSelectedItem()+"' and name = '"+searchNamecho.getSelectedItem()+"'  " ;

            try
            {
                Database d= new Database();
             ResultSet resultSet=   d.statement.executeQuery(query_Search);

             table.setModel(DbUtils.resultSetToTableModel(resultSet));

            }
            catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==Print) {

try {
   table.print();
}  catch (Exception E){
    E.printStackTrace();
}

        } else {
setVisible(false);

        }



    }

    public static void main(String[] args) {

      new Customer_details();
    }
}
