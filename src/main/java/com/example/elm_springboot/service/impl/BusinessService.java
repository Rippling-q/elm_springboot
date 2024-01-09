package com.example.elm_springboot.service.impl;

import com.example.elm_springboot.dao.BusinessDao;
import com.example.elm_springboot.entity.Business;
import com.example.elm_springboot.service.IBusinessService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService implements IBusinessService {
    @Resource
    private BusinessDao bDao;
    @Override
    public List<Business> listBusinessByOrderTypeId(Integer orderType) {
        return bDao.findAllByOrderTypeId(orderType);
    }

    @Override
    public List<Business> listAll() {
        return bDao.findALL();
    }

    @Override
    public Business getBusinessById(Long businessId) {
        return bDao.findBusinessByBusinessId(businessId);
    }

    @Override
    public Page<Business> listBusinessByStarPriceAsc(Pageable pageable) {
          return bDao.findAllByOrderByStarPriceAsc(pageable);
    }

    @Override
    public List<Business> listBySearchContent(String searchContent) {
        return bDao.searchBusinesses(searchContent);
    }
}
