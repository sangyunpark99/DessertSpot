import React from 'react';
import Profile from '../components/Profile';
import styled from "styled-components";

const PageContainer = styled.div`
  width: 100%;
  height: calc(100vh - 60px);
  max-width: 480px;
  margin: 0 auto;
  //padding: 1rem;
`;

function ProfilePage() {
    return (
        <PageContainer>
            <h1>Profile</h1>
            <Profile />
        </PageContainer>
    );
}

export default ProfilePage;