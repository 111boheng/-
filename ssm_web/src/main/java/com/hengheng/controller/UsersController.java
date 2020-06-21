package com.hengheng.controller;

import com.hengheng.domain.Role;
import com.hengheng.domain.Users;
import com.hengheng.service.IUsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @RequestMapping("/login.do")
    public String login(Users users){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(),users.getPassword());
        System.out.println("usercontroller-------"+users);
        try{
            subject.login(token);
        }catch (Exception e){
            return "redirect:/403.jsp";
        }

        return "main";
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Users> usersList = usersService.findAll();
        mv.addObject("userList",usersList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Users user){
        usersService.save(user);

        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam("id")int id){
        ModelAndView mv = new ModelAndView();
        Users user = usersService.findById(id);
        if (user != null) {

            mv.addObject("user", user);
            mv.setViewName("user-show");
        }else {
            mv.setViewName("403");
        }
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) int userid){
        ModelAndView mv = new ModelAndView();
        //查询userid对应的对象
        Users user = usersService.findById(userid);
        //根据userid查询当前用户没有的角色信息
        List<Role> otherRoles = usersService.findOtherRoles(userid);
        mv.addObject("user",user);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId")int userid,@RequestParam(name = "ids")int[] ids){
//        Map<Integer,Integer> map = new HashMap<>();
//        int i ;
//        for(i=0;i<ids.length;i++){
//            map.put(userid,ids[i]);
//        }
//        System.out.println(map);
        usersService.addRoleToUser(userid,ids);


        return "redirect:findAll.do";
    }
}
