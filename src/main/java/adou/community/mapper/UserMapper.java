package adou.community.mapper;

import adou.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


public interface UserMapper {

    void save(User user);

    User findUserByToken(String token);
}
