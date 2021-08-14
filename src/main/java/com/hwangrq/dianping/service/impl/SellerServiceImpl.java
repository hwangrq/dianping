package com.hwangrq.dianping.service.impl;

import com.hwangrq.dianping.common.BusinessException;
import com.hwangrq.dianping.common.EnumBusinessError;
import com.hwangrq.dianping.dal.SellerModelMapper;
import com.hwangrq.dianping.model.SellerModel;
import com.hwangrq.dianping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author hwangrq
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerModelMapper sellerModelMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SellerModel create(SellerModel sellerModel) {
        Date date = new Date();
        sellerModel.setCreatedAt(date);
        sellerModel.setUpdatedAt(date);
        sellerModel.setDisabledFlag(0);
        sellerModel.setRemarkScore(new BigDecimal(0));
        sellerModelMapper.insert(sellerModel);
        return get(sellerModel.getId());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SellerModel get(Integer id) {
        return sellerModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SellerModel> selectAll() {
        return sellerModelMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SellerModel changeStatus(Integer id, Integer disabledFlag) throws BusinessException {
        SellerModel sellerModel = get(id);
        if (sellerModel == null) {
            throw new BusinessException(EnumBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        sellerModel.setDisabledFlag(disabledFlag);
        sellerModelMapper.updateByPrimaryKeySelective(sellerModel);
        return sellerModel;
    }
}
