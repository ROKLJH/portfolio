import React from 'react';
import S from '../style';

const StyledComponent01 = () => {
    return (
        <div>
            <S.Styled color={"blue"}>
                스타일된 컴포넌트입니다.😎
                <input />
                <button><p>입력</p></button>
            </S.Styled>
            {/* styled input을 만들어서 background 컬러를 props로 pink를 받기 5분 */}
            
            <ul>
                <li>글로벌 스타일 잘 들어갔어요?</li>
            </ul>
            <S.Button>스타일된 버튼입니다.😁</S.Button>
        </div>
    );
};

export default StyledComponent01;