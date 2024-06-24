import React from 'react';
import SignUpForm from '../components/SignUpForm';
import styled from "styled-components";

const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: calc(100vh - 80px); /* Adjust for bottom nav height */
  width: 100%;
  padding: 20px 10px 0px;
`;

const SignUpLogo = styled.div`
    font-size: 2rem;
    font-weight: 800;
    margin-bottom: 20px;
`;

const SignUpTitle = styled.div`
    font-weight: 700;
    font-size: 1.5rem;
`;

const SignUpBox = styled.div`
  margin-top: 20px;
  height: 100%;
  background: #fff;
  border-radius: 8px;
  width: 90%;
  max-width: 400px;
`;

function SignUpPage() {
    return (
        <PageContainer>
            <SignUpLogo>Dessert Spot</SignUpLogo>
            <SignUpTitle>회원가입</SignUpTitle>
            <SignUpBox>
                <SignUpForm />
            </SignUpBox>
        </PageContainer>
    )
}

export default SignUpPage;