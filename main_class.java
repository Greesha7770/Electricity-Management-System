package electricity.billing.system;

import com.mysql.cj.log.Log;
import com.mysql.cj.xdevapi.UpdateStatement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class  extends JFrame implements ActionListener {

    String acctype,meter_pass;


    main_class( String acctype,String meter_pass){
        this.meter_pass = meter_pass;

        this.acctype=acctype;
setExtendedState(JFrame.MAXIMIZED_BOTH);

ImageIcon ImageIcon= new ImageIcon(ClassLoader.getSystemResource("Icon/ebs.png"));
Image Image=ImageIcon.getImage().getScaledInstance(1530,830, java.awt.Image.SCALE_DEFAULT);
ImageIcon  imageIcon2=new ImageIcon(Image);
JLabel imageLabel= new JLabel(imageIcon2);
add(imageLabel);

JMenuBar menuBar=new JMenuBar();
     setJMenuBar(menuBar);

     JMenu menu=new JMenu("MENU");
     menu.setFont(new Font("serif",Font.PLAIN,16));




JMenuItem newCustomer=new JMenuItem("New Customer");
newCustomer.setFont(new Font("monospaced",Font.PLAIN,15));
     ImageIcon Customerimg= new ImageIcon(ClassLoader.getSystemResource("Icon/newcustomer.png"));
     Image customimg=Customerimg.getImage().getScaledInstance(24,24, java.awt.Image.SCALE_DEFAULT);
     newCustomer.addActionListener(this);
     newCustomer.setIcon(new ImageIcon(customimg));

menu.add(newCustomer);


     JMenuItem Customerdetails=new JMenuItem("Customer Details");
     Customerdetails.setFont(new Font("monospaced",Font.PLAIN,15));
     ImageIcon Customerdetailimg= new ImageIcon(ClassLoader.getSystemResource("Icon/customerDetails.png"));
     Image customerimage=Customerdetailimg.getImage().getScaledInstance(23,23, Image.SCALE_DEFAULT);
     Customerdetails.setIcon(new ImageIcon(customerimage));
        Customerdetails.addActionListener(this);
     menu.add(Customerdetails);


     JMenuItem Depositdetail= new JMenuItem("Deposit Details");
     Depositdetail.setFont(new Font("monospaced",Font.PLAIN,15));
     ImageIcon Depimg= new ImageIcon(ClassLoader.getSystemResource("Icon/depositdetails.png"));
     Image  Depositimg=Depimg.getImage().getScaledInstance(23,23, Image.SCALE_DEFAULT);
     Depositdetail.setIcon(new ImageIcon(Depositimg));
        Depositdetail.addActionListener(this);
     menu.add(Depositdetail);

     JMenuItem Calculatebill= new JMenuItem("Calculate Bill");
     Calculatebill.setFont(new Font("monospaced",Font.PLAIN,15));
     javax.swing.ImageIcon Calculateimg= new ImageIcon((ClassLoader.getSystemResource("Icon/calculatorbills.png")));
     Image billimg=Calculateimg.getImage().getScaledInstance(23,23, java.awt.Image.SCALE_DEFAULT);
     Calculatebill.setIcon(new ImageIcon((billimg)));
        Calculatebill.addActionListener(this);
     menu.add(Calculatebill);


     JMenu Information=new JMenu("INFORMATION");
     Information.setFont(new Font("serif",Font.PLAIN,16));


     JMenuItem Updateinfo = new JMenuItem("Update Information");
     Updateinfo.setFont(new Font("monospaced",Font.PLAIN,15));
     ImageIcon Updateimg=new ImageIcon(ClassLoader.getSystemResource("Icon/refresh.png"));
     Image Updateimage=  Updateimg.getImage().getScaledInstance(23,23, java.awt.Image.SCALE_DEFAULT);
     Updateinfo.setIcon(new ImageIcon(Updateimage));
        Updateinfo.addActionListener(this);
     Information.add(Updateinfo);

     JMenuItem ViewInfo= new JMenuItem("View Information");
     ViewInfo.setFont(new Font("monospaced",Font.PLAIN,15));
     ImageIcon viewimage=new ImageIcon(ClassLoader.getSystemResource("Icon/Information.png"));
     Image Viewimg= viewimage.getImage().getScaledInstance(23,23, java.awt.Image.SCALE_DEFAULT);
     ViewInfo.setIcon(new ImageIcon(Viewimg));
        ViewInfo.addActionListener(this);
     Information.add(ViewInfo);

    JMenu User= new JMenu("USER");
    User.setFont(new Font("serif",Font.PLAIN,16));


    JMenuItem  Paybill=new JMenuItem("PayBill");
    Paybill.setFont(new Font("monospaced",Font.PLAIN,15));
    ImageIcon payimg= new ImageIcon(ClassLoader.getSystemResource("Icon/pay.png"));
    Image payimage=payimg.getImage().getScaledInstance(23,23, java.awt.Image.SCALE_DEFAULT);
    Paybill.setIcon(new ImageIcon(payimage));
    Paybill.addActionListener(this);
    User.add(Paybill);

    JMenuItem BillDetail= new JMenuItem("Bill Details");
    BillDetail.setFont(new Font("monospaced",Font.PLAIN,15));
    ImageIcon BillImage=new ImageIcon(ClassLoader.getSystemResource("Icon/detail.png"));
    Image Billimg = BillImage.getImage().getScaledInstance(23,23, java.awt.Image.SCALE_DEFAULT);
    BillDetail.setIcon(new ImageIcon(Billimg));
        BillDetail.addActionListener(this);
    User.add(BillDetail);

    JMenu Bills = new JMenu("BILLS");
    Bills.setFont(new Font("serif",Font.PLAIN,16));


    JMenuItem genbill= new JMenuItem("Generate Bill");
    genbill.setFont(new Font("monospaced",Font.PLAIN,15));
    ImageIcon Genimg= new ImageIcon(ClassLoader.getSystemResource("Icon/bill.png"));
    Image GenerateImage= Genimg.getImage().getScaledInstance(23,23, java.awt.Image.SCALE_DEFAULT);
    genbill.setIcon(new ImageIcon(GenerateImage));
        genbill.addActionListener(this);
    Bills.add(genbill);


    JMenu utility= new JMenu("UTILITY");
    utility.setFont(new Font("serif",Font.PLAIN,16));




     JMenuItem notepad= new JMenuItem("Notepad");
     notepad.setFont(new Font("monospaced",Font.PLAIN,15));
     ImageIcon NoteImg= new ImageIcon(ClassLoader.getSystemResource("Icon/notepad.png"));
     Image NotepadImage= NoteImg.getImage().getScaledInstance(23,23, java.awt.Image.SCALE_DEFAULT);
     notepad.setIcon(new ImageIcon(NotepadImage));
        notepad.addActionListener(this);
     utility.add(notepad);



     JMenuItem calculator= new JMenuItem("Calculator");
     calculator.setFont(new Font("monospaced",Font.PLAIN,15));
     ImageIcon CalculatorImg= new ImageIcon(ClassLoader.getSystemResource("Icon/calculator.png"));
     Image CalcImage= CalculatorImg.getImage().getScaledInstance(23,23, java.awt.Image.SCALE_DEFAULT);
     calculator.setIcon(new ImageIcon(CalcImage));
     //calculator.setBackground(Color.pink);
        calculator.addActionListener(this);
     utility.add(calculator);






     JMenu exit= new JMenu("EXIT");
     exit.setFont(new Font("serif",Font.PLAIN,16));


     JMenuItem eexit= new JMenuItem("Exit");
     eexit.setFont(new Font("monospaced",Font.PLAIN,15));
     ImageIcon eexitImg= new ImageIcon(ClassLoader.getSystemResource("Icon/exit.png"));
     Image eexitImage= eexitImg.getImage().getScaledInstance(23,23, java.awt.Image.SCALE_DEFAULT);
     eexit.setIcon(new ImageIcon(eexitImage));
        eexit.addActionListener(this);
     exit.add(eexit);



     if (acctype.equals(("Admin"))){
         menuBar.add(menu);
     }
else {


         menuBar.add(Bills);
         menuBar.add(User);
         menuBar.add(Information);
     }
      //  menuBar.add(menu);

        menuBar.add(utility);
        menuBar.add(exit);



     setLayout(new FlowLayout());
     setVisible(true);

}

    @Override
    public void actionPerformed(ActionEvent e) {

        String msg=e.getActionCommand();

        if(msg.equals("New Customer")){

            new New_customer();
        }
        else if(msg.equals("Customer Details")){
            new Customer_details();

        }
        else if(msg.equals("Deposit Details")){
            new Deposit_details();

        }
        else if(msg.equals("Calculate Bill")){
            new Calculate_bill();

        } else if (msg.equals("PayBill")) {
            new Pay_Bill(meter_pass);
            
        } else if(msg.equals("Update Information")){
            new update_information(meter_pass);
        }
        else if(msg.equals("View Information")){
            new view_information(meter_pass);

        } else if (msg.equals("Bill Details")) {
            new Bill_Details(meter_pass);

        }
        else if (msg.equals("Generate Bill")) {
            new Generate_bill(meter_pass);

        }

        else if (msg.equals("Calculator")) {
         try{

             Runtime.getRuntime().exec("calc.exe");
         }
         catch (Exception E){
             E.printStackTrace();
         }

        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            }

            catch (Exception E)
            {
                E.printStackTrace();
            }
        }


        else if (msg.equals("Exit")) {
           setVisible(false);
           new Login();
            }

    }

    public static void main(String[] args) {

     new main_class("","");

    }
}
