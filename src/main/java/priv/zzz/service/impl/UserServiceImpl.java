package priv.zzz.service.impl;

import org.springframework.stereotype.Service;
import priv.zzz.dao.UserDao;
import priv.zzz.exception.ExampleException;
import priv.zzz.model.ExampleUser;
import priv.zzz.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    public List<ExampleUser> listUser() {
        return userDao.listUser();
    }

    @Override
    public void saveUser(ExampleUser user) throws ExampleException {
        if (userDao.getUserByName(user.getUsername()) != null){
            throw new ExampleException("用户已存在");
        }
        userDao.saveUser(user);
    }

    @Override
    public ExampleUser getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public void deleteUser(String username) throws ExampleException {
        if (userDao.getUserByName(username) == null){
            throw new ExampleException("用户不存在");
        }
        userDao.deleteUser(username);

    }

    @Override
    public void updateUser(ExampleUser user) throws ExampleException {
        if (userDao.getUserByName(user.getUsername()) == null){
            throw new ExampleException("用户不存在");
        }
        userDao.updateUser(user);
    }
}
