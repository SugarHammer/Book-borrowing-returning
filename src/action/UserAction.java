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
    //登录
	public String login(){
		if("".equals(user.getUsername())){
			this.addActionMessage("用户名不能为空");
			return "loginFailed";
		}
		if("".equals(user.getPassword())){
			this.addActionMessage("密码不能为空");
			return "loginFailed";
		}
		MD5Utils key = new MD5Utils();
		user.setPassword(key.getMD5ofStr(user.getPassword()));
		User loginUser = userService.login(user);
		if(loginUser == null){
			this.addActionMessage("用户或密码错误");
			return "loginFailed";
		}else{
			ActionContext.getContext().getSession().put("loginUser", loginUser);
			return "loginSucceed";
		}
	}
	//退出
	public String logout(){
		ActionContext.getContext().getSession().clear();
		return "logout";
	}
	//注册--默认注册普通用户
	public String add(){
		if("".equals(user.getUsername())){
			this.addActionMessage("用户名不能为空");
			return "addFailed";
		}
		if("".equals(user.getPassword())){
			this.addActionMessage("密码不能为空");
			return "addFailed";
		}
		if(!pass.equals(user.getPassword())){
			this.addActionMessage("两次输入密码不一致");
			return "addFailed";
		}
		MD5Utils key = new MD5Utils();//引入MD5Utils类
		user.setPassword(key.getMD5ofStr(user.getPassword()));//用户密码进行加密
		if(userService.add(user)){
			this.addActionMessage("注册成功");
			return "addSucceed";
		}else{
			this.addActionMessage("注册失败，用户名已存在");
			return "addFailed";
		}
	}
    //修改密码
	public String editPass(){
		if("".equals(user.getPassword())){
			this.addActionMessage("密码不能为空");
			return "editPass";
		}
		if(!pass.equals(user.getPassword())){
			this.addActionMessage("两次输入密码不一致");
			return "editPass";
		}
		MD5Utils key = new MD5Utils();//引入MD5Utils类
		user.setPassword(key.getMD5ofStr(user.getPassword()));//用户密码进行加密
		userService.update(user);
		this.addActionMessage("修改成功");
		return "editPass";
	}
	//删除用户
	public String delete(){
		userService.delete(user);
		return "delete";
	}
	//显示所有用户
	public String showAll(){
		List<User> list = userService.findAll();
		ActionContext.getContext().put("list", list);
		return "showAll";
	}
	//显示逾期用户
	public String showFine(){
		List<User> list = userService.findFine();
		ActionContext.getContext().put("list", list);
		return "showFine";
	}
}
