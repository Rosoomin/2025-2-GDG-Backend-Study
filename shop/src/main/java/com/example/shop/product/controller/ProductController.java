package com.example.shop.product.controller;

import com.example.shop.product.entity.Product;
import com.example.shop.product.service.ProductService;
import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "상품 관리", description = "상품 CRUD API")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "상품 생성", description = "새로운 상품을 등록합니다.")
    @ApiResponse(responseCode = "201", description = "상품 생성 완료")
    @ApiResponse(responseCode = "400", description = "유효하지 않은 요청",
            content = @Content(schema = @Schema()))
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductCreateRequest request) {
        Long productId = productService.createProduct(request);
        return ResponseEntity.created(URI.create("/products/" + productId)).build();
    }

    @GetMapping
    @Operation(summary = "전체 상품 조회")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    @Operation(summary = "상품 단건 조회")
    @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음")
    public ResponseEntity<Object> getProduct(@PathVariable Long productId) {
        Object product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{productId}")
    @Operation(summary = "상품 수정", description = "상품 정보를 수정합니다.")
    @ApiResponse(responseCode = "400", description = "유효하지 않은 요청")
    @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long productId,
            @RequestBody @Valid ProductUpdateRequest request) {
        productService.updateProduct(productId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "상품 삭제")
    @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
