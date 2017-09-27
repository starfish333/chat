package cloud2.socket;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/msg")
public class MsgController {
	@Autowired
	MyWebSocketHandler handler;
	static Map<Long, User> users = new HashMap<Long, User>();
	static {
		User u1 = new User();
		u1.setUid(1L);
		u1.setName("一头小小猪");
		users.put(u1.getUid(), u1);
		User u2 = new User();
		u2.setUid(2L);
		u2.setName("一条小小鱼");
		users.put(u2.getUid(), u2);
	}

	//模拟一些数据
	@ModelAttribute
	public void setReqAndRes() {

	}

	//用户登录
	@RequestMapping(value = "login")
	public ModelAndView doLogin(User user, HttpServletRequest request) {
		request.getSession().setAttribute("uid", user.getUid());
		request.getSession().setAttribute("name", users.get(user.getUid()).getName());
		return new ModelAndView("redirect:../talk.jsp");
	}

	//跳转到交谈聊天页面
	@RequestMapping(value = "talk", method = RequestMethod.GET)
	public ModelAndView talk() {
		return new ModelAndView("redirect:../talk.jsp");

	}

	//跳转到发布广播页面
	@RequestMapping(value = "broadcast", method = RequestMethod.GET)
	public ModelAndView broadcast() {
		return new ModelAndView("redirect:../broadcast.jsp");
	}

	//发布系统广播（群发）
	@ResponseBody
	@RequestMapping(value = "broadcast", method = RequestMethod.POST)
	public void broadcast(String text) throws IOException {
		Message msg = new Message();
		msg.setDate(new Date());
		msg.setFrom(-1L);
		msg.setFromName("系统广播");
		msg.setTo(0L);
		msg.setText(text);
		handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
	}
}
