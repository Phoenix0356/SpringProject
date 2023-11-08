package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.Favorites;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoritesMapper extends BaseMapper<Favorites> {
    @Select("SELECT favorites.* " +
            "FROM user_favorites_relation " +
            "JOIN favorites ON user_favorites_relation.favorites_id = favorites.id " +
            "WHERE user_favorites_relation.user_id = #{userId}")
    List<Favorites> selectFavoritesByUserId(@Param("userId") int userId);

    @Insert("INSERT INTO user_favorites_relation (user_id, favorites_id) VALUES (#{userId}, #{favoriteId})")
    int insertRelation(@Param("userId") Integer uerId, @Param("favoriteId") Integer favoriteId);

    @Delete("DELETE FROM user_favorites_relation " +
            "WHERE user_favorites_relation.user_id = #{userId} " +
            "AND user_favorites_relation.favorites_id=#{favId}")
    void deleteRelation(@Param("userId") Integer userId,@Param("favId") Integer favId);



}
