package cn.itcast.service;

import cn.itcast.domain.Orders;

import java.util.List;

public interface IOrderService {
    List<Orders> findAll(int page, int pageSize);

    Orders findById(String id);
}
