<template>
  <div id='my-header'>
    <ul>
      <li>
        <router-link :to="'/setting'">
          <span>设置</span>
        </router-link>/
      </li>
      <li>
        <router-link :to="'/mine'">
          <span>我的</span>
        </router-link>/
      </li>
      <li>
        <router-link :to="'/order'">
          <span>订单</span>
        </router-link>/
      </li>
      <li>
        <router-link :to="'/cart'">
          <span>购物车</span>
        </router-link>/
      </li>
      <li>
        <router-link :to="'/'">
          <span>首页</span>
        </router-link>/
      </li>
      <li>
        <template v-if="name!=''"><span>欢迎您，{{name}}</span></template>
        <template v-else>
          <router-link :to="'/login/'+'log'"><span>请登录</span></router-link>/
          <router-link :to="'/login/'+'reg'"><span style="margin-left:15px;">注册</span></router-link>/
        </template>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  name:'myHeader',
  data(){
    return{
      name:'',
    }
  },
  mounted () {
    let user = $cookies.get('user')
    if(user!=null&&user!=undefined){
      this.name = user.name;
    }
  }
}
</script>
<style scoped>
#my-header{
  width: 100%;
  height: 30px;
  background-color: #e6dfe7;
  color: #fff;
  font-size: 14px;
}
#my-header ul li{
  float: right;
  padding: 5px 5px 0 15px;
}
#my-header ul li span{
  cursor: pointer;
  margin-right: 10px;
  /* font-weight: 600; */
  color: #fff!important;
}
</style>