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
import org.springframework.web.bind.annotation.*;

@Api(tags = "FavoritesController")
@RestController
public class FavoritesController {
    @Autowired
    FavoritesService favoritesService;
    @ApiOperation("get favorites info by token")
    @GetMapping("/favorites/get")
    public ResultBean getFavoritesById(@RequestParam("fav_id")Integer favId){
        return favoritesService.getFavorById(favId);
    }

    @ApiOperation("create favorites")
    @PostMapping("/favorites/create")
    public ResultBean createFavoritesById(@RequestBody FavoritesParam favoritesParam){
        return favoritesService.createFavorites(favoritesParam);
    }

    @ApiOperation("update favorite by id")
    @PutMapping("/favorites/update")
    public ResultBean updateFavoritesById(@RequestBody FavoritesParam favoritesParam){
        return favoritesService.updateFavorites(favoritesParam);
    }
    @ApiOperation("delete favorites by id")
    @DeleteMapping("/favorites/delete")
    public ResultBean deleteFavoritesById(@RequestParam("fav_id") Integer favId){
        return favoritesService.deleteFavorites(favId);
    }


}
