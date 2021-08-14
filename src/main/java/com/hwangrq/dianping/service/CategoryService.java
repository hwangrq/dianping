package com.hwangrq.dianping.service;

import com.hwangrq.dianping.common.BusinessException;
import com.hwangrq.dianping.model.CategoryModel;

import java.util.List;

/**
 * @author hwangrq
 */
public interface CategoryService {

    CategoryModel create(CategoryModel categoryModel) throws BusinessException;
    CategoryModel get(Integer id);
    List<CategoryModel> selectAll();

    Integer countAllCategory();
}
