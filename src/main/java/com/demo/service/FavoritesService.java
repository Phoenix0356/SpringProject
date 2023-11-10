package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Favorites;
import com.demo.vo.ResultBean;
import com.demo.vo.param.FavoritesParam;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

@Service
public interface FavoritesService extends IService<Favorites> {
    ResultBean getFavorById(Integer id);
    //获取一个用户的所有收藏条目
    ResultBean getFavorByUserId(Integer userId);

    ResultBean createFavorites(Integer userId, FavoritesParam favoritesParam);

    ResultBean updateFavorites(FavoritesParam favoritesParam);

    ResultBean deleteFavorites(Integer userId, Integer id);
}
