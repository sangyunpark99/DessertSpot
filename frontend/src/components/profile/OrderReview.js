import React from 'react';
import styled from 'styled-components';

const OrderReviewContainer = styled.div`
  display: flex;
  justify-content: space-around;
  padding: 20px 0;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin: 0px 10px;
`;

const OrderReviewItem = styled.div`
  text-align: center;
  font-size: 13px;
  flex: 3.3;

  &:not(:last-child) {
    border-right: 1px solid #ddd;
  }
`;

const OrderReviewLabel = styled.span`
  display: block;
  color: gray;
  margin-bottom: 5px;
`;

const OrderReviewValue = styled.span`
  font-size: 16px;
  font-weight: bold;
  color: #cb4e4e;
`;

const OrderReviewSection = () => {
    return (
        <OrderReviewContainer>
            <OrderReviewItem>
                <OrderReviewLabel>주문내역</OrderReviewLabel>
                <OrderReviewValue>0</OrderReviewValue>
            </OrderReviewItem>
            <OrderReviewItem>
                <OrderReviewLabel>나의 리뷰</OrderReviewLabel>
                <OrderReviewValue>0</OrderReviewValue>
            </OrderReviewItem>
            <OrderReviewItem>
                <OrderReviewLabel>문의</OrderReviewLabel>
                <OrderReviewValue>0</OrderReviewValue>
            </OrderReviewItem>
        </OrderReviewContainer>
    );
};

export default OrderReviewSection;
