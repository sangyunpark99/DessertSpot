import React from 'react';
import SignUpForm from '../components/SignUpForm';
import styled from "styled-components";

const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: calc(100vh - 60px); /* Adjust for bottom nav height */
  background-color: #f9f9f9;
  width: 100%;
`;

const SignUpBox = styled.div`
  background: #fff;
  padding: 2rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  width: 100%;
  max-width: 400px;
`;

function SignUpPage() {
    return (
        <PageContainer>
            <SignUpBox>
                <h1>SignUp</h1>
                <SignUpForm />
            </SignUpBox>
        </PageContainer>
    )
}

export default SignUpPage;