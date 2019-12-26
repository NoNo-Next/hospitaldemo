package com.blue.hospital.controller;

import com.blue.hospital.entity.Student;
import com.blue.hospital.entity.User;
import com.blue.hospital.service.StudentExportService;
import com.blue.hospital.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return "login1";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestParam("username") String username, @RequestParam("password") String password,String verifyCodeId, Model model) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> result = new HashMap<String,Object>();
        Session session = subject.getSession();
        System.out.println(verifyCodeId);
       String sessionCode = (String) session.getAttribute("codeNumber");
       try {
           if (!sessionCode.equals(verifyCodeId)){
                throw new Exception();
           }
       }catch (Exception e){
           result.put("res","验证码错误");
           return result;
       }

        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
            subject.isRemembered();

        } catch (UnknownAccountException uae) {
            result.put("res","未知账户");
            return result;
        } catch (IncorrectCredentialsException ice) {
            result.put("res","密码不正确");
            return result;
        } catch (LockedAccountException lae) {
            result.put("res","账户已锁定");
            return result;
        } catch (ExcessiveAttemptsException eae) {
            result.put("res","用户名或密码错误次数过多");
            return result;
        } catch (AuthenticationException ae) {
            result.put("res","用户名或密码不正确");
            return result;
        }
        if (subject.isAuthenticated()) {
            result.put("res","登录成功");
            return result;
        } else {
            token.clear();
            result.put("res","用户名或密码不正确");
            return result;
        }
    }

    @RequestMapping("index")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/zhuce")
    public String getZhuce(){
        return "zhuce";
    }

    @RequestMapping("/loginout")
    public String getLoginout(HttpServletRequest request, HttpSession session){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();//使用logout方法退出登录

            //清空session
            session = request.getSession(true);
            session.removeAttribute("user");
            session.invalidate();
        }catch (Exception e){

        }
        return "redirect:dologin";
    }

    /**
     * 注册
     */
    @RequestMapping("dozhuce")
    public void doZhuce(User user){
        try {
            if (userService.createUser(user)){
                System.out.println("注册成功");
            }
            return ;
        }catch (Exception e){
            return ;
        }

    }


    @Autowired
    StudentExportService studentExportService;

    @RequestMapping(value = "/export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student(1000,"zhangsan","20"));
        list.add(new Student(1001,"lisi","23"));
        list.add(new Student(1002,"wangwu","25"));
        HSSFWorkbook wb = studentExportService.export(list);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=student.xls");
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}
