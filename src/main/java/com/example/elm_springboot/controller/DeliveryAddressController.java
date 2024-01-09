package com.example.elm_springboot.controller;

import com.example.elm_springboot.dto.DeliveryAddressDTO;
import com.example.elm_springboot.entity.DeliveryAddress;
import com.example.elm_springboot.service.IDeliveryAddressService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/DeliveryAddressController")
public class DeliveryAddressController {
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private IDeliveryAddressService deliveryAddressService;

    @GetMapping("/listDeliveryAddressByUserId")
    public List<DeliveryAddressDTO> listDeliveryAddressByUserId(@RequestParam Long userId){
        List<DeliveryAddress> deliveryAddresses = deliveryAddressService.listDeliveryAddressByUserId(userId);
        return deliveryAddresses.stream()
                .map(deliveryAddress -> modelMapper.map(deliveryAddress,DeliveryAddressDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/getDeliveryAddressById")
    public DeliveryAddressDTO getDeliveryAddressById(@RequestParam Long daId){
        DeliveryAddress deliveryAddress = deliveryAddressService.getDeliveryAddressById(daId);
        return modelMapper.map(deliveryAddress,DeliveryAddressDTO.class);
    }

    @PostMapping("/saveDeliveryAddress")
    public DeliveryAddressDTO saveDeliveryAddress(@RequestBody DeliveryAddressDTO deliveryAddressDTO){
        DeliveryAddress deliveryAddress = modelMapper.map(deliveryAddressDTO, DeliveryAddress.class);
        DeliveryAddress deliveryAddress1 = deliveryAddressService.saveDeliveryAddress(deliveryAddress);
        return modelMapper.map(deliveryAddress1,DeliveryAddressDTO.class);
    }

    @PostMapping("/updateDeliveryAddress")
    public DeliveryAddressDTO updateDeliveryAddress(@RequestBody DeliveryAddressDTO deliveryAddressDTO){
        DeliveryAddress deliveryAddress = modelMapper.map(deliveryAddressDTO, DeliveryAddress.class);
        deliveryAddressService.updateDeliveryAddress(deliveryAddress);
        return deliveryAddressDTO;
    }

    @DeleteMapping("/removeDeliveryAddress")
    public Integer removeDeliveryAddress(@RequestParam Long daId){
        return deliveryAddressService.removeDeliveryAddress(daId);
    }
}
