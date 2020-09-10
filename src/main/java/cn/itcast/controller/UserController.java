package cn.itcast.controller;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import cn.itcast.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
@Secured({"ROLE_ADMIN"})
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                @RequestParam(name = "pageSize",defaultValue = "4") Integer pageSize){
        ModelAndView mv  = new ModelAndView();
        List<UserInfo> all = userService.findAll(page,pageSize);
        PageInfo info = new PageInfo(all);
        mv.addObject("pageInfo",info);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("save.do")
    private String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";

    }

    @RequestMapping("findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo info = userService.findByid(id);
        mv.addObject("user",info);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(required = true) String id){
        ModelAndView mv = new ModelAndView();
        UserInfo info = userService.findByid(id);
        List<Role> roles = userService.findOherRoles(id);
        mv.addObject("user",info);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name="userId",required = true) String userId,
                                @RequestParam(name="ids",required = true) String [] roleId){
        userService.addRolesToUser(userId,roleId);
        return "redirect:findAll.do";
    }
}
