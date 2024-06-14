import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';
import ProfilePage from './pages/ProfilePage';
import Header from "./components/Header";
import Footer from "./components/Footer";
import GlobalStyle from "./styles/GlobalStyle";

function App() {
  return (
      <Router>
          <div>
              <GlobalStyle/>
              <Header/>
              <Routes>
                  <Route exact path="/" component={HomePage} />
                  <Route path="/login" component={LoginPage} />
                  <Route path="/register" component={SignUpPage} />
                  <Route path="/profile" component={ProfilePage} />
              </Routes>
              <Footer/>
          </div>
      </Router>
  );
}

export default App;
