import React from 'react';
import { useSelector } from 'react-redux';

function Profile() {
    const { user } = useSelector(state => state.auth);

    return (
        <div>
            <h1>Profile</h1>
            {user && (
                <div>
                    <p><strong>Username:</strong> {user.username}</p>
                    <p><strong>Email:</strong> {user.email}</p>
                </div>
            )}
        </div>
    );
}

export default Profile;
