<template>
  <myHeader v-if="path!='/login/'"></myHeader>
  <router-view /> 
</template>
<script>
 import myHeader from '@/views/header/index'
//  import {mapMutations} from 'vuex'
export default {
  name: 'App',
  components: {
    myHeader
  },
  watch:{
      path(newVal){
        //   console.log(newVal,888)
      }
  },
  mounted(){
    // this.getUser({a:1})
    // console.log(this.$store.state.user)
    // console.log(this.$store.getters['user/aa'])
  },
  methods:{
    //  ...mapMutations('user',{getUser:'SET_USER'})
  },
  computed:{
    path(){
      return this.$route.path.slice(0,7)
    }
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
  height: 100%;
}
html, body {
  font-size: 10px;
  height: 100%;
  font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
  overflow-y: hidden;
  overflow-x: hidden;
}

a,
div,
p {
  font-style: normal;
  text-decoration: none;
  margin: 0px;
  padding: 0px;
}

ul,
li {
  list-style: none;
  padding: 0;
  margin: 0;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>
