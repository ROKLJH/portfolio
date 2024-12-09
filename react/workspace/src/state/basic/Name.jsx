import React, { useState } from 'react';

const Name = () => {
    const [result, setResult] = useState("초기값")
    const viewResult = (e) => {
        // console.log(e)
        setResult(e.target.value)
    }
    return (
        <div>
            <p>{result}</p>
            <input onChange={viewResult}/>
        </div>
    );
};

export default Name;