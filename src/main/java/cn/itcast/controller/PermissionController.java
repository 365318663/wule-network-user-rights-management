package cn.itcast.controller;

import cn.itcast.domain.Permission;
import cn.itcast.domain.UserInfo;
import cn.itcast.service.IPermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
@Secured({"ROLE_ADMIN"})
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                @RequestParam(name = "pageSize",defaultValue = "4") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll(page,pageSize);
        PageInfo info = new PageInfo(permissions);
        mv.addObject("pageInfo",info);

        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll.do";

    }
    @RequestMapping("delete.do")
    public String delete(String id){
        permissionService.deleteByPermissionId(id);
        return "redirect:findAll.do";
    }

}
