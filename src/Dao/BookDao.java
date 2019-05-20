package Dao;
import Bean.Book;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.zip.DeflaterOutputStream;

public class BookDao {


    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    /**
     *@Author:李耀鹏
     *@param: * @param null
     *@Description:从 onlinemarket.bookitem表中查询所有商品信息
     *@Date: 22:08 2019/4/24
     */
    public ArrayList<Book> getAllBookItems() {

        ArrayList<Book> list = new ArrayList<Book>(); // 商品集合
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from onlinemarket.bookitem;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getInt("BookItemId"));
                book.setBookName(rs.getString("BookItemName"));
                book.setBookPrice(rs.getString("BookItemPrice"));
                book.setBookAuthor(rs.getString("BookItemAuthor"));
                book.setBookDescription(rs.getString("BookItemDes"));
                list.add(book);// 把一个商品加入集合
            }
            return list; // 返回集合。
        } catch (Exception ex) {
            ex.printStackTrace();
            return list;
        } finally {
            releaseRs();
            releaseStat();
        }

    }

    public Book getBookById(int bookId) {
        Book book = new Book();
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from onlinemarket.bookitem WHERE BookItemId = " + bookId; // SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
           if (rs.next()) {
                book.setBookID(rs.getInt("BookItemId"));
                book.setBookName(rs.getString("BookItemName"));
                book.setBookPrice(rs.getString("BookItemPrice"));
                book.setBookAuthor(rs.getString("BookItemAuthor"));
                book.setBookDescription(rs.getString("BookItemDes"));
            }
            return book;
        } catch (Exception ex) {
            ex.printStackTrace();
            return book;
        } finally {
            releaseRs();
            releaseStat();
        }
    }

    /**
     *@Author:李耀鹏
     *@param: * @param book
     *@Description:添加商品至数据库
     *@Date: 15:21 2019/5/1
     */
    public void addBook(Book book) {
        try {
            conn = DBHelper.getConnection();
            String sql = "insert into onlinemarket.bookitem" +
                    "(BookItemName,BookItemPrice,BookItemAuthor,BookItemDes) " +
                    "values(?,?,?,?)";
            stmt=conn.prepareStatement(sql);//创建一个Statement对象
            //stmt.setInt(1,1);//BookItemId为主键自增长
            stmt.setString(1,book.getBookName());
            stmt.setDouble(2,Double.parseDouble(book.getBookPrice()));
            stmt.setString(3,book.getBookAuthor());
            stmt.setString(4,book.getBookDescription());
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
     *@param: * @param productID
     *@Description:根据商品ID从数据库中删除该商品
     *@Date: 15:50 2019/5/1
     */
    public void delProductByID(int productID){
        try {
            conn = DBHelper.getConnection();
            String sql = "delete  from onlinemarket.bookitem WHERE BookItemId = " + productID ; // SQL语句
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            releaseStat();
        }
    }

    public void modifyProInfo(Book book) {
        try {
            conn = DBHelper.getConnection();
            String sql = "UPDATE onlinemarket.bookitem " +
                    "SET BookItemName = '" + book.getBookName() + "', BookItemPrice = " + Double.parseDouble(book.getBookPrice()) +
                    ", BookItemAuthor = '" + book.getBookAuthor() + "', BookItemDes = '" + book.getBookDescription() +
                    "' WHERE BookItemName = '" + book.getBookName() + "'";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
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
