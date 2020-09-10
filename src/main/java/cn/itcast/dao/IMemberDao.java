package cn.itcast.dao;

import cn.itcast.domain.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {
    @Select("select * from member where id = #{id}")
    Member findById(@Param("id") String id);
}
