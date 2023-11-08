package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.Favorites;
import com.demo.mapper.FavoritesMapper;
import com.demo.service.FavoritesService;
import com.demo.util.DataUtil;
import com.demo.util.JwtTokenUtil;
import com.demo.vo.ResultBean;
import com.demo.vo.param.FavoritesParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;
    @Autowired
    private DataUtil<Favorites> dataUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    //获取单条收藏条目
    @Override
    public ResultBean getFavorById(Integer id){
        return ResultBean.success("success",favoritesMapper.selectById(id));
    }
    //获取一个用户的所有收藏条目
    @Override
    public ResultBean getFavorByUserId(String token){
        Integer userId=jwtTokenUtil.getUserIdFromToken(token);
        return ResultBean.success("success",favoritesMapper.selectFavoritesByUserId(userId));
    }

    @Override
    public ResultBean createFavorites(String token,FavoritesParam favoritesParam){
        Integer userId=jwtTokenUtil.getUserIdFromToken(token);
        Favorites favorites=new Favorites();
        favorites.setItemName(favoritesParam.getItemName());
        favorites.setIntroduction(favoritesParam.getIntroduction());
        favorites.setUrl(favoritesParam.getUrl());
        favoritesMapper.insert(favorites);
        return dataUtil.isOperationSuccess(favoritesMapper.insertRelation(userId,favorites.getId()));
    }
    @Override
    public ResultBean updateFavorites(FavoritesParam favoritesParam){
        Favorites favorites=favoritesMapper.selectById(favoritesParam.getId());
        favorites.setItemName(favoritesParam.getItemName());
        favorites.setIntroduction(favoritesParam.getIntroduction());
        favorites.setUrl(favoritesParam.getUrl());
        return dataUtil.isOperationSuccess(favoritesMapper.updateById(favorites),favorites);
    }

    @Override
    public ResultBean deleteFavorites(String token,Integer id){
        Integer userId=jwtTokenUtil.getUserIdFromToken(token);
        favoritesMapper.deleteRelation(userId,id);
        return dataUtil.isOperationSuccess(favoritesMapper.deleteById(id));
    }
}
