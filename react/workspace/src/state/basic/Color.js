import React, { useState } from 'react';

// 10분
// 빨간색 버튼을 누르면 글자색을 빨갛게
// 파란색 버튼을 누르면 글자색을 파랗게
// 핑크색 입력 시 글자의 색깔을 없앤다.

const Color = () => {

    const [result, setResult] = useState("")
    const [color, setColor] = useState("")

    const onResult = (e) => {
        let value = e.target.value;
        if(value === "핑크색"){ setColor("") }
        return setResult(value)
    }
    const colorRed = () => { setColor("red") }
    const colorBlue = () => { setColor("blue") }

    return (
        <div>
            <p style={{color}}>{result}</p>
            <input onChange={onResult} type="text"/>
            <button onClick={colorRed}>빨간색</button>
            <button onClick={colorBlue}>파란색</button>
        </div>
    );
};

export default Color;