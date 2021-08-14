package com.hwangrq.dianping.model;

import lombok.Data;

import java.util.Date;

/**
 * @author hwangrq
 */
@Data
public class CategoryModel {

    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    private String name;

    private String iconUrl;

    private Integer sort;
}
