package com.dataspring.dataspring.controller;

import com.dataspring.dataspring.entity.data;
import com.dataspring.dataspring.response.dataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequestMapping("/index")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class indxController {
    @Autowired
    private dataResponse dataResponse;
//获取data列表
    @GetMapping("/findAll")
    public List<data> pass(){
        List<data> data = dataResponse.findAll();
        System.out.println(data);
        return data;
    }
//储存信息
    @PutMapping("/saveData")
    public String saveData(@RequestBody data data){
        data passData = dataResponse.save(data);
        if (passData!=null){
            return "ok";
        }
        return "no";
    }
//获取待更新数据
    @GetMapping("/getData/{name}")
    public data  GetDta(@PathVariable("name") String name){
        data data = dataResponse.findById(name).get();
        return data;
    }
//更新数据
    @PutMapping("/putData")
    public String upData(@RequestBody data data){
        data passData = dataResponse.save(data);
        if (passData!=null){
            return "ok";
        }
        return "no";
    }
    @DeleteMapping("/deleteData/{name}")
    public String deleteData(@PathVariable("name") String namme){
        dataResponse.deleteById(namme);
        return "ok";
    }
}
