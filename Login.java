package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
JTextField UserText,PasswordText;

JButton CancelButton,LoginButton,SignupButton;

Choice LoginChoice;

    Login(){




        super("Login View ");
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel  UserName=new JLabel("UserName");
        UserName.setBounds(300,60,100,20);
        add(UserName);

        UserText= new JTextField();
      UserText.setBounds(400,60,150,20);
      add(UserText);

      JLabel  PassWord=new JLabel("PassWord");
      PassWord.setBounds(300,100,100,20);
      add(PassWord);

        PasswordText= new JTextField();
        PasswordText.setBounds(400,100,150,20);
      add(PasswordText);


      JLabel LoggIn = new JLabel("LoggIn In As");
      LoggIn.setBounds(300,140,100,20);
      add(LoggIn);


      LoginChoice = new Choice();
      LoginChoice.add("Admin");
      LoginChoice.add("Customer");
      LoginChoice.setBounds(400,140,150,20);
      add(LoginChoice);

      LoginButton = new JButton("Login");
      LoginButton.setBounds(315,180,100,20);
      LoginButton.addActionListener(this);
      add(LoginButton);


      CancelButton = new JButton("Cancel");
      CancelButton.setBounds(438,180,100,20);
      CancelButton.addActionListener(this);
      add(CancelButton);


      SignupButton = new JButton("Signup");
      SignupButton.setBounds(375,214,100,20);
      SignupButton.addActionListener(this);
      add(SignupButton);


      ImageIcon PROFILE1 =  new ImageIcon(ClassLoader.getSystemResource("Icon/profile.png"));
      Image PROFILE2=PROFILE1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);

      ImageIcon ProFile= new ImageIcon(PROFILE2);
      JLabel  ProfileLabel=new JLabel(ProFile);
      ProfileLabel.setBounds(18,5,250,250);
      add(ProfileLabel);


      setSize(640,300);
      setLocation(400,200);
      setLayout(null);
      setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==LoginButton){

            String susername=UserText.getText();
            String spassword=PasswordText.getText();
            String suser=LoginChoice.getSelectedItem();

            String query = "select * from signup where username = '"+susername+"' and  password = '"+spassword+"' and usertype = '"+suser+"'";
            try {

                Database d= new Database();
                ResultSet resultSet=d.statement.executeQuery(query);

                if (resultSet.next()){

                    String meter=resultSet.getString("meterno");
                    setVisible(false);
                    new main_class(suser,meter);
                }

                else {
            JOptionPane.showMessageDialog(null,"Invalid Login ");
                }

            }catch (Exception k){
       k.printStackTrace();
            }

        } else if (e.getSource()==CancelButton) {

            setVisible(false);
        }

        else if (e.getSource()==SignupButton){

            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {

        new Login();
    }

}
