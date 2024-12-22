import { createAction, handleActions } from 'redux-actions'

// 1번 실습
// type 생성
const BIGGER = "font/BIGGER";
// action 생성
export const bigger = createAction(BIGGER);

// 2번 예제
const INPUTBIGGER = "font/INPUTBIGGER";
export const inputBigger = createAction(INPUTBIGGER, (fontSize) => fontSize)

const initialState = {
    fontSize : "1rem"
}

// reducer 제작 끝!
const font = handleActions({
    [BIGGER] : (state, action) => ({fontSize: "3rem"}),
    [INPUTBIGGER] : (state, action) => ({fontSize: action.payload}),
}, initialState)

export default font;