import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{

    static JLabel title,name,pass,head;
    static JTextField nametf,In_nametf,In_dstntiontf;
    static JPasswordField passtf,In_passtf;
    static JButton submit,reset,newUser,print;
    
    Login()
    {  // setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("AirLines Reservation Form");
        head=new JLabel("J-S-H AirLines");
        head.setForeground(Color.ORANGE);
        head.setFont(new Font("Serif",Font.BOLD,60));
        head.setBounds(250, 10, 700, 200);
        
        title=new JLabel("LOGIN PAGE");
        title.setForeground(Color.BLUE);
        title.setFont(new Font ("Serif",Font.BOLD, 30));
        title.setBounds(350, 145, 340, 50);
        
        name=new JLabel("Enter Username");        
        name.setFont(new Font ("Serif",Font.BOLD, 18));
        name.setBounds(290, 220, 250, 30);
        
        pass=new JLabel("Password");
        pass.setFont(new Font ("Serif",Font.BOLD, 18));
        pass.setBounds(290, 270, 250, 30);
        
        nametf=new JTextField();
        nametf.setBounds(450, 220, 150, 25);
        
        passtf=new JPasswordField();
        passtf.setBounds(450, 270, 150, 25);
        
        submit=new JButton("SUBMIT");
        submit.setBounds(290, 320, 100, 30);
        submit.addActionListener(this);
        
        reset=new JButton("CANCEL");
        reset.setBounds(450,320 ,100, 30);
        reset.addActionListener(this);
        
        newUser=new JButton("NEW USER");
        newUser.setBounds(350, 360, 150, 26);
        newUser.addActionListener(this);
        
        JPanel panel=new JPanel();
            
            JLabel heading=new JLabel("Print Invoice");
            heading.setFont(new Font("Serif",Font.BOLD,32));
            heading.setBounds(190,22 , 300, 30);
            
            JLabel In_name=new JLabel("Username");
            In_name.setBounds(150, 68, 250, 30);
            In_name.setFont(new Font("Serif",Font.BOLD,16));
            In_nametf=new JTextField();
            In_nametf.setBounds(260, 68, 150, 25);
            
            JLabel In_pass=new JLabel("Password");
            In_pass.setBounds(150, 108, 250, 30);
            In_pass.setFont(new Font("Serif",Font.BOLD,16));
            In_passtf=new JPasswordField();
            In_passtf.setBounds(260, 108, 150, 25);
            
            JLabel In_dstntion=new JLabel("Destination");
            In_dstntion.setFont(new Font("Serif",Font.BOLD,16));
            In_dstntion.setBounds(150, 148, 250, 30);
            In_dstntiontf=new JTextField();
            In_dstntiontf.setBounds(260, 148, 150, 25);
            
            JButton In_print=new JButton("Print");
            In_print.setBounds(150, 195, 250, 30);
            In_print.addActionListener(this);
            
            
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            panel.add(heading);
            panel.add(In_name);
            panel.add(In_nametf);
            panel.add(In_pass);
            panel.add(In_passtf);
            panel.add(In_dstntion);
            panel.add(In_dstntiontf);
            panel.add(In_print);
            
            panel.setLayout(null);
            panel.setVisible(true);
            panel.setBounds(150,460 , 550, 300);
       
            
        add(head);
        add(title);
        add(name);
        add(pass);
        add(nametf);
        add(passtf);
        add(submit);
        add(reset);
        add(newUser);
        add(panel);
        
        setSize(900,900);       
        setLocation(520,50);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        String s=e.getActionCommand();
        String name=nametf.getText();
        String password=String.valueOf(passtf.getPassword());
        String Username=In_nametf.getText();
        String Destination=In_dstntiontf.getText();
        String Pass=String.valueOf(In_passtf.getPassword());
        
        if(s.equals("CANCEL"))
        {
            nametf.setText("");
            passtf.setText("");
            
        }
        else if(s.equals("SUBMIT"))
        {
            try{
            Connection con=null;
            PreparedStatement pst=null;
            
             Class.forName("org.apache.derby.jdbc.ClientDriver");
        
             con= DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            
             String q="Select * from Airlines_Users where username=? AND password=?";
             
             pst=con.prepareStatement(q);
             pst.setString(1, name);
             pst.setString(2, password);
            
            ResultSet rs=pst.executeQuery();
            
            if(rs.next())
            {   
                System.out.println("Yes");
                JOptionPane.showMessageDialog(this,"Successful!! Welcome "+name);
                   dispose();
                new Booking();
        
            }
            else
            {
                    System.out.println("No");
                JOptionPane.showMessageDialog(this, "Invalid User");
            }
        
            con.close();
            pst.close();
            
            } catch (ClassNotFoundException |SQLException ex) {
                System.out.println(ex);
            }
         }
        else if(s.equals("NEW USER")){
            dispose();
            new newRegister();
        
        }
        else if(s.equals("Print"))
        {
            try{
            Connection con=null;
            PreparedStatement pst=null;
            
             Class.forName("org.apache.derby.jdbc.ClientDriver");
        
             con= DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            
           String q="Select Airlines_users.username,Airlines_users.password, Airlines_book.destination from Airlines_users inner join Airlines_Book on Airlines_Book.Phone_Number=Airlines_Users.PhoneNo And Username=? And Password=? And Destination=?";
           
             pst=con.prepareStatement(q);
             pst.setString(1, Username);
             pst.setString(2, Pass);
             pst.setString(3, Destination);
             
             ResultSet rs=pst.executeQuery();
             
             if(rs.next())
             {
                 JOptionPane.showMessageDialog(this,"Printing Invoice");
                 dispose();
                 new Invoice();
             }
             else
                     {
                         JOptionPane.showMessageDialog(this, "Invalid Details");
                     }
            
             con.close();
             pst.close();
             
        }   catch (ClassNotFoundException |SQLException ex) {
                System.out.println(ex);
                }                  
        }
       }
    }