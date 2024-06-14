import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { logout } from '../store/slices/authSlice';

function Header() {
    const dispatch = useDispatch();
    const {user: currentUser} = useSelector(state => state.state.auth);

    const handleLogout = () => {
      dispatch(logout());
    };

    return (
        <header>
            <nav>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                    <li>
                        <Link to="/profile">Profile</Link>
                    </li>
                    {currentUser ? (
                        <>
                            <li>
                                <Link to="/profile">{currentUser.username}</Link>
                            </li>
                            <li>
                                <a href="/" onClick={handleLogout}>Logout</a>
                            </li>
                        </>
                    ) : (
                        <>
                            <li>
                                <Link to="/login">Login</Link>
                            </li>
                            <li>
                                <Link to="/register">Register</Link>
                            </li>
                        </>
                    )}
                </ul>
            </nav>
        </header>
    );
}

export default Header;