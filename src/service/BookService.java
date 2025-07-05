package service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import dao.BookDao;
import entity.Book;
@Transactional
public class BookService {
	BookDao bookDao;
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public void add(Book book){
		bookDao.add(book);
	}
	public void delete(Book book){
		bookDao.delete(bookDao.findById(book.getId()));
	}
	public List<Book> findByType(Book book){
		return bookDao.findByType(book);
	}
	public List<Book> findByKey(Book book){
		return bookDao.findByKey(book);
	}
	public List<Book> findByHot() {
		return bookDao.findByHot();
	}
	public List<Book> findAll(){
		 return bookDao.findAll();
	}
	public List<Book> findBookInfoById(int id) {
		List<Book> oldlist = bookDao.findBookInfoById(id);//δ����ǰ
		//�û��鿴һ��ͼ������,ͼ���ȶ�+1
		for(int i=0;i<oldlist.size();i++){
			oldlist.get(i).setHot(oldlist.get(i).getHot()+1);
			bookDao.update(oldlist.get(i));//����book
		}
		List<Book> newlist = bookDao.findBookInfoById(id);//���º�
		return newlist;
	}
}
