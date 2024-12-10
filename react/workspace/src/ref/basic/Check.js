import React, { useRef } from 'react';

const Check = () => {

    const inputRef = useRef([]);
    const formRef = useRef();
    const onClickToCheck = () => {
        for(let input of inputRef.current){
            if(!input.value){
                console.log(input.name + "error")
                return
            }
        }
        formRef.current.submit()
    }

    return (
        <div>
            <form ref={formRef}>
                <div>
                    <span>아이디</span><input name="id" ref={(element)=>{ inputRef.current[0] = element }}/>
                </div>
                <div>
                    <span>비밀번호</span><input name="password" ref={(element)=>{ inputRef.current[1] = element }}/>
                </div>
                <button onClick={onClickToCheck} type="button">전송</button>
            </form>
        </div>
    );
};

export default Check;