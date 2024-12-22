import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { bigger } from '../../redux/modules/font';

const FontButtonComponent = () => {
    const fontSize = useSelector((state) => state.fontSize );
    // const fontSize = "1rem"
    const dispatch = useDispatch();

    return (
        <div>
            <p style={{fontSize: fontSize}}>재미있는 리덕스 수업!😏</p>
            <button onClick={()=>{dispatch(bigger())}}>커지는 버튼!!</button>
        </div>
    );
};

export default FontButtonComponent;