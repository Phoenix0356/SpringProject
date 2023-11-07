package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.Favorites;
import com.demo.mapper.FavoritesMapper;
import com.demo.service.FavoritesService;
import com.demo.util.DataUtil;
import com.demo.vo.ResultBean;
import com.demo.vo.param.FavoritesParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;
    @Autowired
    private DataUtil dataUtil;

    @Override
    public ResultBean getFavorById(Integer id){
        Favorites favorites=favoritesMapper.selectById(id);
        if (favorites!=null) return ResultBean.success("success",favorites);
        else return ResultBean.error("failure");
    }

    @Override
    public ResultBean createFavorites(FavoritesParam favoritesParam){
        Favorites favorites=new Favorites();
        favorites.setItemName(favoritesParam.getItemName());
        favorites.setIntroduce(favoritesParam.getIntroduce());
        favorites.setUrl(favoritesParam.getUrl());
        return dataUtil.isOperationSuccess(favoritesMapper.insert(favorites),favorites);
    }
    @Override
    public ResultBean updateFavorites(FavoritesParam favoritesParam){
        Favorites favorites=favoritesMapper.selectById(favoritesParam.getId());
        favorites.setItemName(favoritesParam.getItemName());
        favorites.setIntroduce(favoritesParam.getIntroduce());
        favorites.setUrl(favorites.getUrl());
        return dataUtil.isOperationSuccess(favoritesMapper.updateById(favorites),favorites);
    }

    @Override
    public ResultBean deleteFavorites(Integer id){
        return dataUtil.isOperationSuccess(favoritesMapper.deleteById(id));
    }



}
