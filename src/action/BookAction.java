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
	public Book getModel() {//ModelDriven拦截器封装模型驱动
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
	//添加图书
	public String add(){
		if("".equals(book.getName())){
			this.addActionMessage("书名不能为空");
			return "add";
		}
		bookService.add(book);
		this.addActionMessage("添加成功");
		return "add";
	}
	//删除图书
	public String delete(){
		bookService.delete(book);
		return "delete";
	}
	//通过图书类型查找图书
	public String showByType(){
		List<Book> list = bookService.findByType(book);
		ActionContext.getContext().put("list", list);
		return "showByType";
 	}
	//通过关键字查找图书
	public String showByKey(){
		List<Book> list = bookService.findByKey(book);
		ActionContext.getContext().put("list", list);
		return "showByKey";
	}
	//通过热度查找图书
	public String showByHot(){
		List<Book> list = bookService.findByHot();
		ActionContext.getContext().put("list", list);
		return "showByHot";
	}
	//查找所有图书(需分页)
	public String showAll(){
		List<Book> list = bookService.findAll();
		int total = list.size();
		int size = 4;//每页四条记录
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
	//查找所有图书(无需分页)
	public String showAll2(){
		List<Book> list = bookService.findAll();
		ActionContext.getContext().put("list", list);
		return "showAll2";
	}
	//通过图书编号查询图书
	public void findBookInfoById(){
		try {
			Thread.sleep(1000);//模拟加载
			List<Book> list = bookService.findBookInfoById(book.getId());//id是通过模型驱动拦截器获得,上面设置属性id默认会被模型驱动器拦截至book的id,这样自己设置的id就会一直为0
			Gson gson = new Gson();//使用JSON的转换工具GSON将对象或集合转成JSON格式的字符串,避免直接传递JSON格式过于麻烦
			String json = gson.toJson(list);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
