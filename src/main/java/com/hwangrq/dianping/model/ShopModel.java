package com.hwangrq.dianping.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hwangrq
 */
@Data
public class ShopModel {

    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    private String name;

    private BigDecimal remarkScore;

    private Integer pricePerMan;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Integer distance;

    private Integer categoryId;

    private CategoryModel categoryModel;

    private String tags;

    private String startTime;

    private String endTime;

    private String address;

    private Integer sellerId;

    private SellerModel sellerModel;

    private String iconUrl;
}
