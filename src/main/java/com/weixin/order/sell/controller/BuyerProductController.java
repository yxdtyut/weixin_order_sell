package com.weixin.order.sell.controller;

import com.weixin.order.sell.Vo.CategoryVo;
import com.weixin.order.sell.Vo.ProductInfoVo;
import com.weixin.order.sell.Vo.ResultVo;
import com.weixin.order.sell.dataobject.ProductCategory;
import com.weixin.order.sell.dataobject.ProductInfo;
import com.weixin.order.sell.service.ProductCategoryService;
import com.weixin.order.sell.service.ProductInfoService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午3:05 2018/5/8
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public ResultVo productInfoList() {

        /**查询所有的上架商品.*/
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        /**查询所有的商品类目.*/
        List<Integer> categoryTypeList =  productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> categoryList = categoryService.findByCategoryTypeList(categoryTypeList);

                /**组装报文数据.*/
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for(ProductCategory productCategory: categoryList) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(productCategory, categoryVo);
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            categoryVo.setProductInfoVoList(productInfoVoList);
            categoryVoList.add(categoryVo);
        }
        return ResultVo.success(categoryVoList);
    }
}
