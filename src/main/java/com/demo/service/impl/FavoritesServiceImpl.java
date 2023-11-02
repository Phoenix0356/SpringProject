package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.Favorites;
import com.demo.mapper.FavoritesMapper;
import com.demo.service.FavoritesService;
import com.demo.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;

public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;

    @Override
    public ResultBean getFavorById(Integer id){
        Favorites favorites=favoritesMapper.selectById(id);
        if (favorites!=null) return ResultBean.success("success",favorites);
        else return ResultBean.error("failure");
    }



}
