import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';
import MyPage from './pages/MyPage';
import BottomNavBar from './components/BottomNavBar';
import GlobalStyle from './styles/GlobalStyle';
import styled from 'styled-components';

const AppContainer = styled.div`
  width: 100%;
  max-width: 480px;
  height: 100vh;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  flex: 1;
  display: flex;
  flex-direction: column;
  position: relative;
  margin: 0 auto;
  overflow: scroll;

  //&::-webkit-scrollbar {
  //  display: none;
  //}
`;



const ContentContainer = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-bottom: 60px; /* Make room for the nav bar */
  width: 100%;
`;

function App() {
    return (
        <Router>
            <GlobalStyle />
            <AppContainer>
                <ContentContainer>
                    <Routes>
                        <Route path="/" element={<HomePage />} />
                        <Route path="/login" element={<LoginPage />} />
                        <Route path="/signUp" element={<SignUpPage />} />
                        <Route path="/profile" element={<MyPage />} />
                    </Routes>
                </ContentContainer>
                <BottomNavBar />
            </AppContainer>
        </Router>
    );
}

export default App;
