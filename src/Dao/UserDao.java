package Dao;

import Bean.User;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    /**
     *@Author:李耀鹏
     *@param: * @param UserName
     *@Description:根据用户名获取用户
     *@Date: 15:21 2019/4/30
     */
    public User getUserByName(String UserName){
        User user = new User();
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from onlinemarket.user WHERE UserName = '" + UserName + "'"; // SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setPassWord(rs.getString("PassWord"));
                user.setUserType(rs.getInt("UserType"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setPhotoNumber(rs.getString("PhoneNumber"));
            }
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return user;
        } finally {
            releaseRs();
            releaseStat();
        }
    }

    public User getUserByID(int UserID){
        User user = new User();
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from onlinemarket.user WHERE UserID = " + UserID; // SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setPassWord(rs.getString("PassWord"));
                user.setUserType(rs.getInt("UserType"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setPhotoNumber(rs.getString("PhoneNumber"));
            }
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return user;
        } finally {
            releaseRs();
            releaseStat();
        }
    }

    public void addUser(User user){
        try {
            conn = DBHelper.getConnection();
            String sql = "insert into onlinemarket.user" +
                    "(UserName,PassWord,UserType,UserEmail,PhoneNumber) " +
                    "values(?,?,?,?,?)";
            stmt=conn.prepareStatement(sql);//创建一个Statement对象
           // stmt.setInt(1,UserID); //UserID为自增长主键
            stmt.setString(1,user.getUserName());
            stmt.setString(2,user.getPassWord());
            stmt.setInt(3,user.getUserType());
            stmt.setString(4,user.getUserEmail());
            stmt.setString(5,user.getPhoneNumber());
            stmt.executeUpdate();//执行sql语句
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            releaseStat();
        }
    }

    /**
     *@Author:李耀鹏
     *@param:
     *@Description:释放相关资源
     *@Date: 14:48 2019/4/25
     */
    private void releaseRs(){
        // 释放数据集对象
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    private void releaseStat(){
        // 释放语句对象
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
