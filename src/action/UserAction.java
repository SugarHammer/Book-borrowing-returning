package action;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import entity.User;
import service.UserService;
import utils.MD5Utils;
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;
	User user = new User();
	UserService userService;
	String pass;
	public User getModel() {
		return user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    //��¼
	public String login(){
		if("".equals(user.getUsername())){
			this.addActionMessage("�û�������Ϊ��");
			return "loginFailed";
		}
		if("".equals(user.getPassword())){
			this.addActionMessage("���벻��Ϊ��");
			return "loginFailed";
		}
		MD5Utils key = new MD5Utils();
		user.setPassword(key.getMD5ofStr(user.getPassword()));
		User loginUser = userService.login(user);
		if(loginUser == null){
			this.addActionMessage("�û����������");
			return "loginFailed";
		}else{
			ActionContext.getContext().getSession().put("loginUser", loginUser);
			return "loginSucceed";
		}
	}
	//�˳�
	public String logout(){
		ActionContext.getContext().getSession().clear();
		return "logout";
	}
	//ע��--Ĭ��ע����ͨ�û�
	public String add(){
		if("".equals(user.getUsername())){
			this.addActionMessage("�û�������Ϊ��");
			return "addFailed";
		}
		if("".equals(user.getPassword())){
			this.addActionMessage("���벻��Ϊ��");
			return "addFailed";
		}
		if(!pass.equals(user.getPassword())){
			this.addActionMessage("�����������벻һ��");
			return "addFailed";
		}
		MD5Utils key = new MD5Utils();//����MD5Utils��
		user.setPassword(key.getMD5ofStr(user.getPassword()));//�û�������м���
		if(userService.add(user)){
			this.addActionMessage("ע��ɹ�");
			return "addSucceed";
		}else{
			this.addActionMessage("ע��ʧ�ܣ��û����Ѵ���");
			return "addFailed";
		}
	}
    //�޸�����
	public String editPass(){
		if("".equals(user.getPassword())){
			this.addActionMessage("���벻��Ϊ��");
			return "editPass";
		}
		if(!pass.equals(user.getPassword())){
			this.addActionMessage("�����������벻һ��");
			return "editPass";
		}
		MD5Utils key = new MD5Utils();//����MD5Utils��
		user.setPassword(key.getMD5ofStr(user.getPassword()));//�û�������м���
		userService.update(user);
		this.addActionMessage("�޸ĳɹ�");
		return "editPass";
	}
	//ɾ���û�
	public String delete(){
		userService.delete(user);
		return "delete";
	}
	//��ʾ�����û�
	public String showAll(){
		List<User> list = userService.findAll();
		ActionContext.getContext().put("list", list);
		return "showAll";
	}
	//��ʾ�����û�
	public String showFine(){
		List<User> list = userService.findFine();
		ActionContext.getContext().put("list", list);
		return "showFine";
	}
}
