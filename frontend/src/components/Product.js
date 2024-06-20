import styled from "styled-components";

const ProductContainer = styled.div`
  width: 47.5%;
  height: 40vh;
  margin-bottom: 30px;
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

const ProductPrice = styled.span`
  font-weight: 700;
  margin-left: 5px;
`

const ProductShopName = styled.div`
  margin-top: 1px;
  font-size: 0.8rem;
`;

const ProductCnt = styled.div`
  margin-top: 2px;
  font-size: 0.7rem;
  color: #9a9a9a;
`;

const Product = ({src, label}) => {
    return (
        <ProductContainer>
            <ProductImage src={src} />
            <ProductInfo>
                <ProductDiscount>16%</ProductDiscount>
                <ProductPrice>23,000</ProductPrice>
                <ProductShopName>Haeun</ProductShopName>
                <ProductCnt>1,342개 구매중</ProductCnt>
            </ProductInfo>
        </ProductContainer>
    )
}

export default Product;