package dao;
import java.util.List;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import entity.Data;
@SuppressWarnings("all")
public class DataDao extends HibernateDaoSupport {
	public void add(Data data){
		this.getHibernateTemplate().save(data);
	}
	public void delete(Data data){
		this.getHibernateTemplate().delete(data);
	}
	public Data findById(int id){
		List<Data> list = (List<Data>) this.getHibernateTemplate().find("from Data where id=?", id);
		if(list.isEmpty()) return null;
		return list.get(0);
	}
	public List<Data> findAll(){
		List<Data> list = (List<Data>) this.getHibernateTemplate().find("from Data");
		return list;
	}
	public List<Data> findByUser(Data data){
		List<Data> list = (List<Data>) this.getHibernateTemplate().find("from Data where user=?", data.getUser());
		return list;
	}
	public void update(Data temp) {
		this.getHibernateTemplate().update(temp);
	}
}
