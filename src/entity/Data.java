package entity;
import java.util.Date;
public class Data {
	private int id;
	private int bid;
	private String name;
	private String user;
	private Date createDate;//假设注册时间和借书时间相同
	private Date bookReturn;//还书日期
	public Date getBookReturn() {
		return bookReturn;
	}
	public void setBookReturn(Date bookReturn) {
		this.bookReturn = bookReturn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
