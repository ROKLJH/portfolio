import React from 'react';

const User = (props) => {
    const { user } = props;

    return (
        <div>
            {user.name}
            {user.email}
        </div>
    );
};

export default User;