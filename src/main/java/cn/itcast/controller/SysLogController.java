package cn.itcast.controller;

import cn.itcast.domain.SysLog;
import cn.itcast.service.ISysLogService;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
@Secured({"ROLE_ADMIN"})
public class SysLogController {

    @Autowired
    ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<SysLog> logs = sysLogService.findAll(page,pageSize);
        PageInfo info = new PageInfo(logs);
        mv.addObject("pageInfo",info);
        mv.setViewName("syslog-list");
        return  mv;
    }
}
