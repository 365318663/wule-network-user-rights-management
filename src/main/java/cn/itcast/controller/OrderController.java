package cn.itcast.controller;

import cn.itcast.domain.Orders;
import cn.itcast.service.IOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                 @RequestParam(name = "pageSize",defaultValue = "4") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = orderService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(orders);
        System.out.println(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findAll(@RequestParam(name = "id",defaultValue = "0") String id){
        ModelAndView mv = new ModelAndView();
        Orders orders = orderService.findById(id);
//        PageInfo pageInfo = new PageInfo(orders);
        System.out.println(orders);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
