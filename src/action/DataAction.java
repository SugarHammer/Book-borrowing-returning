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
	//����
	public String add(){
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		data.setUser(loginUser.getUsername());
		dataService.add(data);
		return "add";
	}
	//����
	public String delete(){
		dataService.delete(data);
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		if(loginUser.getAuth() == 0) return "delete2";
		return "delete";
	}
	//����
	public String updateDate(){
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		data.setUser(loginUser.getUsername());
		dataService.updateDate(data);
		return "updateDate";
	}
	//��ʾ��¼�û�������Ϣ
	public String showByUser(){
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		data.setUser(loginUser.getUsername());
		List<Data> list = dataService.findByUser(data);
		ActionContext.getContext().put("list", list);
		return "showByUser";
	}
	//ͬ������ʾ��¼�û�������Ϣ
	public String showByUserAndRenew(){
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		data.setUser(loginUser.getUsername());
		List<Data> list = dataService.findByUser(data);
		ActionContext.getContext().put("list", list);
		return "showByUserAndRenew";
	}
	//����������ʾ�����û��Ľ������
	public String showAll(){
		List<Data> list = dataService.findAll();
		ActionContext.getContext().put("list", list);
		return "showAll";
	}
}
