import React from 'react';
import Food from './Food';

const FoodContainer = () => {

    const style = {
        listStyle : "none"
    }
    
    const foods = [
        {
            id: 1,
            name : '샐러드'
        },
        {
            id: 2,
            name : '치킨'
        },
        {
            id: 3,
            name : '피자'
        },
        {
            id: 4,
            name : '스테이크'
        },
    ]

    // li태그를 Food컴포넌트로 변경하기
    const foodList = foods.map((food, i, foods) => <Food key={i} style={style} food={food} />)
    
    return (
        <div>
            {foodList}
        </div>
    );
};

export default FoodContainer;