package com.demo.Util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public class DataUtil {
    //get QueryWrapper
    public <T> QueryWrapper<T> getQuerWrapper(Class<T> clazz,
                                                     String tableField,String queryVale){
        QueryWrapper<T> queryWrapper=new QueryWrapper<>();
        return queryWrapper.eq(tableField,queryVale);
    }
    //get UpdateWrapper
    public <T,U> UpdateWrapper<T> getUpdateWrapper(Class<T> clazz,
                                                          String queryTableField,String queryValue,
                                                          String updateTableField,U updateVale){
        UpdateWrapper<T> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq(queryTableField,queryValue);
        return updateWrapper.set(updateTableField,updateVale);
    }

}
