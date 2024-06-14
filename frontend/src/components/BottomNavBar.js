import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import styled from 'styled-components';
import { Home, List, Search, User } from 'react-feather';

const NavBar = styled.nav`
  width: 100%;
  height: 60px;
  background-color: #fff;
  box-shadow: 0 -2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: space-around;
  align-items: center;
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 1000;
`;

const NavItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  color: ${props => (props.active ? '#ff0000' : '#333')};

  a {
    color: inherit;
    text-decoration: none;
    display: flex;
    flex-direction: column;
    align-items: center;
    font-size: 0.75rem;
    margin-top: 0.25rem;
  }

  svg {
    width: 24px;
    height: 24px;
    margin-bottom: 0.25rem;
  }
`;


const BottomNavBar = () => {
    const location = useLocation();

    return (
        <NavBar>
            <NavItem active={location.pathname === '/'}>
                <Link to="/">
                    <Home />
                    <div>홈</div>
                </Link>
            </NavItem>
            <NavItem active={location.pathname === '/signup'}>
                <Link to="/signup">
                    <List />
                    <div>전체보기</div>
                </Link>
            </NavItem>
            <NavItem active={location.pathname === '/login'}>
                <Link to="/login">
                    <Search />
                    <div>검색</div>
                </Link>
            </NavItem>
            <NavItem active={location.pathname === '/profile'}>
                <Link to="/profile">
                    <User />
                    <div>마이페이지</div>
                </Link>
            </NavItem>
        </NavBar>
    );
};

export default BottomNavBar;
