package top.byteinfo.mallsecurity.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.byteinfo.mallsecurity.entity.UmsAdmin;

import java.util.List;
@Mapper
@Repository
public interface UmsAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdmin record);

    UmsAdmin selectByPrimaryKey(Long id);

    List<UmsAdmin> selectAll();

    int updateByPrimaryKey(UmsAdmin record);
    List<UmsAdmin> selectByPrimaryName(String username);
}