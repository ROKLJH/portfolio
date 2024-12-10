import React, { useRef, useState } from 'react';

// 사용자가 입력한 이름을 p태그에 실시간으로 작성되도록 구현
// 버튼을 클릭하면 이름 뒤에 "님"이 붙도록 구현
// 입력 후 p태그에 반영

const Name = () => {

    const [result, setResult] = useState();
    const inputRef = useRef();
    const nameRef = useRef();

    const onClickToPutName = () => {
        setResult(inputRef.current.value + "님")
        // setResult(nameRef.current.innerText + "님")
    };

    const onChangeSetName = (e) => {
        setResult(e.target.value)
    };

    return (
        <>
            <input ref={inputRef} type="text" onChange={onChangeSetName} value={result} />
            <p ref={nameRef}>{result}</p>
            <button onClick={onClickToPutName}>완료 버튼</button>
        </>
    );
};

export default Name;