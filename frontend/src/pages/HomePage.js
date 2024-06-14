import React from 'react';
import styled from 'styled-components';

const PageContainer = styled.div`
  width: 100%;
  height: calc(100vh - 60px);
  max-width: 480px;
  margin: 0 auto;
  //padding: 1rem;
`;

const Banner = styled.div`
  width: 100%;
  height: 200px;
  background: url('https://via.placeholder.com/480x200') center/cover no-repeat;
  margin-bottom: 1rem;
`;

const Categories = styled.div`
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  margin-bottom: 1rem;
`;

const Category = styled.div`
  width: 22%;
  text-align: center;
  margin-bottom: 1rem;
`;

const Products = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
`;

const Product = styled.div`
  width: 48%;
  margin-bottom: 1rem;
`;

const HomePage = () => {
    return (
        <PageContainer>
            <Banner />
            <Categories>
                <Category>
                    <img src="https://via.placeholder.com/50" alt="Category" />
                    <div>남자패션</div>
                </Category>
                <Category>
                    <img src="https://via.placeholder.com/50" alt="Category" />
                    <div>의류</div>
                </Category>
                <Category>
                    <img src="https://via.placeholder.com/50" alt="Category" />
                    <div>주얼리</div>
                </Category>
                <Category>
                    <img src="https://via.placeholder.com/50" alt="Category" />
                    <div>패션소품</div>
                </Category>
                <Category>
                    <img src="https://via.placeholder.com/50" alt="Category" />
                    <div>라이프</div>
                </Category>
                <Category>
                    <img src="https://via.placeholder.com/50" alt="Category" />
                    <div>신발</div>
                </Category>
                <Category>
                    <img src="https://via.placeholder.com/50" alt="Category" />
                    <div>디지털/핸드폰</div>
                </Category>
                <Category>
                    <img src="https://via.placeholder.com/50" alt="Category" />
                    <div>가방</div>
                </Category>
            </Categories>
            <div>회원님을 위한 추천 상품</div>
            <Products>
                <Product>
                    <img src="https://via.placeholder.com/200x250" alt="Product" />
                    <div>17% 9,900원</div>
                </Product>
                <Product>
                    <img src="https://via.placeholder.com/200x250" alt="Product" />
                    <div>10% 8,910원</div>
                </Product>
            </Products>
        </PageContainer>
    );
};

export default HomePage;
