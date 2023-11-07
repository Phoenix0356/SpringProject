package com.demo.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.demo.vo.ResultBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataUtil<T> {
    @Value("${avatar_storage.path}")
    private  String avatarPath;
    @Value("${avatar_storage.default}")
    private  String defaultAvatar;

    public QueryWrapper<T> getQueryWrapper(String tableField,String queryVale){
        QueryWrapper<T> queryWrapper=new QueryWrapper<>();
        return queryWrapper.eq(tableField,queryVale);
    }

//    public <U> UpdateWrapper<T> getUpdateWrapper(String queryTableField,String queryValue,
//                                                 String updateTableField,U updateVale){
//        UpdateWrapper<T> updateWrapper=new UpdateWrapper<>();
//        updateWrapper.eq(queryTableField,queryValue);
//        return updateWrapper.set(updateTableField,updateVale);
//    }
    public String saveAvatar(String fileName) {
        if (fileName==null||fileName.isEmpty()) return avatarPath+defaultAvatar;
        else return avatarPath+fileName;
    }
    public ResultBean isOperationSuccess(Integer flag,Object object){
        return flag>0?ResultBean.success("success",object):ResultBean.error("fail");
    }
    public ResultBean isOperationSuccess(Integer flag){
        return flag>0?ResultBean.success("success"):ResultBean.error("fail");
    }

}
