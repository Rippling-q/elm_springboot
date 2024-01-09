package com.example.elm_springboot.dao;

import com.example.elm_springboot.entity.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessDao extends JpaRepository<Business,Long> {
    List<Business> findAllByOrderTypeId(Integer orderTypeId);
    Business findBusinessByBusinessId(Long businessId);

    @Query("select business from Business business")
    List<Business> findALL();
    Page<Business> findAllByOrderByStarPriceAsc(Pageable pageable);

    @Query("SELECT b FROM Business b WHERE b.businessName LIKE %:keywordName% OR EXISTS " +
            "(SELECT f FROM Food f WHERE f.business = b AND f.foodName LIKE %:keywordName%)")
    List<Business> searchBusinesses(@Param("keywordName") String keywordName);

}
