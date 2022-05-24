/*
RMIT University Vietnam
INTE2512 Final Project - News Aggregator App
Nguyen Anh Minh
S3911237
 */
package sample;

import java.sql.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ConnectDatabase {
    public static Connection connectDataBase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newsfeed", "root", "areyouroot");
            return conn;
        } catch (Exception ex) {
            System.out.println("Connection failure");
        }
        return null;
    }
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    public boolean loginDb(String username,String password)
    {
        connection = connectDataBase();
        String sql = "select * from login where username=?;";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,username);
            //pst.setString(2,password_txt.getText());
            rs = pst.executeQuery();
            if (rs.next())
            {
                //JOptionPane.showMessageDialog(null, "username and password are correct");
                String pass=password;
                if (rs.getNString("pwd").equals(pass))
                {
                    connection.close();
                    return true;
                }
            }
            else
            {
                System.out.println("no username");
            }
            connection.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }        
        return false;
    }
    public void registerDb(String username,String password,String country,Map<String, Integer> m)
    {
        connection = connectDataBase();
        String sql = "insert into login (username,pwd,country,business,entertainment,general,health,science,sports,technology) values (?,?,?,?,?,?,?,?,?,?)";
        try
        {
            pst = connection.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,country);
            pst.setString(4,Integer.toString(m.get("Geography")));
            pst.setString(5,Integer.toString(m.get("Entertainment")));
            pst.setString(6,Integer.toString(m.get("Medicine")));
            pst.setString(7,Integer.toString(m.get("Food")));
            pst.setString(8,Integer.toString(m.get("Science")));
            pst.setString(9,Integer.toString(m.get("Sports")));
            pst.setString(10,Integer.toString(m.get("ArtificialIntelligence")));
            pst.execute();
            //conn.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }        
    }
    
    public String getcountry(String username)
    {
        connection = connectDataBase();
        String sql = "select country from login where username=?";
        try
        {
            pst = connection.prepareStatement(sql);
            pst.setString(1,username);
            rs=pst.executeQuery();
            if (rs.next())
            {
                return rs.getNString("country");
            }
            //conn.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return "";
    }
    
    public Queue<Integer> getpreference(String username)
    {
        Queue<Integer> prefs=new LinkedList<>();
        connection = connectDataBase();
        String sql = "select * from login where username=?";
        try
        {
            pst = connection.prepareStatement(sql);
            pst.setString(1,username);
            rs=pst.executeQuery();
            if (rs.next())
            {
                prefs.add(rs.getInt("Geography"));
                prefs.add(rs.getInt("Entertainment"));
                prefs.add(rs.getInt("Medicine"));
                prefs.add(rs.getInt("Food"));
                prefs.add(rs.getInt("Science"));
                prefs.add(rs.getInt("Sports"));
                prefs.add(rs.getInt("ArtificialIntelligence"));
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return prefs;
    }
    
    public void editDb(String username,String country,Map<String, Integer> m)
    {
        connection = connectDataBase();
        String sql = "update login set country=?,business=?,entertainment=?,general=?,health=?,science=?,sports=?,technology=? where username=?";
        try
        {
            pst = connection.prepareStatement(sql);
            pst.setString(1,country);
            pst.setString(2,Integer.toString(m.get("Geography")));
            pst.setString(3,Integer.toString(m.get("Entertainment")));
            pst.setString(4,Integer.toString(m.get("Medicine")));
            pst.setString(5,Integer.toString(m.get("Food")));
            pst.setString(6,Integer.toString(m.get("Science")));
            pst.setString(7,Integer.toString(m.get("Sports")));
            pst.setString(8,Integer.toString(m.get("ArtificialIntelligence")));
            pst.setString(9,username);
            pst.execute();
            //conn.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }        
    }
}
