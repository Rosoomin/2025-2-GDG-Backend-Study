package com.example.shop.common.message;

public class ErrorMessage {

    // Member 관련 에러 메시지
    public static final String MEMBER_NOT_FOUND = "회원을 찾을 수 없습니다.";
    public static final String MEMBER_ALREADY_EXISTS = "이미 존재하는 로그인 아이디입니다.";

    // DTO
    public static final String LOGIN_ID_NOT_NULL = "로그인 아이디는 필수입니다.";
    public static final String LOGIN_ID_SIZE = "로그인 아이디는 4자 이상 20자 이하입니다.";

    public static final String PASSWORD_NOT_NULL = "비밀번호는 필수입니다.";
    public static final String PASSWORD_SIZE = "비밀번호는 8자 이상 20자 이하입니다.";

    public static final String PHONE_NUMBER_NOT_NULL = "전화번호는 필수입니다.";
    public static final String PHONE_NUMBER_PATTERN = "전화번호 형식은 010-xxxx-xxxx입니다.";

    public static final String ADDRESS_NOT_NULL = "주소는 필수입니다.";
    public static final String ADDRESS_SIZE = "주소는 1자 이상 255자 이하입니다.";

    public static final String PRODUCT_ALREADY_EXISTS = "이미 존재하는 상품명입니다.";
    public static final String PRODUCT_NOT_FOUND = "상품을 찾을 수 없습니다.";

    public static final String PRODUCT_NAME_NOT_NULL = "상품명은 필수입니다.";
    public static final String PRODUCT_NAME_SIZE = "상품명은 1자 이상 50자 이하입니다.";
    public static final String PRODUCT_PRICE_NOT_NULL = "가격은 필수입니다.";
    public static final String PRODUCT_PRICE_MIN = "가격은 0원 이상이어야 합니다.";
    public static final String PRODUCT_STOCK_NOT_NULL = "재고 수량은 필수입니다.";
    public static final String PRODUCT_STOCK_MIN = "재고 수량은 0 이상이어야 합니다.";


}
