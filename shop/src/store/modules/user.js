const state = {
  user:{}
}
const mutations = { //commit
  SET_USER(state,user){
    state.user = user
  }
}
const actions = {  //dispatch
  setUser(context,user){
    context.commit('SET_USER',user)
  }
}
// const getters = {
//   aa(state){
//     return '00'  //取this.$store.getters['user/aa']
//   }
// }
export default {
  namespaced: true,
  state, mutations, actions
  // ,getters
}