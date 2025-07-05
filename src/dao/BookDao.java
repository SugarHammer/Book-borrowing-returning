package dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import entity.Book;
@SuppressWarnings("unchecked")
public class BookDao extends HibernateDaoSupport {

	public void add(Book book){
		this.getHibernateTemplate().save(book);
	}
	
	public void delete(Book book){
		this.getHibernateTemplate().delete(book);
	}
	
	public void update(Book book){
		this.getHibernateTemplate().update(book);
	}
	
	public Book findById(int id){
		List<Book> list = (List<Book>) this.getHibernateTemplate().find("from Book where id=?", id);
		if(list.isEmpty()) return null;
		return list.get(0);
	}
	
	public List<Book> findByType(Book book){
		List<Book> list = (List<Book>) this.getHibernateTemplate().find("from Book where type=?", book.getType());
		return list;
	}
	
	public List<Book> findByKey(Book book){
		List<Book> list = (List<Book>) this.getHibernateTemplate().find("from Book where name like '%"+book.getName()+"%'");
		return list;
	}
	
	public List<Book> findByHot() {
		List<Book> list = (List<Book>) this.getHibernateTemplate().find("from Book order by hot desc");
		return list;
	}
	
	public List<Book> findAll(){
		List<Book> list = (List<Book>) this.getHibernateTemplate().find("from Book");
		return list;
	}

	public List<Book> findBookInfoById(int id) {
		List<Book> list = (List<Book>) this.getHibernateTemplate().find("from Book where id=?",id);
		return list;
	}
}
