package dao;
import java.util.List;
import org.junit.Test;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import entity.User;
@SuppressWarnings("all")
public class UserDao extends HibernateDaoSupport{
	public void add(User user){
		this.getHibernateTemplate().save(user);
	}
	public void delete(User user){
		this.getHibernateTemplate().delete(user);
	}
	public void update(User user){
		this.getHibernateTemplate().update(user);
	}
	public User find(User user){
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where username=?", user.getUsername());
		if(list.isEmpty()) return null;
		return list.get(0);
	}
	public User findById(int uid){
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where uid=?", uid);
		if(list.isEmpty()) return null;
		return list.get(0);
	}
	public List<User> findAll(){
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where auth = 1");
		return list;
	}
	public List<User> findFine() {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where auth = 1 and uflag = '是'");
		return list;
	}
	public User findByName(String username) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where username=?", username);
		if(list.isEmpty()) return null;
		return list.get(0);
	}
}
