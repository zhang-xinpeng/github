package com.zhang.controller;

import com.zhang.entity.Dept;
import com.zhang.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("findAll")
    public Map<String, Object> findALll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<Dept> depts = deptService.findAll(page, rows);
        Integer count = deptService.getCount();
        int i = count / rows;
        if (count % rows != 0) {
            i++;
        }
        map.put("page", page);
        map.put("records", count);
        map.put("total", i);
        map.put("rows", depts);
        return map;
    }

    @RequestMapping("edit")
    public void edit(String oper, Dept dept) {
        if ("add".equals(oper)) {
            dept.setId(UUID.randomUUID().toString());
            deptService.add(dept);
        } else if ("edit".equals(oper)) {
            deptService.update(dept);
        } else {
            deptService.del(dept);
        }
    }
}
