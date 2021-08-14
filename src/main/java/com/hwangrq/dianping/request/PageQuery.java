package com.hwangrq.dianping.request;

import lombok.Data;

/**
 * @author hwangrq
 */
@Data
public class PageQuery {

    private Integer page = 1;

    private Integer size = 20;

}
