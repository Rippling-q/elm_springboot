package com.example.elm_springboot.controller;

import com.example.elm_springboot.config.FileHandler.FileHandler;
import com.example.elm_springboot.dto.BusinessDTO;
import com.example.elm_springboot.entity.Business;
import com.example.elm_springboot.service.IBusinessService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/BusinessController")
public class BusinessController {
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private IBusinessService businessService;

    @Resource
    private FileHandler fileHandler;

    @GetMapping("/listAll")
    public List<BusinessDTO> listAll(){
        List<Business> businesses = businessService.listAll();
        return businesses.stream().map(business -> modelMapper.map(business, BusinessDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getBusinessById")
    public BusinessDTO getBusinessById(@RequestParam Long businessId){
        Business business = businessService.getBusinessById(businessId);
        return modelMapper.map(business, BusinessDTO.class);
    }

    @GetMapping("/listBusinessByOrderTypeId")
    public List<BusinessDTO> listBusinessByOrderTypeId(@RequestParam Integer orderTypeId){
        List<Business> businesses = businessService.listBusinessByOrderTypeId(orderTypeId);
        return businesses.stream().map(business -> modelMapper.map(business, BusinessDTO.class))
                .collect(Collectors.toList());
    }


    @GetMapping("/listBySearch")
    public List<BusinessDTO> listBySearch(@RequestParam String searchContent){
        List<Business> businesses = businessService.listBySearchContent(searchContent);
        return businesses.stream().map(business -> modelMapper.map(business, BusinessDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/listBusinessByStarPrice")
    public List<BusinessDTO> listBusinessByStarPrice(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Business> businesses = businessService.listBusinessByStarPriceAsc(pageable).getContent();
        return businesses.stream().map(business -> modelMapper.map(business, BusinessDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile multipartFile){
        return fileHandler.uploadFile(multipartFile);
    }

}
