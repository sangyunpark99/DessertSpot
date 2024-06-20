import styled from "styled-components";
import Product from "./Product";

const ProductsContainer = styled.div`
  padding-bottom: 60px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
`;

const Products = ({data}) => {
    return (
        <ProductsContainer>
            {data.map((product, index) => (
                <Product key={index} src={product.imageUrl} label={product.label} />
            ))}
        </ProductsContainer>
    );
}

export default Products;