package electricity.billing.system;

import java.awt.*;

import javax.swing.*;

public class Splash  extends JFrame {
    Splash(){
        ImageIcon ImageIcon =new ImageIcon(ClassLoader.getSystemResource("Icon/Splash.jpg"));
        Image Imageone=ImageIcon.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);

        ImageIcon ImageIcon2= new ImageIcon(Imageone);
        JLabel imagelabel=new JLabel(ImageIcon2);
        add(imagelabel);

        setSize(600,400);
        setLocation(500,210);
        setVisible(true);

        try{
            Thread.sleep(3000);
            setVisible(false);
            new Login();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }
    public static void main(String[] args) {
        new Splash();

    }
}
