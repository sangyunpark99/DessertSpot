import React from 'react';
import styled from "styled-components";

const ProductContainer = styled.div`
  width: 47.5%;
  height: 40vh;
  margin-bottom: 45px;
`;

const ProductImage = styled.img`
  width: 100%;
  height: 90%;
  object-fit: cover;
  border-radius: 3%;
`

const ProductInfo = styled.div`
  margin-top: 5px;
`;

const ProductDiscount = styled.span`
  color: #f84d4d;
  font-weight: 600;
`;

const ProductName = styled.div`
  margin-top: 3px;
  font-weight: 700;
  font-size: 0.8rem;
`;

const ProductPrice = styled.span`
  font-weight: 700;
  margin-left: 5px;
`

const ProductShopName = styled.div`
  margin-top: 1px;
  font-size: 0.8rem;
`;

const ProductCnt = styled.div`
  margin-top: 10px;
  font-size: 0.7rem;
  color: #9a9a9a;
`;

const Product = React.forwardRef(({ src, name, price, stock}, ref) => {
    return (
        <ProductContainer ref={ref}>
            <ProductImage src={src} />
            <ProductInfo>
                <ProductDiscount>16%</ProductDiscount>
                <ProductPrice>{price}</ProductPrice>
                <ProductShopName>Haeun</ProductShopName>
                <ProductName>{name}</ProductName>
                <ProductCnt>{stock}개 판매중</ProductCnt>
            </ProductInfo>
        </ProductContainer>
    )
});

export default Product;