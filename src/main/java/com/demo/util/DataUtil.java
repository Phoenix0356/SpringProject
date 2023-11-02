package com.demo.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataUtil {
    @Value("${avatar_storage.path}")
    private  String avatarPath;
    @Value("${avatar_storage.default}")
    private  String defaultAvatar;

    public  <T> QueryWrapper<T> getQueryWrapper(Class<T> clazz,
                                                     String tableField,String queryVale){
        QueryWrapper<T> queryWrapper=new QueryWrapper<>();
        return queryWrapper.eq(tableField,queryVale);
    }

    public <T,U> UpdateWrapper<T> getUpdateWrapper(Class<T> clazz,
                                                          String queryTableField,String queryValue,
                                                          String updateTableField,U updateVale){
        UpdateWrapper<T> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq(queryTableField,queryValue);
        return updateWrapper.set(updateTableField,updateVale);
    }

    public String saveAvatar(String fileName) {
        if (fileName==null||fileName.isEmpty()) return avatarPath+defaultAvatar;
        else return avatarPath+fileName;
    }
}
