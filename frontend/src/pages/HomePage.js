import React from 'react';
import styled from 'styled-components';
import BannerCarousel from "../components/BannerCarousel";
import Categories from "../components/Categories";
import bread from "../assets/bread.png";
import cake from "../assets/cake.png";
import cookie from "../assets/cookie.png";
import madeleine from "../assets/madeleine.png";
import macaron from "../assets/macaron.png";
import pudding from "../assets/pudding.png";
import pie from "../assets/pie.png";
import sale from "../assets/sale.png"

const PageContainer = styled.div`
  width: 100%;
  height: calc(100vh - 60px);
  max-width: 480px;
  margin: 0 auto;
`;

const Product = styled.div`
`;

const Banner = styled.div`
  width: 100%;
  height: 28vh;
  margin-bottom: 1rem;
  background-color: black;
`;

const Products = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
`;

const BannerItem = styled.div`
  width: 48%;
  margin-bottom: 1rem;
`;

const HomePage = () => {

    const data = [ // 디저트 종류 뭐있어? 알려줘 애기, 이런 느낌으로?
        {
            imageUrl:cake,
            label:"케이크"
        },
        {
            imageUrl:pie,
            label:"파이&타르트"
        },
        {
            imageUrl:madeleine,
            label:"구움과자"
        },
        {
            imageUrl:cookie,
            label:"쿠키"
        },
        {
            imageUrl: pudding,
            label:"푸딩"
        },
        {
            imageUrl:bread,
            label:"빵"
        },
        {
            imageUrl: macaron,
            label: "마카롱"
        },
        {
            imageUrl: sale,
            label: "세일"
        }
    ];

    return (
        <PageContainer>
            <Banner>
                <BannerCarousel/>
            </Banner>
            <Categories data={data}/>
            <div>회원님을 위한 추천 상품</div>
            <Products>
                <Product>
                    <img src="https://via.placeholder.com/200x250" alt="BannerItem" />
                    <div>17% 9,900원</div>
                </Product>
                <Product>
                    <img src="https://via.placeholder.com/200x250" alt="BannerItem" />
                    <div>10% 8,910원</div>
                </Product>
            </Products>
        </PageContainer>
    );
};

export default HomePage;
