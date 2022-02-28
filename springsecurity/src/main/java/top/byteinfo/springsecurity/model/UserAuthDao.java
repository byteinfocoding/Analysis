package top.byteinfo.springsecurity.model;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 用户账号
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Repository
@Mapper
public interface UserAuthDao extends BaseMapper<UserAuth> {

//    /**
//     * 查询后台用户列表
//     *
//     * @param current   页码
//     * @param size      大小
//     * @param condition 条件
//     * @return {@link List<UserBackDTO>} 用户列表
//     */
//    List<UserBackDTO> listUsers(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);
//
//    /**
//     * 查询后台用户数量
//     *
//     * @param condition 条件
//     * @return 用户数量
//     */
//    Integer countUser(@Param("condition") ConditionVO condition);

}
