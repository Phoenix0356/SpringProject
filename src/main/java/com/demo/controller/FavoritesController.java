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
    @ApiOperation("get favorites info by id")
    @GetMapping("/favorites/get")
    public ResultBean getFavoritesById(@RequestParam("id")Integer id){
        return favoritesService.getFavorById(id);
    }

    @ApiOperation("get favorites list by token")
    @GetMapping("/favorites/home")
    public ResultBean getFavoritesByUserId(@RequestHeader("userId") Integer userId){
        return favoritesService.getFavorByUserId(userId);
    }

    @ApiOperation("create favorites")
    @PostMapping("/favorites/create")
    public ResultBean createFavoritesById(@RequestHeader("userId") Integer userId,@RequestBody FavoritesParam favoritesParam){
        return favoritesService.createFavorites(userId,favoritesParam);
    }

    @ApiOperation("update favorite by id")
    @PutMapping("/favorites/update")
    public ResultBean updateFavoritesById(@RequestBody FavoritesParam favoritesParam){
        return favoritesService.updateFavorites(favoritesParam);
    }
    @ApiOperation("delete favorites by id")
    @DeleteMapping("/favorites/delete")
    public ResultBean deleteFavoritesById(@RequestHeader("userId") Integer userId,@RequestParam("fav_id") Integer favId){
        return favoritesService.deleteFavorites(userId,favId);
    }


}
