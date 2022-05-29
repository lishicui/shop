import { createStore } from 'vuex'
import user from './modules/user'

export default createStore({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  // getters:{
  //   aa(state){
  //     return '00'  //取this.$store.getters['aa']
  //   }
  // },
  modules: {
    user
  }
})
