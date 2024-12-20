import React from 'react';
import BasicButton from '../components/button/BasicButton';

const BasicContainer = () => {
    return (
        <div>
            <BasicButton 
                size={"large"} 
                shape={"round"}
                variant={"primary"}
                // onClick={}
            >제가 만든 베이직 버튼이에요😎</BasicButton>
        </div>
    );
};

export default BasicContainer;