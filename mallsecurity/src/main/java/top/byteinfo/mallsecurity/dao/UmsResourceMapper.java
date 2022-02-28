package top.byteinfo.mallsecurity.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.byteinfo.mallsecurity.entity.UmsResource;

import java.util.List;
@Repository
@Mapper
public interface UmsResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsResource record);

    UmsResource selectByPrimaryKey(Long id);

    List<UmsResource> selectAll();

    int updateByPrimaryKey(UmsResource record);
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

}