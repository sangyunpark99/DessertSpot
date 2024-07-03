import React from 'react';
import styled from 'styled-components';
import {useNavigate} from 'react-router-dom';
import Profile from "../components/profile/Profile";
import MyShop from "../components/profile/MyShop";
import Stats from "../components/profile/Stats";
import OrderReview from "../components/profile/OrderReview";
import RecentlyView from "../components/profile/RecentlyView";
import {PiShoppingCartLight} from "react-icons/pi";

const PageContainer = styled.div`
  width: 100%;
  max-width: 480px;
  display: flex;
  flex-direction: column;
  min-height: calc(100vh - 60px);
  overflow: hidden;
  position: relative;
`;

const TitleContainer = styled.div`
  max-width: 480px;
  position: fixed;
  top: 0;
  left: 1;
  width: 100%;
  background-color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 40px;
`;

const Title = styled.div`
  font-size: 20px;
  font-weight: 700;
  text-align: center;
  flex: 10;
`;

const ShoppingCart = styled.div`
  position: absolute;
  right:0;
  padding: 5px;
`;

const ProfilePage = () => {

    const navigate = useNavigate();

    return (
        <PageContainer>
            <TitleContainer>
                <Title>마이페이지</Title>
                <ShoppingCart>
                    <PiShoppingCartLight size={24}/>
                </ShoppingCart>
            </TitleContainer>
            <Profile />
            <Stats/>
            <OrderReview/>
            <RecentlyView/>
            <MyShop/>
        </PageContainer>
    );
};

export default ProfilePage;
