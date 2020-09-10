package cn.itcast.dao;

import cn.itcast.domain.Traveller;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ITraveller {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{orderId})")
    Traveller findByOrdersId(@Param("orderId") String id);
}
