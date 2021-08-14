package com.hwangrq.dianping.service.impl;

import com.hwangrq.dianping.common.BusinessException;
import com.hwangrq.dianping.common.EnumBusinessError;
import com.hwangrq.dianping.dal.CategoryModelMapper;
import com.hwangrq.dianping.model.CategoryModel;
import com.hwangrq.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author hwangrq
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryModelMapper categoryModelMapper;

    @Override
    public CategoryModel create(CategoryModel categoryModel) throws BusinessException {
        Date now = new Date();
        categoryModel.setCreatedAt(now);
        categoryModel.setUpdatedAt(now);
        try{
            categoryModelMapper.insertSelective(categoryModel);
        }catch(DuplicateKeyException ex){
            throw new BusinessException(EnumBusinessError.CATEGORY_NAME_DUPLICATED);
        }
        return get(categoryModel.getId());
    }

    @Override
    public CategoryModel get(Integer id) {
        return categoryModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CategoryModel> selectAll() {
        return categoryModelMapper.selectAll();
    }

    @Override
    public Integer countAllCategory() {
        return categoryModelMapper.countAllCategory();
    }
}
