import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Booking extends JFrame implements ChangeListener,ItemListener,ActionListener{

    static JLabel head,title,place,date,dateLabel,tickets,classType,seat,phoneNo,selected,detail,predefined;
    static JTextField tickettf,phonetf;
    static JSpinner dateDepart,monthDepart;
    static JComboBox placetf;
    static JRadioButton eco,buss;
    static JButton submit,reset,Print,logOut;
    static JCheckBox a1,a2,a3,a4,a5,b1,b2,b3,b4,b5,c1,c2,c3,c4,c5,d1,d2,d3,d4,d5;
    
    
    Booking()
    {
        
        head=new JLabel("J-S-H AirLines");
        head.setForeground(Color.ORANGE);
        head.setFont(new Font("Serif",Font.BOLD,50));
        head.setBounds(180, 40, 600, 100);
        
        title=new JLabel("Ticket Booking");
        title.setForeground(Color.BLUE); 
        title.setFont(new Font ("Serif",Font.BOLD, 30));
        title.setBounds(230, 140, 350, 50);
                
        place=new JLabel("Destination");
        place.setBounds(150, 220, 250, 30);
        place.setFont(new Font ("Serif",Font.BOLD, 18));
        
        String s1[]={"Mumbai","Bangalore","Kolkata","Chennai","Delhi","Ahmedabad","Goa"};        
        placetf=new JComboBox(s1);
        placetf.addItemListener(this);
        placetf.setBounds(340, 220,180 , 28);
        selected=new JLabel("Mumbai ");
        selected.setFont(new Font ("Serif",Font.BOLD,16));
        selected.setBounds(540, 220, 100, 30);
        predefined=new JLabel("Selected");
        predefined.setBounds(605, 220, 100, 30);
        predefined.setFont(new Font ("Serif",Font.BOLD,16));
        
        date=new JLabel("Date of Departure");
        date.setFont(new Font ("Serif",Font.BOLD, 18));
        date.setBounds(150, 280, 250, 30);
        
        dateDepart=new JSpinner(new SpinnerNumberModel(1,1,31,1));       
        dateDepart.setBounds(340, 280, 50, 28);
        dateDepart.addChangeListener(this);
        
        String months[]={
            "08","09","10","11","12"
        };
        monthDepart=new JSpinner(new SpinnerListModel(months));
        monthDepart.setBounds(410, 280, 50, 28);
        monthDepart.addChangeListener(this);
        
        dateLabel=new JLabel("2019-11-21");
        dateLabel.setBounds(480, 280, 180, 28);
        
        tickets=new JLabel("Number of tickets");
        tickets.setFont(new Font("Serif",Font.BOLD,18));
        tickets.setBounds(150, 340, 250, 30);
        
        tickettf=new JTextField("0");
        tickettf.setBounds(340, 340, 180, 28);
        
        classType=new JLabel("Class Type");
        classType.setFont(new Font("Serif",Font.BOLD,18));
        classType.setBounds(150, 400, 250, 30);
        
        eco=new JRadioButton("Economy Class");
        eco.setBounds(340, 400, 130, 28);
        buss=new JRadioButton("Business Class");       
        buss.setBounds(500, 400, 130, 28);
        
        phoneNo=new JLabel("Phone No:");
        phoneNo.setBounds(150, 460, 250, 30);
        phoneNo.setFont(new Font("Serif",Font.BOLD,18));
        phonetf=new JTextField();
        phonetf.setBounds(340, 460, 180, 28);
        
        seat=new JLabel("Seat Selection");
        seat.setFont(new Font ("Serif",Font.BOLD,16));
        seat.setBounds(150,510 , 250, 30);
               
        
        JPanel panel=new JPanel();
                
        a1=new JCheckBox("A1");
        a1.setBounds(200,550,50 , 30);               
        a2=new JCheckBox("A2");
        a2.setBounds(200,590,50 , 30);
        a3=new JCheckBox("A3");
        a3.setBounds(200,630,50 , 30);
        a4=new JCheckBox("A4");
        a4.setBounds(200,670,50 , 30);
        a5=new JCheckBox("A5");
        a5.setBounds(200,710,50 , 30);
        b1=new JCheckBox("B1");
        b1.setBounds(270,550,50 , 30);
        b2=new JCheckBox("B2");
        b2.setBounds(270,590,50 , 30);
        b3=new JCheckBox("B3");
        b3.setBounds(270,630,50 , 30);
        b4=new JCheckBox("B4");
        b4.setBounds(270,670,50 , 30);
        b5=new JCheckBox("B5");
        b5.setBounds(270,710,50 ,30);
        c1=new JCheckBox("C1");
        c1.setBounds(410,550,50 , 30);
        c2=new JCheckBox("C2");
        c2.setBounds(410,590,50 , 30);
        c3=new JCheckBox("C3");
        c3.setBounds(410,630,50 , 30);
        c4=new JCheckBox("C4");
        c4.setBounds(410,670,50 , 30);
        c5=new JCheckBox("C5");
        c5.setBounds(410,710,50 , 30);
        d1=new JCheckBox("D1");
        d1.setBounds(480,550,50 , 30);
        d2=new JCheckBox("D2");
        d2.setBounds(480,590,50 , 30);
        d3=new JCheckBox("D3");
        d3.setBounds(480,630,50 , 30);
        d4=new JCheckBox("D4");
        d4.setBounds(480,670,50 , 30);
        d5=new JCheckBox("D5");
        d5.setBounds(480,710,50 , 30);
        
        submit=new JButton("Submit");
        submit.setBounds(150, 770, 110, 40);
        submit.addActionListener(this);
        
        reset=new JButton("Cancel");
        reset.setBounds(360, 770, 110, 40);
        reset.addActionListener(this);
        
        logOut=new JButton("Log Out");
        logOut.setBounds(230, 845, 180, 28);
        logOut.addActionListener(this);
        
        Print=new JButton("Print Invoice");
        Print.setBounds(230, 880, 180, 28);
        Print.addActionListener(this);
        
        
        panel.add(a1);
        panel.add(a2);
        panel.add(a3);
        panel.add(a4);
        panel.add(a5);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(c1);
        panel.add(c2);
        panel.add(c3);
        panel.add(c4);
        panel.add(c5);
        panel.add(d1);
        panel.add(d2);
        panel.add(d3);
        panel.add(d4);
        panel.add(d5);
        panel.setBounds(110, 420, 250, 250);
        //panel.setBorder(BorderFactory.createLineBorder(Color.black)); 
        panel.setLayout(null);
        
        ButtonGroup bgr=new ButtonGroup();
        bgr.add(eco);
        bgr.add(buss);
        
        
        add(head);
        add(title);
        add(place);
        add(placetf);
        add(date);
        add(dateDepart);
        add(monthDepart);
        add(dateLabel);
        add(tickets);
        add(tickettf);
        add(classType);
        add(eco);
        add(buss);
        add(selected);
        add(predefined);
        add(seat);
        add(phoneNo);
        add(phonetf);
        panel.add(submit);
        panel.add(reset);       
        panel.add(Print);
        panel.add(logOut);
        add(panel);
        
        setSize(800,1000);
        setLocation(550,10);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

    }
    
    public static void main(String[] args) {
        
        new Booking();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
            dateLabel.setText( "2019-" + monthDepart.getValue() + "-" +dateDepart.getValue()  );
    
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    
        if(e.getSource()==placetf)
        {
            selected.setText((String) placetf.getSelectedItem());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        String s=e.getActionCommand();
        String place=selected.getText();
        String date=dateLabel.getText();
        int tickets=Integer.parseInt(tickettf.getText());
        String classType=null;
        if(eco.isSelected())
        {
         classType="Economic Class";
        }
        else if(buss.isSelected())
        {
            classType="Business Class";
        }
        String PhoneNo=phonetf.getText();
        String setA1="No", setA2="No", setA3="No", setA4="No", setA5="No", setB1="No", setB2="No", setB3="No", setB4="No", setB5="No", setC1="No", setC2="No", setC3="No", setC4="No", setC5="No", setD1="No", setD2="No", setD3="No", setD4="No", setD5="No";
        
        if(a1.isSelected())
        {
            setA1="Selected";
        }
        if(a2.isSelected())
        {
            setA2="Selected";
        }
        if(a3.isSelected())
        {
            setA3="Selected";
        }
        if(a4.isSelected())
        {
            setA4="Selected";
        }
        if(a5.isSelected())
        {
            setA5="Selected";
        }
        if(b1.isSelected())
        {
            setB1="Selected";
        }
        if(b2.isSelected())
        {
            setB2="Selected";
        }
        if(b3.isSelected())
        {
            setB3="Selected";
        }
        if(b4.isSelected())
        {
            setB4="Selected";
        }
        if(b5.isSelected())
        {
            setB5="Selected";
        }
        if(c1.isSelected())
        {
            setC1="Selected";
        }
        if(c2.isSelected())
        {
            setC2="Selected";
        }
        if(c3.isSelected())
        {
            setC3="Selected";
        }
        if(c4.isSelected())
        {
            setC4="Selected";
        }
        if(c5.isSelected())
        {
            setC5="Selected";
        }
        if(d1.isSelected())
        {
            setD1="Selected";
        }
        
        if(d2.isSelected())
        {
            setD2="Selected";
        }
        if(d3.isSelected())
        {
            setD4="Selected";
        }
        if(d5.isSelected())
        {
            setD5="Selected";
        }
        
        
        if(s.equals("Cancel"))
        {
            selected.setText("Mumbai Selected");
            dateLabel.setText("2019-08-1");
            tickettf.setText("0");
            phonetf.setText("");
      }    
        
        
        else if(s.equals("Submit"))
        {
            try
            {                
            
            Connection con=null;
            PreparedStatement pst=null;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con= DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
                
            String q="insert into Airlines_Book values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            pst=con.prepareStatement(q);
            
            Date date1=Date.valueOf(date);
            
            pst.setString(1, place);
            pst.setDate(2, date1 );
            pst.setInt(3, tickets);
            pst.setString(4, classType);
            pst.setString(5, PhoneNo);
            pst.setString(6, setA1);
            pst.setString(7, setA2);
            pst.setString(8, setA3);
            pst.setString(9, setA4);
            pst.setString(10, setA5);
            pst.setString(11, setB1);
            pst.setString(12, setB2);
            pst.setString(13, setB3);
            pst.setString(14, setB4);
            pst.setString(15, setB5);
            pst.setString(16, setC1);
            pst.setString(17, setC2);
            pst.setString(18, setC3);
            pst.setString(19, setC4);
            pst.setString(20, setC5);
            pst.setString(21, setD1);
            pst.setString(22, setD2);
            pst.setString(23, setD3);
            pst.setString(24, setD4);
            pst.setString(25, setD5);
            
           // System.out.println("hello");
            int a=pst.executeUpdate();
           
            
            con.close();
            pst.close();
            
             
            JOptionPane.showMessageDialog(this, "Seats are Selected");
            dispose();
            new Invoice();
            } 
            catch (ClassNotFoundException |SQLException ex) {
            
                System.out.println(ex);
            } 
        }
        else if(s.equals("Log Out"))
        {
            dispose();
            new Login();
        }
        else if(s.equals("Print Invoice"))
        {
            dispose();
            new Invoice();
        }
            
    }
}
