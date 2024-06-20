import React from 'react';
import styled from 'styled-components';
import {IoIosSearch} from "react-icons/io";
import {PiShoppingCartLight} from "react-icons/pi";

const SearchBarContainer = styled.div`
  width: calc(100% - 24px);
  max-width: 456px; // 내부 여백 padding을 빼준값
  height: 35px;
  padding: 7px 12px;
  position: fixed;
  top: 0;
  z-index: 1;
  background-color: #FFFFFF;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const SearchBarButton = styled.div`
  width: 90%;
  height: 100%;
  background-color: #EEEEEE;
  border-radius: 5px;
  padding: 0px 5px;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const SearchButtonLeftContainer = styled.div`
  display: flex;
  align-items: center;
  font-size: 16px;
  margin-right: 2px;
`;

const SearchButtonText = styled.div`
  margin-left: 5px;
  margin-top: 5px;
`;

const SearchBar = () => {
    return (
        <SearchBarContainer>
            <SearchBarButton>
                <SearchButtonLeftContainer>
                    <IoIosSearch size={18}/>
                    <SearchButtonText>디저트 모두 모여라</SearchButtonText>
                </SearchButtonLeftContainer>
            </SearchBarButton>
                <PiShoppingCartLight size={24}/>
        </SearchBarContainer>
    );
};

export default SearchBar;
