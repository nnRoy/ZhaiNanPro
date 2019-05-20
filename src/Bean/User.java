package Bean;

public class User
{
    //初始化
    private int UserID = 0;     //主键自增
    private String UserName = "";
    private String PassWord = "";
    private int UserType = 0;
    private String UserEmail = "";
    private String phoneNumber= "";

    public User(String UserName,String PassWord,int UserType,String UserEmail,String phoneNumber){
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.UserType = UserType;
        this.UserEmail = UserEmail;
        this.phoneNumber = phoneNumber;
    }
    public User(){}

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhotoNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
