package TecnoTienda.tienda.mappers;


import TecnoTienda.tienda.dto.ProductDTO;
import TecnoTienda.tienda.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductMapper {

    public Product productDtoToProduct(ProductDTO productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setBrand(productDto.getBrand());
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }

    public ProductDTO productToProductDto(Product product) {
        ProductDTO productDto = new ProductDTO();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setBrand(product.getBrand());
        productDto.setImageUrl(product.getImageUrl());
        return productDto;
    }
    public List<ProductDTO> productListToProductDtoList(List<Product> listProduct){
        List<ProductDTO> listProductDto = new ArrayList<>();
        for(Product p : listProduct){
            listProductDto.add(this.productToProductDto(p));
        }
        return listProductDto;
    }
    public List<Product> productDtoListToProductList(List<ProductDTO> listProductDto){
        List<Product> listProduct = new ArrayList<>();
        for(ProductDTO p : listProductDto){
            listProduct.add(this.productDtoToProduct(p));
        }
        return listProduct;
    }


}