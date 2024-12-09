import React,{ useState }  from 'react';

const Count = () => {

    // 변수, 상태 변화를 위한 setter 메서드 = useState('초기값')
    const [number, setNumber] = useState(0)
    const increase = () => {
        setNumber(number + 1)
    }
    // 감소 만들기
    const decrease = () => {
        setNumber(number - 1)
    }

    return (
        <div>
            <button onClick={decrease}>감소</button>
            {number}
            <button onClick={increase}>증가</button>
        </div>
    );
};

export default Count;