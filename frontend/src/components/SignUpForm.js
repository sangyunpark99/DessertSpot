import React, { useState } from 'react';
import styled from "styled-components";
import {useMutation} from "react-query";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {AUTH_SIGNUP} from "../constants/urls";

const Form = styled.form`
  display: flex;
  flex-direction: column;
`;

const FormGroup = styled.div`
  margin-bottom: 1rem;
`;

const Label = styled.label`
  margin-bottom: 0.5rem;
  font-weight: bold;
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

function RegisterForm() {

    const navigation = useNavigate();
    const {mutate, isLoading, error} = useMutation(async (newUser) => {
        const response = await axios.post(AUTH_SIGNUP, newUser, {
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
            navigation('/signup')
        }

    })

    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === "email") setEmail(value);
        else if (name === "username") setUsername(value);
        else if (name === "password") setPassword(value);
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        const newUser = {
            username: username,
            email: email,
            password: password
        }
        mutate(newUser);
    };

    return (
        <Form onSubmit={handleSubmit}>
            <FormGroup>
                <Input
                    placeholder={"이름을 입력하세요"}
                    type="text"
                    name="username"
                    value={username}
                    onChange={handleChange}
                    required
                />
            </FormGroup>
            <FormGroup>
                <Input
                    placeholder={"이메일을 입력하세요"}
                    type="email"
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
            <Button type="submit">회원가입</Button>
            {error && <ErrorMessage>{error.message
            }</ErrorMessage>}
        </Form>
    );
}

export default RegisterForm;
