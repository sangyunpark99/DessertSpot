import React from 'react';
import styled from 'styled-components';
import {Link, useNavigate} from 'react-router-dom';

const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: calc(100vh - 60px);
`;

const Logo = styled.img`
  width: 100px;
  margin-bottom: 1rem;
`;

const Title = styled.h1`
  font-size: 1.5rem;
  margin-bottom: 0.5rem;
  text-align: center;
`;

const SubTitle = styled.h2`
  font-size: 1rem;
  margin-bottom: 1rem;
  text-align: center;
`;

const ShippingImage = styled.img`
  width: 200px;
  margin-bottom: 1rem;
`;

const SignUpButton = styled.button`
  width: 100%;
  max-width: 300px;
  background-color: #C29F6D;
  color: #3c1e1e;
  border: none;
  border-radius: 4px;
  padding: 1rem;
  font-size: 1rem;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1rem;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: #E6B36D;
  }

  svg {
    margin-right: 0.5rem;
  }
`;

const OtherOptions = styled.div`
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-bottom: 1rem;
`;

const Option = styled(Link)`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
  background-color: #fff;
  border-radius: 50%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  font-size: 1.5rem;
  color: #333;
  text-decoration: none;
  transition: background-color 0.3s;

  &:hover {
    background-color: #f0f0f0;
  }
`;

const EmailLink = styled(Link)`
  font-size: 0.875rem;
  color: #333;
  text-decoration: none;
  margin-top: 1rem;

  &:hover {
    text-decoration: underline;
  }
`;

const ProfilePage = () => {

    const navigate = useNavigate();

    const handleSignUpButtonClick = () => {
        navigate("/signup");
    }

    return (
        <PageContainer>
            <Title>DessertSpot</Title>
            <SubTitle>디저트에 빠져봅시다!</SubTitle>
            <ShippingImage src={"https://files.oaiusercontent.com/file-XEjqPJkIHa4UwiLLzxFbaEQF?se=2024-06-14T04%3A08%3A00Z&sp=r&sv=2023-11-03&sr=b&rscc=max-age%3D31536000%2C%20immutable&rscd=attachment%3B%20filename%3D81eac7e0-0acd-4921-9890-e3ef0ec8bc4d.webp&sig=T%2BCD7aGHGyAdIUHjtnKPcqs1kOJnpTFR8MF9j8vZenE%3D"} alt="Free Shipping" />
            <SignUpButton onClick={handleSignUpButtonClick}>
                회원 가입
            </SignUpButton>
            <EmailLink to="/login">이메일로 로그인하기</EmailLink>
        </PageContainer>
    );
};

export default ProfilePage;
