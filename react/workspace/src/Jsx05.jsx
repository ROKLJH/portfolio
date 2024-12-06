// if문을 쓰지 않고 연산자로 출력하기 실습
import React from 'react';
import PassComponent from './PassComponent';
import NotPassComponent from './NotPassComponent';
import LuckyComponent from './LuckyComponent';
import UnLuckyComponent from './UnLuckyComponent';


const Jsx05 = () => {

    const age = 20;
    // 비구조화 할당
    const [pass, notPass, lucky, unLucky] = ['입장 가능', '입장 불가', '당첨', '꽝']

    // 조건식을 변수에 담음
    const isAdult = age >= 19;
    const isEven = age % 2 === 0;

    // 컴포넌트를 제작
    //const passComponent = (<div>{pass}</div>)
    //const notPassComponent = (<div>{notPass}</div>)
    //const luckyComponent = (<div>{lucky}</div>)
    //const unLuckyComponent = (<div>{unLucky}</div>)

    // 삼항 연산자
    const enter = isAdult ? <PassComponent /> : <NotPassComponent />;
    const win = isEven ? <LuckyComponent /> : <UnLuckyComponent />

    // JSX는 if문을 사용할 수 없기 때문에 삼항 연산자를 지원한다.
    // 조건식 ? 참일 때 랜더링할 JSX : 거짓일 때 랜더링할 JSX
    // 조건식 && 참일 때 랜더링할 JSX, 거짓이면 아무것도 출력되지 않음
    // && 연산자 앞의 조건식이 false이면 뒤를 실행하지 않으며, false 라는 값은 랜더링 되지 않음

    return (
        <div>
            당신의 나이는 {age}입니다.
            {enter}
            {win}
        </div>
    );
};

export default Jsx05;