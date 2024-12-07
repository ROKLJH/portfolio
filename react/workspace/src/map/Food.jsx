import React from 'react';

const Food = ({food}) => {
    const {id, name} = food;
    return (
        <div>
            {id}, {name}
        </div>
    );
};

export default Food;