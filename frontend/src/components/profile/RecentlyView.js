import React from 'react';
import styled from 'styled-components';
import { HiOutlineShoppingBag } from "react-icons/hi2";
import { IoIosArrowForward } from "react-icons/io";

const RecentlyViewedContainer = styled.div`
  padding: 15px 0px;
  margin: 0 10px;
`;

const SectionTitle = styled.h3`
  font-size: 18px;
  margin-bottom: 10px;
  display: flex;
`;

const TitleIcon = styled.div`
    margin-right: 10px;
`;

const Title = styled.div`
  display: flex;
  align-items: center;
  flex: 9;
  font-size: 17px;
`;

const RecentlyViewedItems = styled.div`
  height: 100%;
  display: flex;
  overflow-x: auto;
`;

const Item = styled.div`
  width: 150px;
  margin-right: 10px;
`;

const ItemImage = styled.img`
  width: 100%;
  display: block;
  border-radius: 5px;
`;

const ItemDetails = styled.div`
  display: flex;
  text-align: center;
  margin-top: 10px;
`;

const ItemDiscount = styled.span`
  color: red;
  font-weight: 600;
  font-size: 14px;
  margin-right: 4px;
`;

const ItemPrice = styled.span`
  display: block;
  font-size: 14px;
  font-weight: 700;
`;

const ItemName = styled.div`
  font-size: 11px;
  color: #737373;
  margin-top: 3px;
`;

const RecentlyViewedSection = () => {
    const items = [
        {
            img: 'https://dessertspot.s3.ap-northeast-2.amazonaws.com/product1.png',
            discount: '28%',
            price: '14,150',
            name:"달달한 마들렌"
        },
        {
            img: 'https://dessertspot.s3.ap-northeast-2.amazonaws.com/product2.png',
            discount: '50%',
            price: '19,800',
            name: "고소한 요들렌"
        },
        {
            img: 'https://dessertspot.s3.ap-northeast-2.amazonaws.com/product3.png',
            discount: '20%',
            price: '15,900',
            name: "기분좋은 고들렌"
        },
        {
            img: 'https://dessertspot.s3.ap-northeast-2.amazonaws.com/product4.png',
            discount: '67%',
            price: '9,800',
            name: "떫은 비들렌"
        }
    ];

    return (
        <RecentlyViewedContainer>
            <SectionTitle>
                <TitleIcon>
                    <HiOutlineShoppingBag size={22}/>
                </TitleIcon>
                <Title>최근 본 디저트</Title>
                <IoIosArrowForward size={23}/>
            </SectionTitle>
            <RecentlyViewedItems>
                {items.map((item, index) => (
                    <Item key={index}>
                        <ItemImage src={item.img} alt="상품 이미지" />
                        <ItemDetails>
                            <ItemDiscount>{item.discount}</ItemDiscount>
                            <ItemPrice>{item.price}</ItemPrice>
                        </ItemDetails>
                        <ItemName>{item.name}</ItemName>
                    </Item>
                ))}
            </RecentlyViewedItems>
        </RecentlyViewedContainer>
    );
};

export default RecentlyViewedSection;
