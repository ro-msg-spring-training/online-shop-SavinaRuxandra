package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.ProductConverter;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.service.ProductService;

@AllArgsConstructor
@RequestMapping(value = "/api/products")
@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDto productDto) {
        productService.updateProduct(id, productDto);
        return new ResponseEntity<>("Product has updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product has deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Object> readByIdProduct(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productConverter.modelToDto(
                productService.readByIdProduct(id)
        ), HttpStatus.OK
        );
    }

    @RequestMapping(value = "")
    public ResponseEntity<Object> readAllProduct() {
        return new ResponseEntity<>(productService.readAllProduct(), HttpStatus.OK);
    }

}
