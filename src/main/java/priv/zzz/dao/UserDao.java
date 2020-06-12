package priv.zzz.dao;

import org.apache.ibatis.annotations.Mapper;
import priv.zzz.model.ExampleUser;

import java.util.List;

@Mapper
public interface UserDao {

    List<ExampleUser> listUser();

    void saveUser(ExampleUser user);

    ExampleUser getUserByName(String username);

    void deleteUser(String username);

    void updateUser(ExampleUser user);
}
