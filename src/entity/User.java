package entity;
import java.util.Date;
public class User {
	private int uid;
	private String username;
	private String password;
	private int auth;//�û�Ȩ�� Ĭ��0��ʾ����Ա 1��ʾ��ͨ�û�
	private Date createDate;
	private int account=200;//�û��˻����,Ĭ��ÿ��200
	private String uflag="��";//�Ƿ����� Ĭ�Ϸ�
	private int bookquota=5;//�����޶� Ĭ��5��
	private int amountborrowed;//�ѽ�����
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
