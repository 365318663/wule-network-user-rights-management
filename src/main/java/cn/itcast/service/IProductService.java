package cn.itcast.service;

import cn.itcast.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll(int page, int pageSize);

    void save(Product product);
}
