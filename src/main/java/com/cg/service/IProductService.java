package com.cg.service;

import com.cg.model.Description;
import com.cg.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IProductService {
    Product selectProduct(int id);

    boolean existById(int id);

    List<Product> selectAllProducts();

    List<Product> selectAllProductsInclLocked();

    Description selectDescriptionByProductId(int id);
    boolean insertProduct(Product newProduct, Description newDescription);

    boolean updateProduct(Product updatedProduct, Description newDescription);

    void lockProduct(int id);

    void unlockProduct(int id);

    boolean isLocked(int id);

    HttpServletRequest processProduct(HttpServletRequest request);

    HttpServletRequest modifyProduct(HttpServletRequest request);

    HttpServletRequest setAttProductDetail(HttpServletRequest request, Product product);
    HttpServletRequest setAttDescription(HttpServletRequest request, Description description);
}
