import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { login } from '../store/slices/authSlice';
import styled from "styled-components";

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
    border-color: #007bff;
    outline: none;
  }
`;

const Button = styled.button`
  padding: 0.75rem;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  margin-top: 1rem;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: #0056b3;
  }
`;

const ErrorMessage = styled.p`
  color: red;
  font-size: 0.875rem;
`;

function LoginForm() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();
    const { error } = useSelector((state) => state.auth);

    const handleSubmit = (e) => {
        e.preventDefault();
        dispatch(login({ username, password }));
    };

    return (
        <Form onSubmit={handleSubmit}>
            <FormGroup>
                <Label>Username</Label>
                <Input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
            </FormGroup>
            <FormGroup>
                <Label>Password</Label>
                <Input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
            </FormGroup>
            <Button type="submit">Login</Button>
            {error && <ErrorMessage>{error}</ErrorMessage>}
        </Form>
    );
}

export default LoginForm;
