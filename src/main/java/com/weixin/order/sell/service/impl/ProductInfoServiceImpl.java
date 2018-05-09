package com.weixin.order.sell.service.impl;

import com.weixin.order.sell.dataobject.ProductInfo;
import com.weixin.order.sell.dto.CartDTO;
import com.weixin.order.sell.enums.ProductInfoStatusEnum;
import com.weixin.order.sell.enums.ResultEnum;
import com.weixin.order.sell.exception.SellException;
import com.weixin.order.sell.repository.ProductInfoRepository;
import com.weixin.order.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   商品service实现
 * @Date : 下午2:32 2018/5/8
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductInfoStatusEnum.NOMAL.getId());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            int result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NULL);
            }
            if (productInfo.getProductStock() < cartDTO.getProductQuantity()) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_NOT_ENOUGH);
            }
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
}
