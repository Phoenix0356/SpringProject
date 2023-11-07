package com.demo.controller;

import com.demo.service.FavoritesService;
import com.demo.service.impl.FavoritesServiceImpl;
import com.demo.vo.ResultBean;
import com.demo.vo.param.FavoritesParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "FavoritesController")
@Controller
public class FavoritesController {
    @Autowired
    FavoritesService favoritesService;
    @ApiOperation("get favorites info by token")
    @GetMapping("/user/favorites")
    public ResultBean getFavoritesById(@RequestParam("favorite_id")Integer id){
        return favoritesService.getFavorById(id);
    }

    @ApiOperation("get favorites info by token")
    @PutMapping("/user/favorites/create")
    public ResultBean createFavoritesById(@RequestBody FavoritesParam favoritesParam){
        return favoritesService.createFavorites(favoritesParam);
    }
}
