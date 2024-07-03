import React from 'react';
import styled from 'styled-components';

const ProfileContainer = styled.div`
  display: flex;
  align-items: center;
  padding-bottom: 10px;
  margin-top: 40px;
`;

const ProfileInfo = styled.div`
  display: flex;
  align-items: center;
  padding: 8px;
`;

const ProfilePic = styled.div`
  width: 60px;
  height: 60px;
  background-color: #ddd;
  border-radius: 50%;
  margin-right: 20px;
`;

const ProfileDetails = styled.div`
  display: flex;
  flex-direction: column;
`;

const ProfileId = styled.h2`
  font-size: 24px;
  margin: 0;
`;

const ProfileName = styled.div`
  font-size: 14px;
  text-decoration: none;
  margin-top: 8px;
`;

const ProfileSection = () => {
    return (
        <ProfileContainer>
            <ProfileInfo>
                <ProfilePic />
                <ProfileDetails>
                    <ProfileId>psy_99</ProfileId>
                    <ProfileName>박상윤</ProfileName>
                </ProfileDetails>
            </ProfileInfo>
        </ProfileContainer>
    );
};

export default ProfileSection;
