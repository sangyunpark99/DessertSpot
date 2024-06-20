import React from 'react';
import styled from 'styled-components';
import BannerCarousel from "../components/BannerCarousel";
import Categories from "../components/Categories";
import categories from "../data/categories";
import products from "../data/products";
import Products from "../components/Products";


const PageContainer = styled.div`
  width: 100%;
  height: calc(100vh - 60px);
  max-width: 480px;
  margin: 0 auto;
`;

const Banner = styled.div`
  width: 100%;
  height: 28vh;
  margin-bottom: 1rem;
  background-color: black;
`;

const ProductLabel = styled.div`
  height: 7%;
  display: flex;
  justify-content: start;
  align-items: center;
  padding-left: 0.5rem;
  font-size: 1.2rem;
  font-weight: 700;
`

const HomePage = () => {

    return (
        <PageContainer>
            <Banner>
                <BannerCarousel/>
            </Banner>
            <Categories data={categories}/>
            <ProductLabel>
                <div>박상윤님을 위한 디저트</div>
            </ProductLabel>
            <Products data={products}/>
        </PageContainer>
    );
};

export default HomePage;
