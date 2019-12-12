package com.blue.hospital.controller;

import com.blue.hospital.entity.User;
import com.blue.hospital.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getUser/{id}")
    public String getUser(@PathVariable int id){
        System.out.println("查找用户");
        return userService.findUserById(id).toString();
    }

    @RequestMapping(value = "/dologin")
    public String defaultLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();

        Session session = subject.getSession();
        User user = userService.findUserByName(username);
        System.out.println(user);
        session.setAttribute("user",user);
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {

            subject.login(token);

        } catch (UnknownAccountException uae) {
            model.addAttribute("msg","未知账户");
            return "index";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg","密码不正确");
            return "index";
        } catch (LockedAccountException lae) {
            model.addAttribute("msg","账户已锁定");
            return "index";
        } catch (ExcessiveAttemptsException eae) {
            model.addAttribute("msg","用户名或密码错误次数过多");
            return "index";
        } catch (AuthenticationException ae) {
            model.addAttribute("msg","用户名或密码不正确");
            return "index！";
        }
        if (subject.isAuthenticated()) {
            model.addAttribute("msg","登录成功");
            return "index";
        } else {
            token.clear();
            model.addAttribute("msg","登录失败");
            return "index";
        }
    }

    @RequestMapping("/zhuce")
    public String getZhuce(){
        return "zhuce";
    }
}
