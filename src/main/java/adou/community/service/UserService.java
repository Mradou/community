package adou.community.service;

import adou.community.mapper.UserMapper;
import adou.community.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findUserByAccount_id(user.getAccount_id());
        if (dbUser == null){
            //插入
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.save(user);
        }else {
            //更新
            dbUser.setGmt_modified(System.currentTimeMillis());
            dbUser.setAvatar_url(user.getAvatar_url());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }
    }
}
