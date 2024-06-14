import LoginForm from "../components/LoginForm";
import styled from "styled-components";

const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: calc(100vh - 60px); /* Adjust for bottom nav height */
  background-color: #f9f9f9;
  //padding-bottom: 60px; /* Make room for the bottom nav */
  width: 100%;
`;

const LoginBox = styled.div`
  background: #fff;
  padding: 2rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  width: 100%;
  max-width: 400px;
`;


function LoginPage() {
    return (
        <PageContainer>
            <LoginBox>
                <h1>Login</h1>
                <LoginForm />
            </LoginBox>
        </PageContainer>
    );
}

export default LoginPage;