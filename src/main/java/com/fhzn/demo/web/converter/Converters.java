package com.fhzn.demo.web.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fhzn.commons.toolkit.entity.PageInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Converters {

    public static <F, T> PageInfo<T> convert2page(Page<F> page, Function<F, T> beanConverter) {
        List<T> list = page.getRecords().stream().map(beanConverter).collect(Collectors.toList());
        return PageInfo.builder(page.getTotal(), list).page((int) page.getCurrent()).pageSize((int) page.getSize()).build();
    }
}
