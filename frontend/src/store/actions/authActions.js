import AuthService from "../services/AuthService";

export const SIGNUP_SUCCESS = 'SIGNUP_SUCCESS';
export const SIGNUP_FAIL = 'SIGNUP_FAIL';
export const LOGIN_SUCCESS = "LOGIN_SUCCESS";
export const LOGIN_FAIL = "LOGIN_FAIL";
export const LOGOUT = "LOGOUT";

export const signup = (username, email, password) => dispatch => {
    return AuthService.signup(username, email, password).then(
        response => {
            dispatch({
                type:SIGNUP_SUCCESS
            });

            return Promise.resolve();
        },
        error => {
            dispatch({
                type:SIGNUP_FAIL
            });

            return Promise.reject();
        }
    )
}

export const login = (username, password) => dispatch => {
    return AuthService.login(username, password).then(
        data => {
            dispatch({
                type: LOGIN_SUCCESS,
                payload: { user: data }
            });
            return Promise.resolve();
        },
        error => {
            dispatch({
                type: LOGIN_FAIL
            });
            return Promise.reject();
        }
    );
};

export const logout = () => dispatch => {
    AuthService.logout();
    dispatch({
        type: LOGOUT
    });
};
