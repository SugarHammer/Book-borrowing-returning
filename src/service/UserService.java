package service;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import dao.UserDao;
import entity.User;
@Transactional
public class UserService {
	UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User login(User user){
		User temp = userDao.find(user);
		if(temp == null){
			return null;
		}
		if(!temp.getPassword().equals(user.getPassword())){
			return null;
		}
		return temp;
	}
	public void update(User user){
		User temp = userDao.find(user);
		temp.setPassword(user.getPassword());
		userDao.update(temp);
	}
	public boolean add(User user){
		User temp = userDao.find(user);
		if(temp != null) return false;
		user.setAuth(1);
		user.setCreateDate(new Date());
		userDao.add(user);
		return true;
	}
	public void delete(User user){
		User temp = userDao.findById(user.getUid());
		userDao.delete(temp);
	}
	public List<User> findAll(){
		return userDao.findAll();
	}
	public List<User> findFine() {
		List<User> list = userDao.findAll();
		Date date=new Date();
		long time=date.getTime();
		for(int i=0;i<list.size();i++){
			//假设超过10小时算逾期
			if(time-list.get(i).getCreateDate().getTime()>1000*60*60*10){
				list.get(i).setUflag("是");
				list.get(i).setAccount(150);//逾期扣50
				userDao.update(list.get(i));
			}
		}
		return userDao.findFine();
	}
}
