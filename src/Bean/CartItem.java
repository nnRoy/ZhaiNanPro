package Bean;

public class CartItem {
    private int CartItemID = 0;
    private int UserID = 0;
    private String CartItemName = "";
    private int CartItemNum = 0;
    private String CartItemPrice = "";
    private String CartItemAllPrice = "";
    private Boolean CartItemChecked = false;


    public int getCartItemID() {
        return CartItemID;
    }

    public void setCartItemID(int cartItemID) {
        CartItemID = cartItemID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getCartItemName() {
        return CartItemName;
    }

    public void setCartItemName(String cartItemName) {
        CartItemName = cartItemName;
    }

    public int getCartItemNum() {
        return CartItemNum;
    }

    public void setCartItemNum(int cartItemNum) {
        CartItemNum = cartItemNum;
    }

    public String getCartItemPrice() {
        return CartItemPrice;
    }

    public void setCartItemPrice(String cartItemPrice) {
        CartItemPrice = cartItemPrice;
    }

    public String getCartItemAllPrice() {
        return CartItemAllPrice;
    }

    public void setCartItemAllPrice(String cartItemAllPrice) {
        CartItemAllPrice = cartItemAllPrice;
    }

    public Boolean getCartItemChecked() {
        return CartItemChecked;
    }

    public void setCartItemChecked(Boolean cartItemChecked) {
        CartItemChecked = cartItemChecked;
    }
}
