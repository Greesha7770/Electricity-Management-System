package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RectangularShape;
import java.sql.ResultSet;

public class Generate_bill  extends JFrame implements ActionListener {

    Choice searchmonthcho;
    JTextArea Area;
    String meter;

    JButton bill;
    Generate_bill (String meter){
        this.meter=meter;
        setSize(500,700);
        setLayout(new BorderLayout());
        setLocation(500,30);

        JPanel panel=new JPanel();
        JLabel heading =new JLabel("Generate Bill");
        JLabel meter_no=new JLabel(meter);




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




    Area=new JTextArea(50,15);
    Area.setText("\n \n \t -------------------Click on the -------------------------\n \t -------------------Generate Bill ");
    Area.setFont(new Font("Senserif", Font.ITALIC,15));
    JScrollPane pane= new JScrollPane(Area);
    bill = new JButton("Generate Bill");
    bill.addActionListener(this);

    add(pane);

        panel.add(heading);
        panel.add(meter_no);
        panel.add(searchmonthcho);

        add(panel,"North");
        add(bill,"South");

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        try{

            Database d= new Database();
            String smonth= searchmonthcho.getSelectedItem();
            Area.setText("\t Reliance  Power Limited \n Electricity Bill  Generated For Month Of "+smonth+", 2023 \n\n\n");


            ResultSet resultSet = d.statement.executeQuery("select * from new_customer where meter_no='"+meter+"'");

            if(resultSet.next()){
                Area.append("\n  Customer Name       : "+resultSet.getString("name"));
                Area.append("\n  Meter  Number       : "+resultSet.getString("meter_no"));
                Area.append("\n  Address             : "+resultSet.getString("address"));
                Area.append("\n  City                : "+resultSet.getString("city"));
                Area.append("\n  State               : "+resultSet.getString("state"));
                Area.append("\n  Email               : "+resultSet.getString("email"));
                Area.append("\n  Phone               : "+resultSet.getString("phone"));


            }






            resultSet = d.statement.executeQuery("select * from meter_info where meter_no='"+meter+"'");

            if(resultSet.next()){
                Area.append("\n  Customer Meter Location      : "+resultSet.getString("meter_location"));
                Area.append("\n  Customer Meter Type          : "+resultSet.getString("meter_type"));
                Area.append("\n  Customer Phase Code          : "+resultSet.getString("phase_code"));
                Area.append("\n  Customer Bill Type           : "+resultSet.getString("bill_type"));
                Area.append("\n  Customer Days                : "+resultSet.getString("days"));



            }










            resultSet = d.statement.executeQuery("select * from tax ");


            if(resultSet.next()){
                Area.append("\n  Cost Per Unit       : "+resultSet.getString("cost_per_unit"));
                Area.append("\n  Meter Rent          : "+resultSet.getString("meter_rent"));
                Area.append("\n  Service Charge      : "+resultSet.getString("service_charge"));
                Area.append("\n  Service Tax         : "+resultSet.getString("service_tax"));
                Area.append("\n  Swacch Bharat Acss  : "+resultSet.getString("swacch_bharat"));
                Area.append("\n  Fixed Tax           : "+resultSet.getString("fixed_tax"));


            }


            resultSet = d.statement.executeQuery("select * from bill where meter_no='"+meter+"'   and month = '"+searchmonthcho.getSelectedItem()+"' ");

            if(resultSet.next()){
                Area.append("\n  Current Month           : "+resultSet.getString("month"));
                Area.append("\n  Units Consumed          : "+resultSet.getString("unit"));
                Area.append("\n  Total Charges           : "+resultSet.getString("total_bill"));
                Area.append("\n  Total Payable           : "+resultSet.getString("status"));



            }


        }
        catch(Exception E){
           E.printStackTrace();

        }
    }

    public static void main(String[] args) {
        new Generate_bill ("");

    }
}
