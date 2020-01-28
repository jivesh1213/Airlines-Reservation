
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;


public class Invoice extends JFrame implements ActionListener{
 
    static JLabel head,invoice,cName,cAdd,cId,telephone,email,cust,printName,custPhone,printPhone,printPrice,invoiceno,reserno,details,Detail_heading,price;
    static JTable table1,table2;
    static JButton newBook,logOut;
    static JPanel panel;
    static String custName,cCity,phoneNo,Destination;
    static Date dateDepart;
    static Integer tickets;
    static int p;
    Invoice()
    {
        
        head=new JLabel("J-S-H AirLines");
        head.setForeground(Color.ORANGE);
        head.setFont(new Font("Serif",Font.BOLD,50));
        head.setBounds(280, 20, 600, 150);
        
        invoice=new JLabel("INVOICE");
        invoice.setForeground(Color.BLUE);
        invoice.setFont(new Font ("Serif",Font.BOLD,30));
        invoice.setBounds(340, 120, 300, 80);
        
        cName=new JLabel("JSH Airlines");
        cName.setFont(new Font("Serif",Font.BOLD,18));
        cName.setBounds(90, 210, 250, 25);
        
        cAdd=new JLabel("Address:  B-75,  ITO, Delhi-92");        
        cAdd.setFont(new Font("Serif",Font.BOLD,14));
        cAdd.setBounds(90, 240, 350, 25);
        
        cId=new JLabel("Company Id:  936395284");
        cId.setBounds(90, 270, 250, 25);
        cId.setFont(new Font("Serif",Font.BOLD,14));
        
        telephone=new JLabel("Telephone no:  +01122479103");
        telephone.setFont(new Font("Serif",Font.BOLD,14));
        telephone.setBounds(90, 300, 250, 25);
        
        email=new JLabel("Email:  jshAirlines@yahoo.co.in");
        email.setFont(new Font("Serif",Font.BOLD,14));
        email.setBounds(90, 330, 250, 25);
        
        cust=new JLabel("Customer: ");
        cust.setFont(new Font("Serif",Font.BOLD,18));        
        cust.setBounds(500,210 , 250, 25);
        
        custPhone=new JLabel("Phone no: ");
        custPhone.setFont(new Font("Serif",Font.BOLD,14));
        custPhone.setBounds(500, 240, 250, 25);
        
        invoiceno=new JLabel("Invoice No:  MV-6524");
        invoiceno.setFont(new Font("Serif",Font.BOLD,14));
        invoiceno.setBounds(500, 300, 250, 25);
        
        reserno=new JLabel("Reservation Number:  123");
        reserno.setFont(new Font("Serif",Font.BOLD,14));
        reserno.setBounds(500, 330, 250, 25);
        try{
            Connection con=null;
            PreparedStatement pst=null;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con= DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
                                   
            String q="Select * from Airlines_Users Cross Join Airlines_Book where Airlines_Users.PhoneNo=Airlines_Book.Phone_Number ";
            pst=con.prepareStatement(q);
            
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                custName=rs.getString("USERNAME");                   
                cCity=rs.getString("City");
                phoneNo=rs.getString("PhoneNo");
                Destination=rs.getString("Destination");
                tickets=rs.getInt("No_of_tickets");
                dateDepart=rs.getDate("Date_of_Departure");
               
            }
            
        }
        catch (ClassNotFoundException |SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
            }
       
        
                printName=new JLabel(custName);
                printName.setBounds(590, 210, 150, 25);
                printName.setFont(new Font("SansSerif",Font.ITALIC,17));
                printName.setForeground(Color.blue);
                
                printPhone=new JLabel(phoneNo);
                printPhone.setBounds(570,240, 150,25);                
                printPhone.setFont(new Font("SansSerif",Font.ITALIC,17));
                printPhone.setForeground(Color.BLUE);
        
        
        
        String [][]data1={
            { "Airlines ","Destination","Number of tickets","Ticket Number"},
            {"JSH Airlines",Destination,tickets.toString(),"38572463"}
        };
        
        String []columnNames1={
            "Airlines","Destination Place","Number of tickets","Ticket Number"
        };
        
        table1=new JTable(data1,columnNames1);
        table1.setBounds(90,430 , 750, 60);
        table1.setBackground(Color.LIGHT_GRAY);
        table1.setFont(new Font("SansSerif",Font.ITALIC,17));
        table1.setRowHeight(30);
        table1.setVisible(true);
        
        price=new JLabel("Total Price: ");
        price.setFont(new Font("Serif",Font.BOLD,17));      
        price.setBounds(640, 490, 100, 25);
        
        switch (Destination) {
            case "Mumbai":
                p = (tickets*4500);
                break;
            case "Bangalore":
                p = (tickets*3500);
                break;
            case "Kolkata":
                p = (tickets*3000);
                break;
            case "Chennai":
                p = (tickets*2500);
                break;
            case "Delhi":
                p = (tickets*4000);
                break;
            case "Ahmedabad":
                p = (tickets*2000);
                break;
            case "Goa":
                p = (tickets*4200);
                break;
        }
        
        JLabel printPrice=new JLabel("Rs "+String.valueOf(p));
         printPrice.setFont(new Font("SansSerif",Font.ITALIC,17));
         printPrice.setForeground(Color.BLUE);
        printPrice.setBounds(735, 490, 150, 30);
        
        details=new JLabel("Details: Overnight Stay included at Charles Airport Terminal Voucher Included in Package Only!! ");
        details.setBounds(90, 540, 650, 25);
        details.setFont(new Font("Serif",Font.CENTER_BASELINE,15));
        
        Detail_heading=new JLabel("DETAILS:");
        Detail_heading.setFont(new Font("Serif",Font.BOLD,18));
        Detail_heading.setBounds(90, 610, 650, 25);
        
        String[][] data2={ {" FROM ","  TO  ","Date of Departure","Flight Number","Departure Time","Arrival Time"},
                { cCity,Destination,dateDepart.toString(),"AC2001","1:00","14:30"}
          };
        
        String[]columnNames2={
            "From","To","Date","Flight Number","Departure Time","Arrival Time"
        };
        
        table2=new JTable(data2,columnNames2);
        table2.setBounds(90, 670, 750, 60);        
        table2.setBackground(Color.LIGHT_GRAY);
        table2.setRowHeight(30);
        table2.setFont(new Font("SansSerif",Font.ITALIC,15));
    
        
        newBook=new JButton("New Booking");
        newBook.setBounds(120, 750, 110, 30);
        newBook.addActionListener(this);
        
        logOut=new JButton("Log Out");
        logOut.setBounds(280, 750, 110, 30);
        logOut.addActionListener(this);
        
        panel=new JPanel();
        panel.setBounds(200,800,200,35);
        
        add(head);
        add(invoice);
        add(cName);
        add(cAdd);
        add(cId);
        add(telephone);
        add(email);
        add(cust);
        add(printName);
        add(custPhone);
        add(printPhone);
        add(invoiceno);
        add(reserno);
        add(table1);
        add(price);
        add(printPrice);
        add(details);
        add(Detail_heading);
        add(table2);
        add(newBook);
        add(logOut);
        add(panel);
    
        
        setSize(900,1000);       
        setLocation(550,10);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
        
        new Invoice();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
   
        String s=e.getActionCommand();
        
        if(s.equals("New Booking"))
        {
            dispose();
            new Login();
        }
        else if(s.equals("Log Out"))
        {
            dispose();
            
        }
    }
}
