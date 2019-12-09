package com.zhang.serviceImple;

import com.zhang.annocation.CacheAnnocation;
import com.zhang.dao.UserDao;
import com.zhang.entity.User;
import com.zhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class UserServiceImple implements UserService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @CacheAnnocation("find")
    public void login(User user) {
        User u = new User();
        u.setUsername(user.getUsername());
        User u2 = userDao.selectOne(u);
        if (u2 == null) throw new RuntimeException("用户名错误");
        if (!u2.getPassword().equals(user.getPassword())) throw new RuntimeException("密码错误");
        request.getSession().setAttribute("user", u2);
    }
}
