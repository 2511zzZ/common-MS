package priv.zzz.service;

import org.springframework.stereotype.Service;
import priv.zzz.exception.ExampleException;
import priv.zzz.model.ExampleUser;

import java.util.List;

@Service
public interface UserService {

    List<ExampleUser> listUser();

    void saveUser(ExampleUser user) throws ExampleException;

    ExampleUser getUserByName(String username);

    void deleteUser(String username) throws ExampleException;

    void updateUser(ExampleUser user) throws ExampleException;
}
