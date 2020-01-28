
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class newRegister extends JFrame implements ActionListener,ChangeListener{

    static JLabel head,title,user,DOB,DOBLabel,pass,cpass,city,phone;
    static JTextField usertf,citytf,phonetf;
    static JPasswordField passtf,cpasstf;
    static JButton create,reset,existing,noUse;
    static JSpinner date,month,year;
    
    newRegister ()
    {
        
        head=new JLabel("J-S-H AirLines");
        head.setForeground(Color.ORANGE);
        head.setFont(new Font("Serif",Font.BOLD,50));
        head.setBounds(130, 20, 700, 200);
        
        title=new JLabel("New Registration");
        title.setForeground(Color.BLUE); 
        title.setFont(new Font ("Serif",Font.BOLD, 30));
        title.setBounds(180, 160, 350, 50);
        
        user=new JLabel("Enter Username");
        user.setBounds(100, 240, 250, 30);
        user.setFont(new Font ("Serif",Font.BOLD, 18));
        usertf=new JTextField();
        usertf.setBounds(290, 240,180 , 28);
        
        DOB=new JLabel("Date of Birth");
        DOB.setFont(new Font ("Serif",Font.BOLD, 18));
        DOB.setBounds(100, 290, 250, 30);
               
        date=new JSpinner(new SpinnerNumberModel(1,1,31,1));
        date.setBounds(290, 290, 50, 28);
        date.addChangeListener(this);
         String months[]={
             
             //    "Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"
                 "01","02","03","04","05","06","07","08","09","10","11","12"
         };
         month=new JSpinner(new SpinnerListModel(months));
         month.addChangeListener(this);
         month.setBounds(360, 290, 50, 28);
         year=new JSpinner();
         year.setValue(2000);
         year.addChangeListener(this);
         year.setBounds(430, 290, 50, 28);
         
         DOBLabel=new JLabel("2000-01-1");
         DOBLabel.setBounds(500, 290, 250, 28);         
        pass=new JLabel("Create Password");
        pass.setFont(new Font ("Serif",Font.BOLD, 18));
        pass.setBounds(100, 340, 250, 30);
        passtf=new JPasswordField();
        passtf.setBounds(290, 340, 180, 28);
        
        cpass=new JLabel("Confirm Password");
        cpass.setFont(new Font ("Serif",Font.BOLD, 18));
        cpass.setBounds(100, 390, 250, 30);
        cpasstf=new JPasswordField();
        cpasstf.setBounds(290, 390, 180, 28);
        
        city=new JLabel("City ");
        city.setFont(new Font ("Serif",Font.BOLD, 18));
        city.setBounds(100, 440, 250, 30);
        citytf=new JTextField();
        citytf.setBounds(290, 440, 180, 28);
        
        phone=new JLabel("Phone No.");
        phone.setFont(new Font ("Serif",Font.BOLD, 18));
        phone.setBounds(100, 490, 250, 30);
        phonetf=new JTextField();
        phonetf.setBounds(290, 490, 180, 28);
        
        create=new JButton("Submit");
        create.setBounds(150, 560, 100, 30);
        create.addActionListener(this);
        
        reset=new JButton("Cancel");
        reset.setBounds(310, 560, 100, 30);
        reset.addActionListener(this);
        
        existing=new JButton("Existing User?");
        existing.setBounds(210, 620, 150, 25);
        existing.addActionListener(this);
        
        noUse=new JButton("noUse");
        noUse.setBounds(150, 690, 150, 25);
        
        add(head);
        add(title);
        add(user);
        add(DOB);
        add(date);
        add(month);
        add(year);
        add(DOBLabel);
        add(pass);
        add(cpass);
        add(city);
        add(phone);
        add(usertf);
        add(passtf);
        add(cpasstf);
        add(citytf);
        add(phonetf);
        add(create);
        add(reset);
        add(existing);
        add(noUse);
        setVisible(true);
        setSize(650,800);
        setLocation(650,150);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    public static void main(String[] args) {
        
        new newRegister();
    }

    
    @Override
    public void stateChanged(ChangeEvent e) {
        DOBLabel.setText(year.getValue()+ "-" + month.getValue() + "-" +date.getValue()  );
    }
    
    @Override
    public void actionPerformed(ActionEvent et) {
    
        String s=et.getActionCommand();
        String username=usertf.getText();
        String DOB=DOBLabel.getText();
     
        String password=String.valueOf(passtf.getPassword());
        String cpassword=String.valueOf(cpasstf.getPassword());
        String city=citytf.getText();
        String phoneno=phonetf.getText();
        String q="insert into Airlines_Users values(?,?,?,?,?)";
        
        
        if(s.equals("Cancel"))
        {
            usertf.setText("");
            DOBLabel.setText("2000-1-1");
            passtf.setText("");
            cpasstf.setText("");
            citytf.setText("");
            phonetf.setText("");
        }
        
        else if(s.equals("Submit"))
        {
            if(!password.equals(cpassword))
            {
                JOptionPane.showMessageDialog(this, "Retype the password");
            }
            else {
                try
            {
                Connection con=null;
            
            PreparedStatement pst=null;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            con= DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
                System.out.println("hello1"+DOB);
            Date date1=Date.valueOf(DOB); //new SimpleDateFormat("dd/MM/yyyy").parse(DOB);
                System.out.println("hello2");
            pst=con.prepareStatement(q);
            
            pst.setString(1,username);
            pst.setDate(2,date1);
            pst.setString(3, password);
           
            pst.setString(4, city);
            pst.setString(5,phoneno);
            
            int a=pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this," Data Entered in " +String.valueOf(a)+" Fields");
            
            con.close();
            pst.close();
            
            } 
            catch (ClassNotFoundException |SQLException ex) {
                System.out.println(ex);
            }
          }
        }
        
        
        
        else if(s.equals("Existing User?"))
        {
            dispose();
            new Login();
        }
    }

    
    
}
