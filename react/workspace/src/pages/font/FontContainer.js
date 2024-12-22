import React from 'react';
import FontButtonComponent from './FontButtonComponent';
import FontInputComponent from './FontInputComponent';
import FontInputHooks from './FontInputHooks';

const FontContainer = () => {

    // 실습
    // "재미있는 리덕스 수업!"
    // 기본 글자 크기가 1rem
    // 버튼을 눌렀을 때 redux를 이용해서 3rem으로 변경
    // FontButtonComponent로 만들기
    // 50분 실습

    return (
        <div>
            {/* <FontButtonComponent /> */}
            {/* <FontInputComponent /> */}
            <FontInputHooks />
        </div>
    );
};

export default FontContainer;