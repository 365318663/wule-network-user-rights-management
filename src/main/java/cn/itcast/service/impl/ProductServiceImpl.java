package cn.itcast.service.impl;

import cn.itcast.dao.IProductDao;
import cn.itcast.domain.Product;
import cn.itcast.service.IProductService;
import cn.itcast.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao dao;
    @Override
    public List<Product> findAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return dao.findAll();
    }

    @Override
    public void save(Product product) {
        product.setId(UuidUtil.getUuid());

        dao.save(product);
    }
}
