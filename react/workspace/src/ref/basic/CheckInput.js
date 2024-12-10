import React, { useRef } from 'react';
import Form from './Form';

const CheckInput = () => {

    const style = {
        display : "flex",
        flexDirection : "column",
        width : "200px"
    }
    
    const inputsRef = useRef([]);
    const formRef = useRef();
    const onClickInputValue = () => {
        for(let input of inputsRef.current){
                if(!input.value){
                    console.log(input.name + " error")
                    return
                }
        }
        formRef.current.submit()
    }
    
    return (
        <div>
            <Form style={style} inputsRef={inputsRef} formRef={formRef} onClickInputValue={onClickInputValue}/>
        </div>
    );
};

export default CheckInput;