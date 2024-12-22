import React, { useState } from 'react';

// hook함수 : 사용자 지정 함수
// initialValue : 초기값이 들어온다.
const useInput = (initialValue) => {
    const [input, setInput] = useState(initialValue)

    const onChangeInput = (e) => {
        setInput(e.target.value)
    } 

    return [input, onChangeInput];
};

export default useInput;