<template>
  <div id="my-nav">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">家用电器</el-menu-item>
      <el-submenu index="2">
        <template v-slot:title>手机</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
        <el-menu-item index="2-3">选项3</el-menu-item>
        <el-submenu index="2-4">
          <template v-slot:title>选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>
          <el-menu-item index="2-4-2">选项2</el-menu-item>
          <el-menu-item index="2-4-3">选项3</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3">电脑</el-menu-item>
      <el-menu-item index="4">家居</el-menu-item>
      <el-menu-item index="5">男装</el-menu-item>
      <el-menu-item index="6">女装</el-menu-item>
      <el-menu-item index="7">男鞋</el-menu-item>
      <el-menu-item index="8">女鞋</el-menu-item>
      <el-menu-item index="9">美妆</el-menu-item>
      <el-menu-item index="10">母婴</el-menu-item>
      <el-menu-item index="11">食品</el-menu-item>
      <el-menu-item index="12">图书</el-menu-item>
      <el-item style="float:right;height: 60px;line-height: 60px;margin: 0;padding:0 20px;">
        <el-input placeholder="请输入关键字" v-model="keyWord" class="input-with-select">
          <template v-slot:append>
            <el-button icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </el-item>
    </el-menu>
  </div>
</template>
<script>
export default {
  name:'myNav',
  data(){
    return{
       activeIndex: '2-1',
       type:"1",
       keyWord:'',
    }
  },
  methods: {
     handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
  }
}
</script>
<style scoped>
  #my-nav{
    justify-content: center;
    display: flex;
  }
  #my-nav ::v-deep(.el-menu--horizontal>.el-submenu.is-active .el-submenu__title) {
    border-bottom: 2px solid #e6dfe7!important;
}
</style>