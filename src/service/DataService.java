package service;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import dao.BookDao;
import dao.DataDao;
import dao.UserDao;
import entity.Book;
import entity.Data;
import entity.User;
@Transactional//事务管理
@SuppressWarnings("deprecation")
public class DataService {
	DataDao dataDao;
	BookDao bookDao;
	UserDao userDao;
	public DataDao getDataDao() {
		return dataDao;
	}
	public void setDataDao(DataDao dataDao) {
		this.dataDao = dataDao;
	}
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void add(Data data){
		Book book = bookDao.findById(data.getBid());
		book.setNum(book.getNum() - 1);
		bookDao.update(book);
		data.setName(book.getName());
		Date date=new Date();
		data.setCreateDate(date);
		date.setHours(date.getHours()+10);//应还日期假设为借书后10小时
		data.setBookReturn(date);
		dataDao.add(data);
	}
	public void delete(Data data){
		Data temp = dataDao.findById(data.getId());
		Book book = bookDao.findById(temp.getBid());
		if(book != null){//还书成功后设置图书数量+1
			book.setNum(book.getNum() + 1);
			bookDao.update(book);//更新book
		}
		dataDao.delete(temp);
	}
	public List<Data> findByUser(Data data){
		return dataDao.findByUser(data);
	}
	public List<Data> findAll(){
		return dataDao.findAll();
	}
	public void updateDate(Data data) {
		Data temp = dataDao.findById(data.getId());
		User user = userDao.findByName(temp.getUser());
		user.setCreateDate(new Date());//重新设置注册时间
		temp.setCreateDate(new Date());//借书时间默认与注册时间相同
		userDao.update(user);
		dataDao.update(temp);
	}
}
