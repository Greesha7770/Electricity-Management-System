package electricity.billing.system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paytm extends JFrame implements ActionListener {
    JButton back;
    String meter;


    Paytm(String meter){
        this.meter=meter;
        JEditorPane Jpane= new JEditorPane();
        Jpane.setEditable(false);


        try {
            Jpane.setPage("https://paytm.com/online-payments");
            Jpane.setBounds(400,150,800,600);

        }catch (Exception e){
Jpane.setContentType("text/html");
Jpane.setText("<html> Error!      Error!             Error!                  Error!                 Error! </html>");
        }


        JScrollPane scroll=new JScrollPane(Jpane);
        add(scroll);
        back = new JButton("BACK");
        back.setBounds(640,20,80,30);

        back.addActionListener(this);
        Jpane.add(back);

        setSize(800,600);
        setLocation(400,150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
setVisible(false);
new Pay_Bill("");
    }

    public static void main(String[] args) {
        new Paytm("");
    }
}
