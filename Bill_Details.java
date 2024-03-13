package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Bill_Details  extends JFrame  {
    String meter;

    Bill_Details(String meter){

        this.meter=meter;
        getContentPane().setBackground(Color.white);
        setSize(700,650);
        setLocation(400,150);
        setLayout(null);
JTable table= new JTable();

try{
Database d=new Database();
String query_bill="select * from  bill where meter_no = '"+meter+"'";
    ResultSet resultSet= d.statement.executeQuery(query_bill);
    table.setModel(DbUtils.resultSetToTableModel(resultSet));

}
catch(Exception E){
    E.printStackTrace();
}
JScrollPane sp=new JScrollPane(table);
sp.setBounds(0,0,700,650);
add(sp);
        setVisible(true);

    }



    public static void main(String[] args) {
        new Bill_Details("");
    }
}
