package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Category;
import org.example.service.CategoryService;
import org.example.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【t_category】的数据库操作Service实现
* @createDate 2024-07-26 15:31:49
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




