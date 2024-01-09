package com.example.elm_springboot.service;

import com.example.elm_springboot.entity.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBusinessService {
    List<Business> listBusinessByOrderTypeId(Integer orderType);
    List<Business> listAll();
    Business getBusinessById(Long businessId);
    Page<Business> listBusinessByStarPriceAsc(Pageable pageable);
    List<Business> listBySearchContent(String searchContent);
}
