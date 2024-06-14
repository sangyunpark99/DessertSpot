import axios from 'axios';

const URL = "http://localhost:8080/api/auth";

// 기본 회원 가입
const signup = (username, email, password) => {
    return axios.post(URL + 'signup', {
       username,
       email,
       password
    });
}

const login = (username, password) => {
    return axios.post(URL + 'login', {
        username,
        password
    }).then(response => {
        if(response.data) {
            localStorage.setItem('user', JSON.stringify(response.data.user));
        }

        return response.data;
    })
}

const logout = () => {
    localStorage.removeItem('user');
}

const getCurrentUser = () => {
    return JSON.stringify(localStorage.getItem('user'));
}

export default {
    signup,
    login,
    logout,
    getCurrentUser
}