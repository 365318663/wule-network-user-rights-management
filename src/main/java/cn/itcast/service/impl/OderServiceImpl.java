package cn.itcast.service.impl;

import cn.itcast.dao.IOrderDao;
import cn.itcast.domain.Orders;
import cn.itcast.service.IOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderServiceImpl implements IOrderService {

    @Autowired
    IOrderDao orderDao;

    @Override
    public List<Orders> findAll(int page, int pageSize) {

        PageHelper.startPage(page,pageSize);
        return orderDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        return orderDao.findById(id);
    }
}
