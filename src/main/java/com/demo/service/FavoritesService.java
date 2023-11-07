package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Favorites;
import com.demo.vo.ResultBean;
import com.demo.vo.param.FavoritesParam;
import org.springframework.stereotype.Service;

@Service
public interface FavoritesService extends IService<Favorites> {
    ResultBean getFavorById(Integer favId);

    ResultBean createFavorites(FavoritesParam favoritesParam);

    ResultBean updateFavorites(FavoritesParam favoritesParam);

    ResultBean deleteFavorites(Integer favId);
}
