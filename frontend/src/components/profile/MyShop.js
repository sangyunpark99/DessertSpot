import styled from 'styled-components';
import { MdOutlineStorefront } from "react-icons/md";
import {IoIosArrowForward} from "react-icons/io";
import Brand from "../../assets/shop/brand_logo.jpg";
import React from "react";

const MyShopContainer = styled.div`
  height: 100%;
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

const ShopInfo = styled.div`
  height: 100%;
`;

const ShopInfoTitle = styled.div`
  margin-bottom: 10px;
  display: flex;
  align-items: center;
`;

const ShopItems = styled.div`
  height: 100%;
  display: flex;
  overflow-x: auto;
`;

const Item = styled.div`
  width: 25%;
  height: 100%;
  margin-right: 10px;
  height: 100%;
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

const ShopProfilePic = styled.img`
  width: 60px;
  height: 60px;
  background-color: #ddd;
  border: 0.5px solid darkgray;
  border-radius: 50%;
  margin-right: 20px;
`;

const ShopProfileNameContainer = styled.div`
`;

const ShopHashTags = styled.div`
  display: flex;
`;

const ShopHashTag = styled.div`
  font-size: 12px;
  margin-right: 3px;
  color: dimgrey;
`;

const ShopName = styled.div`
  font-size: 17px;
  text-decoration: none;
  margin-top: 8px;
  margin-bottom: 5px;
`;

const MyShop = () => {

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
        },
    ];

    const hashTags = [
      "#마들렌", "#심플재료", "#달짝지근"
    ];

    return (
        <MyShopContainer>
            <SectionTitle>
                <TitleIcon>
                    <MdOutlineStorefront size={22}/>
                </TitleIcon>
                <Title>나의 상점</Title>
                <IoIosArrowForward size={23}/>
            </SectionTitle>
            <ShopInfo>
                <ShopInfoTitle>
                    <ShopProfilePic src={Brand}/>
                    <ShopProfileNameContainer>
                        <ShopName>TasteButter</ShopName>
                        <ShopHashTags>
                            {hashTags.map((hashTag, index) => (
                                <ShopHashTag>
                                    {hashTag}
                                </ShopHashTag>
                            ))}
                        </ShopHashTags>
                    </ShopProfileNameContainer>
                </ShopInfoTitle>
                <ShopItems>
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
                </ShopItems>
            </ShopInfo>
        </MyShopContainer>
    )
}

export default MyShop;