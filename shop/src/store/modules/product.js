const state = {
  type:{},//菜单栏选择的类型
}
const mutations = { //commit
  SET_NAV_TYPE(state,type){
    state.type = type
  }
}
const actions = {  //dispatch
  setNavType({commit},type){
    commit('SET_NAV_TYPE',type)
  }
}
export default {
  namespaced: true,
  state, mutations, actions
}