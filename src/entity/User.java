package entity;
import java.util.Date;
public class User {
	private int uid;
	private String username;
	private String password;
	private int auth;//用户权限 默认0表示管理员 1表示普通用户
	private Date createDate;
	private int account=200;//用户账户额度,默认每人200
	private String uflag="否";//是否逾期 默认否
	private int bookquota=5;//借书限额 默认5本
	private int amountborrowed;//已借数量
	public int getBookquota() {
		return bookquota;
	}
	public void setBookquota(int bookquota) {
		this.bookquota = bookquota;
	}
	public int getAmountborrowed() {
		return amountborrowed;
	}
	public void setAmountborrowed(int amountborrowed) {
		this.amountborrowed = amountborrowed;
	}
	public String getUflag() {
		return uflag;
	}
	public void setUflag(String uflag) {
		this.uflag = uflag;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
