package TecnoTienda.tienda.service.ServiceImp;

import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.dto.ProductDTO;
import TecnoTienda.tienda.dto.ProductPaginationDTO;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.exceptions.ProductNotFoundException;
import TecnoTienda.tienda.mappers.ProductMapper;
import TecnoTienda.tienda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    IProductDao productDao;
    @Autowired
    ProductMapper productMapper;

    @Override
    public ProductDTO addProduct(ProductDTO productDto) {
        try {
            Product product = productMapper.productDtoToProduct(productDto);
            return productMapper.productToProductDto(productDao.save(product));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductPaginationDTO findByCategory(String category, int page) {

        Pageable pageable = PageRequest.of(page, 10);

        //       crea el listado de productos paginable 
        ProductPaginationDTO listProducts = new ProductPaginationDTO();

//        se setean los datos devueltos por la bd y se modela un dto
        listProducts.setPage(page);
        listProducts.setProductos(productMapper.productListToProductDtoList(productDao.findByCategory(category, pageable).getContent()));
        listProducts.setTotal(productDao.findByCategory(category, pageable).getTotalPages());
        return listProducts;
    }

    @Override
    public ProductPaginationDTO getAllProducts(int page) {

        // objeto pagina
        Pageable pageable = PageRequest.of(page, 10);

        // crea el listado de productos paginable
        ProductPaginationDTO listProducts = new ProductPaginationDTO();

        // se obtienen los datos de la BD
        Page<Product> productsPage = productDao.findAll(pageable);

        // se mapean los productos a ProductDTO
        List<ProductDTO> productDTOs = productMapper.productListToProductDtoList(productsPage.getContent());

        // se setean los datos en el ProductPaginationDTO
        listProducts.setPage(page);
        listProducts.setProductos(productMapper.productListToProductDtoList(productDao.findAllPage(pageable).getContent()));
        listProducts.setTotal(productDao.findAllPage(pageable).getTotalPages());
        return listProducts;
    }
    @Override
    public void setStockById(int id, int stock){
        productDao.setStockById(id,stock);
    }
    @Override
    public ProductDTO findById(int id) {
        return productMapper.productToProductDto(productDao.findById(id).get());
    }

    @Override
    public void softDeleteProductById(int id) {
        productDao.softDeleteProductById(id);

    }

    @Override
    public void setActiveProductById(int id) {
        productDao.setActiveProductById(id);
    }

    @Override
    public void addBulkProducts(List<ProductDTO> productsDto) {
        productDao.saveAll(productMapper.productDtoListToProductList(productsDto));
    }

    @Override
    public ProductPaginationDTO getProductByQuery(String q, int page) {

        Pageable pageable = PageRequest.of(page, 10);

        //       crea el listado de productos paginable 
        ProductPaginationDTO listProducts = new ProductPaginationDTO();

//        se setean los datos devueltos por la bd y se modela un dto
        listProducts.setPage(page);
        listProducts.setProductos(productMapper.productListToProductDtoList(productDao.findProductsBySearchQuery(q, pageable).getContent()));
        listProducts.setTotal(productDao.findProductsBySearchQuery(q, pageable).getTotalPages());
        return listProducts;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO updatedProductDto) {



            // Guardar el producto actualizado
            Product product = productMapper.productDtoToProduct(updatedProductDto);
            product.setId(updatedProductDto.getId());
            Product prod = productDao.save(product);
            System.out.println("prod = " + prod);
            return productMapper.productToProductDto(prod);

    }

    private static Product getProduct(ProductDTO updatedProductDto, Optional<Product> optionalProduct) {
        Product existingProduct = optionalProduct.get();

        // Actualizar los campos del producto existente con los nuevos datos
        existingProduct.setName(updatedProductDto.getName());
        existingProduct.setBrand(updatedProductDto.getBrand());
        existingProduct.setDescription(updatedProductDto.getDescription());
        existingProduct.setCategory(updatedProductDto.getCategory());
        existingProduct.setPrice(updatedProductDto.getPrice());
        existingProduct.setImageUrl(updatedProductDto.getImageUrl());
        existingProduct.setStock(updatedProductDto.getStock());
        return existingProduct;
    }
}
