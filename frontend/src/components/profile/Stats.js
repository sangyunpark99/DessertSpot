import React from 'react';
import styled from 'styled-components';

const StatsContainer = styled.div`
  display: flex;
  justify-content: space-around;
  padding: 20px 0;
  border: 1px solid #dedede;
  border-radius: 5px;
  margin: 0px 10px 15px 10px;
`;

const StatItem = styled.div`
  text-align: center;
  font-size: 13px;
  flex: 3.3;
  
  &:not(:last-child) {
    border-right: 1px solid #ddd;
  }
`;

const StatLabel = styled.span`
  display: block;
  color: gray;
  margin-bottom: 5px;
`;

const StatValue = styled.span`
  font-size: 14px;
  font-weight: bold;
`;

const StatsSection = () => {
    return (
        <StatsContainer>
            <StatItem>
                <StatLabel>포인트</StatLabel>
                <StatValue>300원</StatValue>
            </StatItem>
            <StatItem>
                <StatLabel>쿠폰</StatLabel>
                <StatValue>77장</StatValue>
            </StatItem>
            <StatItem>
                <StatLabel>베리</StatLabel>
                <StatValue>0개</StatValue>
            </StatItem>
        </StatsContainer>
    );
};

export default StatsSection;
