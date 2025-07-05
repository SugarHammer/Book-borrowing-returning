package action;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import entity.Data;
import entity.User;
import service.DataService;
public class DataAction extends ActionSupport implements ModelDriven<Data> {
	private static final long serialVersionUID = 1L;
	Data data = new Data();
	DataService dataService;
	public Data getModel() {
		return data;
	}
	public DataService getDataService() {
		return dataService;
	}
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	//借书
	public String add(){
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		data.setUser(loginUser.getUsername());
		dataService.add(data);
		return "add";
	}
	//还书
	public String delete(){
		dataService.delete(data);
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		if(loginUser.getAuth() == 0) return "delete2";
		return "delete";
	}
	//续借
	public String updateDate(){
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		data.setUser(loginUser.getUsername());
		dataService.updateDate(data);
		return "updateDate";
	}
	//显示登录用户借书信息
	public String showByUser(){
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		data.setUser(loginUser.getUsername());
		List<Data> list = dataService.findByUser(data);
		ActionContext.getContext().put("list", list);
		return "showByUser";
	}
	//同样是显示登录用户借书信息
	public String showByUserAndRenew(){
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		data.setUser(loginUser.getUsername());
		List<Data> list = dataService.findByUser(data);
		ActionContext.getContext().put("list", list);
		return "showByUserAndRenew";
	}
	//不加区别显示所有用户的借书情况
	public String showAll(){
		List<Data> list = dataService.findAll();
		ActionContext.getContext().put("list", list);
		return "showAll";
	}
}
