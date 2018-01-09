package com.dascom.product.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dascom.product.constant.LoginConstant;
import com.dascom.product.entity.CpUser;
import com.dascom.product.service.ResourceService;
import com.dascom.product.service.UserService;
import com.dascom.product.util.JsonTransform;
import com.dascom.product.util.PagedResult;
import com.dascom.product.util.ValidateCode;

@Controller
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private ResourceService resourceServiceImpl;
	
	
	
	
	/**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     * @ValidateCode.generateTextCode(验证码字符类型,验证码长度,需排除的特殊字符)
     * @ValidateCode.generateImageCode(文本验证码,图片宽度,图片高度,干扰线的条数,字符的高低位置是否随机,图片颜色,字体颜色,干扰线颜色)
     */
    @RequestMapping(value = "validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_LOWER, 4, null);
        request.getSession().setAttribute("validateCode", verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 5, true, Color.WHITE, Color.BLUE, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }
	
	
	//跳转到登录首页
	@RequestMapping(value="login",produces="application/json;charset=utf-8")
	public String login(){
		return "Login/index";
	}
	
	//跳转到跳转提示
	@RequestMapping("loginHint")
	public String loginHint(HttpServletRequest request,String hint ){
		request.setAttribute("hint", hint);
		return "Login/loginHint";
	}
	//用户登录
	@RequestMapping(value="loginSubmit" ,method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String loginSubmit(HttpServletRequest request, String username,String password){
		//保存用户名
		request.getSession().setAttribute("username", username);
		Subject sub =SecurityUtils.getSubject(); ;
		Session session =sub.getSession();
		//系统的验证码
        String code = (String) session.getAttribute("validateCode");
        //用户输入的验证码
        String submitCode = WebUtils.getCleanParam(request, "validateCode");
        
        
       if (StringUtils.isEmpty(submitCode)  || !StringUtils.equals(code,submitCode.toLowerCase())) {
            return JsonTransform.loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_100000, LoginConstant.LOGIN_ERROR_MESSAGE_VALIDATECODE, null);
        }
        // 想要得到 SecurityUtils.getSubject() 的对象．．访问地址必须跟shiro的拦截地址内．不然后会报空指针 
       
      
        // 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
        // 认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
        // 当以上认证成功后会向下执行,认证失败会抛出异常
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            sub.login(token);
            //从subject中获取保存在安全管理员中的user对象
            CpUser cpUser = (CpUser) sub.getPrincipal();
            //将用户保存在session中,因为自定义拦截器的原因(如果没有自定义拦截器,将用户存放在session域中的这个步骤可以省略)
            request.getSession().setAttribute("cpUser", cpUser);
            return JsonTransform.loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_1000, LoginConstant.LOGIN_SUCCEED_MESSAGE_VALIDATECODE, null);
        } catch (LockedAccountException lae) {
            token.clear();
            return JsonTransform.loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_100002, LoginConstant.LOGIN_ERROR_MESSAGE_SYSTEMERROR, null);
        } catch (ExcessiveAttemptsException e) {	
            token.clear();
            return JsonTransform.loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_100003, "账号：" + username + LoginConstant.LOGIN_ERROR_MESSAGE_MAXERROR, null);
        } catch (Exception e) {
            token.clear();
            e.printStackTrace();
            return JsonTransform.loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_100001, LoginConstant.LOGIN_ERROR_MESSAGE_USERERROR, null);
        }
	}
	
	//主页
	@RequestMapping("index")
	public String index(){
		return "Index/index";
	}
	
	//首页欢迎页面
	@RequestMapping("welcome")
	public String welcome( HttpServletRequest request){
		//当前用户的ip地址. 
		String ip= request.getHeader("x-forwarded-for")!=null?request.getHeader("x-forwarded-for"):request.getRemoteAddr();
		
		//用户ip地址
		request.setAttribute("ip", ip);  
		return "Index/welcome";
	}
	
	//管理员列表
	@RequestMapping(value="findUserList")
	public String findUserList(HttpServletRequest request,String like ,@RequestParam(value="", defaultValue="1") Integer pageNumber,@RequestParam(value="",defaultValue="5")Integer pageSize){
		try {
			PagedResult<CpUser> userList=userServiceImpl.findByNamePage(like,pageNumber,pageSize);
			request.setAttribute("userList", userList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询管理员分页数据的时,出现异样:"+e);
		}
		request.setAttribute("like", like);
		return "Admin/userList";
	}
	
	//添加管理员页面
	@RequestMapping(value="addUser")
	public String addUser(){
		return "Admin/addUser";
	}
	
	@RequestMapping(value="addUserSubmit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String addUserSubmit(String  passwordTo ,CpUser user,Integer sexto){
		if(sexto!=null&&sexto==1){
			user.setSex(true);
		}else{
			user.setSex(false);
		}
		String error=userValiDate(user ,passwordTo);
		try {
			if(error!=null){
				return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, error, null);
			}
			int i=userServiceImpl.addUser(user);
			if(i>0){
				return JsonTransform.loginJsonTransform(LoginConstant.SUCCEED_CODE, LoginConstant.SUCCEED_MESSAGE, null);
			}if(i==-1){
				error ="用户名重复";
			}else{
				error=LoginConstant.ERROR_MESSAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("添加管理员时出现错误:"+e);
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, error==null?LoginConstant.ERROR_MESSAGE:error, null);
	}
	
	
	
	
	@RequestMapping(value="delUser",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public String delUser(String id){
		try {
			Integer e=userServiceImpl.delUser(id);
			if(e>0)
				return JsonTransform.loginJsonTransform(LoginConstant.SUCCEED_CODE, LoginConstant.SUCCEED_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("删除管理员时出现异常"+e);
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, LoginConstant.ERROR_MESSAGE, null);
	}
	//修改管理员
	@RequestMapping(value="updateUser")
	public String updateUser(HttpServletRequest request,Integer id ){
		try {
			CpUser user=userServiceImpl.findUserByKey(id);
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("在转发到更新管理员页面是出现错误.");
		}
		return "Admin/updateUser";
	}
	
	@RequestMapping(value="updateUserSubmit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateUserSubmit(String  passwordTo ,CpUser user,Integer sexto){
		if(sexto!=null&&sexto==1){
			user.setSex(true);
		}else{
			user.setSex(false);
		}
		String error=userValiDate(user ,passwordTo);
		try {
			if(error!=null){
				return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, error, null);
			}
			int i=userServiceImpl.updateUser(user);
			if(i>0){
				return JsonTransform.loginJsonTransform(LoginConstant.SUCCEED_CODE, LoginConstant.SUCCEED_MESSAGE, null);
			}if(i==-1){
				error ="用户名重复";
			}else{
				error=LoginConstant.ERROR_MESSAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("添加管理员时出现错误:"+e);
		}
		return JsonTransform.loginJsonTransform(LoginConstant.ERROR_CODE, error==null?LoginConstant.ERROR_MESSAGE:error, null);
	}
	
	
	
	
	
	private String userValiDate(CpUser user,String passwordTo){
		if(user.getUsername()==null||"".equals(user.getUsername())){
			return "用户名不能为空.";
		}
		if(user.getUsername().length()<=4&&user.getUsername().length()>=16){
			return "请填写4~16位的用户名.";
		}
		
		if(user.getPassword()==null||"".equals(user.getPassword())||passwordTo==null||"".equals(passwordTo)){
			return "密码或重复密码不能为空.";
		}
		if(!user.getPassword().equals(passwordTo)){
			return "二次密码不一样.";
		}
		if(user.getPassword().length()<=4&&user.getPassword().length()>=16){
			return "请填写4~16位的密码.";
		}
		return null;
	}
	
	
}
