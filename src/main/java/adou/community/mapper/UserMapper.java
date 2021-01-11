package adou.community.mapper;

import adou.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


public interface UserMapper {

    void save(User user);

    User findUserByToken(String token);

    User findUserById(Integer creator);

    User findUserByAccount_id(String account_id);

    void update(User dbUser);
}
