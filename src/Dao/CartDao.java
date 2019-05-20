package Dao;

import Bean.Book;
import Bean.CartItem;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDao {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    /**
     *@Author:李耀鹏
     *@param: * @param book 加入购物车的Item
     *          @param UserID 该用户的ID
     *@Description:把该book加入购物车
     *@Date: 20:27 2019/4/25
     */
    public void addProductToCart(Book book,int UserID){

        try {
            conn = DBHelper.getConnection();
            String sql = "insert into onlinemarket.shoppingcart" +
                    "(UserID,CartItemName,CartItemPrice,CartItemNum,CartItemAllPrice,CartItemChecked) " +
                    "values(?,?,?,?,?,?)";
            stmt=conn.prepareStatement(sql);//创建一个Statement对象
            //stmt.setInt(1,1);//CartItemID为主键自增长
            stmt.setInt(1,UserID);
            stmt.setString(2,book.getBookName());
            stmt.setString(3,book.getBookPrice());
            //加入购物车的该商品数目初始值设为1
            stmt.setInt(4,1);
            stmt.setString(5,book.getBookPrice());
            //商品初始为选中状态
            stmt.setInt(6,1);
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
     *@param: * @param UserID用户ID
     *@Description:获取某一用户的购物车内的所有Item
     *@Date: 20:26 2019/4/25
     */
    public ArrayList<CartItem> getItemsByUserID(int UserID) {

        ArrayList<CartItem> cartItemList = new ArrayList<CartItem>(); // 商品集合
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from onlinemarket.shoppingcart WHERE UserID = " + UserID; // SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setUserID(rs.getInt("UserID"));
                cartItem.setCartItemID(rs.getInt("CartItemID"));
                cartItem.setCartItemName(rs.getString("CartItemName"));
                cartItem.setCartItemPrice(rs.getString("CartItemPrice"));
                cartItem.setCartItemNum(rs.getInt("CartItemNum"));
                cartItem.setCartItemAllPrice(rs.getString("CartItemAllPrice"));
                cartItem.setCartItemChecked(rs.getBoolean("CartItemChecked"));
                cartItemList.add(cartItem);// 把一个商品加入集合
            }
            return cartItemList; // 返回集合。
        } catch (Exception ex) {
            ex.printStackTrace();
            return cartItemList;
        } finally {
            releaseRs();
            releaseStat();
        }

    }
    /**
     *@Author:李耀鹏
     *@param: * @param UserID
     *@Description:根据UserID清空相应的用户的购物车
     *@Date: 15:55 2019/4/26
     */
    public void deleteAllCartItem(int UserID){
        try {
            conn = DBHelper.getConnection();
            String sql = "delete from onlinemarket.shoppingcart where UserID = " + UserID;  // SQL语句
            stmt=conn.prepareStatement(sql);//创建一个Statement对象
            stmt.executeUpdate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            releaseStat();
        }
    }

    /**
     *@Author:李耀鹏
     *@param: * @param UserID
     * @param cartItemID
     *@Description:根据UserID从相应的购物车中删除cartItemID对应的cartItem
     *@Date: 21:36 2019/4/26
     */
    public void removeCartItemByID(int UserID,int cartItemID){

        try {
            conn = DBHelper.getConnection();
            //conn.setAutoCommit(false);
            String sql = "delete  from onlinemarket.shoppingcart WHERE UserID = " + UserID + " and CartItemID = " + cartItemID; // SQL语句
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
     *@param: * @param UserID
     *@Description:根据UserID从获取相应的购物车的商品种类数
     *@Date: 0:44 2019/4/28
     */
    /*public int getCartItemNumByUserID(int UserID){

        int CartItemNum = 0;
        try {
            conn = DBHelper.getConnection();
            String sql = "select count(*) CartItemNum from  onlinemarket.shoppingcart where UserID = " + UserID;  // SQL语句
            stmt=conn.prepareStatement(sql);//创建一个Statement对象
            rs = stmt.executeQuery();
            if(rs.next()){
                CartItemNum = rs.getInt("CartItemNum");
            }
            return CartItemNum;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return CartItemNum;
        }
        finally {
            releaseRs();
            releaseStat();
        }
    }*/

    /**
     *@Author:李耀鹏
     *@param: * @param CartItemID
     * @param UserID
     * @param CartItemNum  更新后商品在购物车中的数量
     * @param CartItemAllPrice   更新后商品在购物车中的总金额
     *@Description:更新购物车中某一项商品的数目以及该商品的总金额
     *@Date: 16:15 2019/4/28
     */
    public void updateCartItemNumByID(int CartItemID,int UserID,int CartItemNum,String CartItemAllPrice){

        try {
            conn = DBHelper.getConnection();
            String sql = "UPDATE onlinemarket.shoppingcart " +
                    "SET CartItemNum = " + CartItemNum + ", CartItemAllPrice = " + CartItemAllPrice +
                    " WHERE CartItemID = " + CartItemID +  " and UserID = " + UserID;
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
     *@param: * @param CartItemID
     * @param UserID
     *@Description:根据CartItemID以及UserID获取相应的商品
     *@Date: 16:24 2019/4/28
     */
    public CartItem getCartItemByID(int CartItemID,int UserID){

        CartItem cartItem = new CartItem();
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from onlinemarket.shoppingcart" +
                    " WHERE CartItemID = " + CartItemID +  " and UserID = " + UserID;
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if(rs.next()) {
                cartItem.setUserID(rs.getInt("UserID"));
                cartItem.setCartItemID(rs.getInt("CartItemID"));
                cartItem.setCartItemName(rs.getString("CartItemName"));
                cartItem.setCartItemPrice(rs.getString("CartItemPrice"));
                cartItem.setCartItemNum(rs.getInt("CartItemNum"));
                cartItem.setCartItemAllPrice(rs.getString("CartItemAllPrice"));
                cartItem.setCartItemChecked(rs.getBoolean("CartItemChecked"));
            }
            return cartItem; // 返回集合。
        } catch (Exception ex) {
            ex.printStackTrace();
            return cartItem;
        } finally {
            releaseRs();
            releaseStat();
        }
    }

    /**
     *@Author:李耀鹏
     *@param: * @param CartItemID
     * @param UserID
     * @param updateState  checkBox转变为的状态（0/1）
     *@Description:更新购物车数据库中的CartItemChecked字段
     *@Date: 18:11 2019/4/28
     */
    public void updeteCheckBoxStateByID(int CartItemID,int UserID,int updateState){
        try {
            conn = DBHelper.getConnection();
            String sql = "UPDATE onlinemarket.shoppingcart SET CartItemChecked = " + updateState  +
                    " WHERE CartItemID = " + CartItemID +  " and UserID = " + UserID;
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
     *@param: * @param UserID
     *@Description:获取该用户选中的商品
     *@Date: 20:30 2019/4/28
     */
    public ArrayList<CartItem> getCheckedItemListByUserID(int UserID){

        ArrayList<CartItem> CheckedItemList = new ArrayList<CartItem>(); // 商品集合
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from onlinemarket.shoppingcart WHERE UserID = " + UserID + " and CartItemChecked = 1"; // SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setUserID(rs.getInt("UserID"));
                cartItem.setCartItemID(rs.getInt("CartItemID"));
                cartItem.setCartItemName(rs.getString("CartItemName"));
                cartItem.setCartItemPrice(rs.getString("CartItemPrice"));
                cartItem.setCartItemNum(rs.getInt("CartItemNum"));
                cartItem.setCartItemAllPrice(rs.getString("CartItemAllPrice"));
                cartItem.setCartItemChecked(rs.getBoolean("CartItemChecked"));
                CheckedItemList.add(cartItem);// 把一个商品加入集合
            }
            return CheckedItemList; // 返回集合。
        } catch (Exception ex) {
            ex.printStackTrace();
            return CheckedItemList;
        } finally {
            releaseRs();
            releaseStat();
        }
    }

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
