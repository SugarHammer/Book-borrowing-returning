package action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import entity.Book;
import service.BookService;
@SuppressWarnings("unused")
public class BookAction extends ActionSupport implements ModelDriven<Book> , ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = 1L;
	Book book = new Book();
	int page;
	int differ;
	private BookService bookService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public Book getModel() {//ModelDriven��������װģ������
		return book;
	}
    public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
    public void setServletResponse(HttpServletResponse response) {
    	this.response=response;
	}

	public BookService getBookService() {
		return bookService;
	}
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	public int getDiffer() {
		return differ;
	}
	public void setDiffer(int differ) {
		this.differ = differ;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	//���ͼ��
	public String add(){
		if("".equals(book.getName())){
			this.addActionMessage("��������Ϊ��");
			return "add";
		}
		bookService.add(book);
		this.addActionMessage("��ӳɹ�");
		return "add";
	}
	//ɾ��ͼ��
	public String delete(){
		bookService.delete(book);
		return "delete";
	}
	//ͨ��ͼ�����Ͳ���ͼ��
	public String showByType(){
		List<Book> list = bookService.findByType(book);
		ActionContext.getContext().put("list", list);
		return "showByType";
 	}
	//ͨ���ؼ��ֲ���ͼ��
	public String showByKey(){
		List<Book> list = bookService.findByKey(book);
		ActionContext.getContext().put("list", list);
		return "showByKey";
	}
	//ͨ���ȶȲ���ͼ��
	public String showByHot(){
		List<Book> list = bookService.findByHot();
		ActionContext.getContext().put("list", list);
		return "showByHot";
	}
	//��������ͼ��(���ҳ)
	public String showAll(){
		List<Book> list = bookService.findAll();
		int total = list.size();
		int size = 4;//ÿҳ������¼
		int pageTotal = 0;
		if(total < size){
			pageTotal = 1;
		}else if(total%size == 0){
			pageTotal = total/size;
		}else{
			pageTotal = total/size + 1;
		}
		if(page < 1){
			page = 1;
		}else if(page > pageTotal){
			page = pageTotal;
		}
		int start = (page - 1)*size;
		int end = page*size;
		if(end>total){
			end = total;
		}
		List<Book> list2 = list.subList(start, end);
		ActionContext.getContext().put("list", list2);
		ActionContext.getContext().put("total",total);
		ActionContext.getContext().put("pageTotal",pageTotal);
		ActionContext.getContext().put("page",page);
		if(differ==1){
			return "showAll";
		}else{
			return "searchBook";
		}
	}
	//��������ͼ��(�����ҳ)
	public String showAll2(){
		List<Book> list = bookService.findAll();
		ActionContext.getContext().put("list", list);
		return "showAll2";
	}
	//ͨ��ͼ���Ų�ѯͼ��
	public void findBookInfoById(){
		try {
			Thread.sleep(1000);//ģ�����
			List<Book> list = bookService.findBookInfoById(book.getId());//id��ͨ��ģ���������������,������������idĬ�ϻᱻģ��������������book��id,�����Լ����õ�id�ͻ�һֱΪ0
			Gson gson = new Gson();//ʹ��JSON��ת������GSON������򼯺�ת��JSON��ʽ���ַ���,����ֱ�Ӵ���JSON��ʽ�����鷳
			String json = gson.toJson(list);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
