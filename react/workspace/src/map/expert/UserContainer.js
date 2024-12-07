import React from 'react';
import { useAsync } from 'react-async'
import User from './User';
import Users from './Users';

const UserContainer = () => {

    const users = [
        {
            id : 1,
            name : '한둘셋',
            email : 'handulset@gmail.com'
        },
        {
            id : 2,
            name : '홍길동',
            email : 'hkd1234@gmail.com'
        },
        {
            id : 3,
            name : '신짱구',
            email : 'zzangoo@gmail.com'
        },
        {
            id : 4,
            name : '장보고',
            email : 'jbg4567@gmail.com'
        },
    ]
    // 화면쪽에 컨테이너로만 유저의 이름과 이메일 출력하기
    // 7분
    // 컴포넌트로 분리 난이도1 User로 분리하기
    // 난이도3 Users - User로 분리하기

    const userList = <Users users={users}/>;

    return (
        <div>
            {userList}
        </div>
    );
};

export default UserContainer;