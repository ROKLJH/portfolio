import React from 'react';
import User from './User';

const Users = ({users}) => {
    // console.log(props)

    return (
        <>
            {users.map((user) => <User user={user}/>)}
        </>
    );
};

export default Users;