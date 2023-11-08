package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Favorites;
import com.demo.vo.ResultBean;
import com.demo.vo.param.FavoritesParam;
import org.springframework.stereotype.Service;

@Service
public interface FavoritesService extends IService<Favorites> {
    ResultBean getFavorById(Integer id);

    ResultBean getFavorByUserId(String token);

    ResultBean createFavorites(String token,FavoritesParam favoritesParam);

    ResultBean updateFavorites(FavoritesParam favoritesParam);

    ResultBean deleteFavorites(String token,Integer id);
}
