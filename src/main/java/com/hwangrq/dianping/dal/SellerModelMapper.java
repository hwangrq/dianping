package com.hwangrq.dianping.dal;

import com.hwangrq.dianping.model.SellerModel;

import java.util.List;

/**
 * @author hwangrq
 */
public interface SellerModelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SellerModel record);

    List<SellerModel> selectAll();

    int insertSelective(SellerModel record);

    SellerModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellerModel record);

    int updateByPrimaryKey(SellerModel record);
}
