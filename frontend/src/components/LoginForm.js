import { useState } from 'react';
import styled from "styled-components";
import {useMutation} from "react-query";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {AUTH_LOGIN} from "../constants/urls";

const Form = styled.form`
  display: flex;
  flex-direction: column;
`;

const FormGroup = styled.div`
  margin-bottom: 1rem;
`;

const Input = styled.input`
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 93%;
  transition: border-color 0.3s;

  &:focus {
    border-color: #000000;
    outline: none;
  }
`;

const Button = styled.button`
  padding: 0.75rem;
  background-color: #000000;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  margin-top: 1rem;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: #646464;
  }
`;

const ErrorMessage = styled.p`
  color: red;
  font-size: 0.875rem;
`;

function LoginForm() {

    const navigation = useNavigate();
    const {mutate, isLoading, error} = useMutation(async (user) => {
        const response = await axios.post(AUTH_LOGIN, user, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
        return response.data;
    },{
        onSuccess: (data) => {
            navigation('/');
        },
        onError: (error) => {
            navigation("/login");
        }
    })

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === "email") setEmail(value);
        else if (name === "password") setPassword(value);
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        const user = {
            email: email,
            password: password
        }
        mutate(user);
    };

    return (
        <Form onSubmit={handleSubmit}>
            <FormGroup>
                <Input
                    placeholder={"이메일을 입력하세요"}
                    type="text"
                    name="email"
                    value={email}
                    onChange={handleChange}
                    required
                />
            </FormGroup>
            <FormGroup>
                <Input
                    placeholder={"비밀번호를 입력하세요"}
                    type="password"
                    name="password"
                    value={password}
                    onChange={handleChange}
                    required
                />
            </FormGroup>
            <Button type="submit">Login</Button>
            {/*{error && <ErrorMessage>{error}</ErrorMessage>}*/}
        </Form>
    );
}

export default LoginForm;
