package top.byteinfo.nblogsecurity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.byteinfo.nblogsecurity.entity.User;

import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
    User selectByPrimaryName(String username);
}