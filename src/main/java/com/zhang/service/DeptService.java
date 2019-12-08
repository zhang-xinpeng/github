package com.zhang.service;

import com.zhang.entity.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll(Integer page, Integer size);

    Integer getCount();

    void add(Dept dept);

    void update(Dept dept);

    void del(Dept dept);


}
