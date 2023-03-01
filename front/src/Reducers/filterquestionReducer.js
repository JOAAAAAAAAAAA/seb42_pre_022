import { createSlice } from "@reduxjs/toolkit";

let initialState = {
  newest: true,
  highestscore: false,
  unanswered: false,
  tags: [],
  user: '',
  answerCount: null,
}

const filterSlice = createSlice({
  name: 'filter',
  initialState,
  reducers: {
    filteringBy: (state, action) => {
      // state=initialState
      // 가 작동안하는 이유는 순수함수만 가능해서 ?
      state.newest= false
      state.highestscore= false
      state.unanswered= false
      state.tags= []
      state.user= ''
      state.answerCount= null
      state[action.payload]= true
    },
    customfilter: (state, action) => {
      //state = action.payload 로 통째로 바꿔버리면 작동 안함
      state.newest = action.payload.newest
      state.highestscore = action.payload.highestscore
      state.unanswered = action.payload.unanswered
      state.tags = action.payload.tags
    },
    searchBarfilter: (state,action) => {
      if(action.payload.tags){
        state.tags = [action.payload.tags]
      } else if(action.payload.user){
        state.user = action.payload.user
      } else {
        state.answerCount = Number(action.payload.answerCount)
      }
    },
}})

export const { filteringBy, customfilter, searchBarfilter } = filterSlice.actions
export default filterSlice.reducer
