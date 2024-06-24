import LoginForm from "../components/LoginForm";
import styled from "styled-components";
import {Link} from "react-router-dom";

const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: calc(100vh - 80px); /* Adjust for bottom nav height */
  width: 100%;
  padding: 20px 10px 0px;
`;

const LoginLogo = styled.div`
    font-size: 2rem;
    font-weight: 800;
    margin-bottom: 20px;
`;

const LoginTitle = styled.div`
    font-weight: 700;
    font-size: 1.5rem;
    margin-bottom: 20px;
`;

const LoginBox = styled.div`
  background: #fff;
  border-radius: 8px;
  width: 100%;
  max-width: 400px;
  margin-bottom: 20px;
`;

const FindAccountBox = styled.div`
    width: 200px;
    display: flex;
    justify-content: space-between;
`;

const FindAccountLink = styled(Link)`
  text-decoration: none;
  color: black;
`;


function LoginPage() {
    return (
        <PageContainer>
            <LoginLogo>Dessert Spot</LoginLogo>
            <LoginTitle>로그인</LoginTitle>
            <LoginBox>
                <LoginForm />
            </LoginBox>
            <FindAccountBox>
                <FindAccountLink to = "/" style={{textDecoration: "none"}}>아이디 찾기</FindAccountLink>
                <div> | </div>
                <FindAccountLink to = "/" style={{textDecoration: "none"}}>비밀번호 찾기</FindAccountLink>
            </FindAccountBox>
        </PageContainer>
    );
}

export default LoginPage;