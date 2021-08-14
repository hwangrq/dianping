package com.hwangrq.dianping.service;

import com.hwangrq.dianping.common.BusinessException;
import com.hwangrq.dianping.model.SellerModel;

import java.util.List;

/**
 * @author hwangrq
 */
public interface SellerService {

    SellerModel create(SellerModel sellerModel);

    SellerModel get(Integer id);

    List<SellerModel> selectAll();

    SellerModel changeStatus(Integer id,Integer disabledFlag) throws BusinessException;
}
