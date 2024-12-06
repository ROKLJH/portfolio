import React from 'react';

const Component3 = ({users}) => {
//    const { users } = props;
   const { name, age, address, phone } = users;

    return (
        <ul>
            <l1>{name}</l1>
            <l1>{age}</l1>
            <l1>{address}</l1>
            <l1>{phone}</l1>
        </ul>
    );
};

export default Component3;