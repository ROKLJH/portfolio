import React, { useEffect, useState } from 'react';

const SideEffect = () => {

    const [color, setColor] = useState("black")
    const [name, setName] = useState("");

    const onClickToChangeColor = () => {
        setColor("red")
    }

    const onClickToChangeName = () => {
        setName("홍길동")
    }

    // 리랜더링이 될 때마다 실행
    // useEffect(()=> {
    //     console.log("리랜더링 될 때마다 실행하는 콘솔😁")
    // })
    
    // 마운트가 됐을 때 딱 한 번
    // useEffect(()=> {
    //     console.log("마운트가 되었을 때 실행하는 콘솔😁")
    // }, [])
    
    // 해당 상태가 변경 됐을 때 리랜더링
    useEffect(()=> {
        console.log("색상이 변경 됐을 때 실행하는 콘솔😁")
    }, [color])
    
    return (
        <div>
            <h1 style={{color}}>안녕하세요! {name}님</h1>
            <button onClick={onClickToChangeColor}>색상 변경</button>
            <button onClick={onClickToChangeName}>이름 변경</button>
        </div>
    );
};

export default SideEffect;