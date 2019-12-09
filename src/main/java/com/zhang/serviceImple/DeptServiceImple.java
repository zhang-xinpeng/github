package com.zhang.serviceImple;

import com.zhang.annocation.CacheAnnocation;
import com.zhang.dao.DeptDao;
import com.zhang.entity.Dept;
import com.zhang.service.DeptService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptServiceImple implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @CacheAnnocation("find")
    public List<Dept> findAll(Integer page, Integer size) {
        return deptDao.selectByRowBounds(new Dept(), new RowBounds((page - 1) * size, size));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @CacheAnnocation("find")
    public Integer getCount() {
        return deptDao.selectCount(new Dept());
    }

    @Override
    @CacheAnnocation("edit")
    public void add(Dept dept) {
        deptDao.insertSelective(dept);
    }

    @Override
    @CacheAnnocation("edit")
    public void update(Dept dept) {
        deptDao.updateByPrimaryKeySelective(dept);
    }

    @Override
    @CacheAnnocation("edit")
    public void del(Dept dept) {
        deptDao.deleteByPrimaryKey(dept);
    }
}
