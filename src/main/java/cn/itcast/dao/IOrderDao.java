package cn.itcast.dao;

import cn.itcast.domain.Member;
import cn.itcast.domain.Orders;

import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDao {
    @Select( "select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "oderNum",column = "oderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "oderNum",column = "oderNum"),
            @Result(property = "product",column = "productId",javaType = Product.class,one =
                @One(select = ("cn.itcast.dao.IProductDao.findById"))),


    })
    List<Orders> findAll();


    @Select( "select * from orders where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "oderNum",column = "oderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "oderNum",column = "oderNum"),
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one = @One(select = ("cn.itcast.dao.IProductDao.findById"))),
            @Result(property = "member",column = "memberId",javaType = Member.class,
                    one = @One(select = "cn.itcast.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "cn.itcast.dao.ITraveller.findByOrdersId"))

    })
    Orders findById(@Param("id") String id);
}
