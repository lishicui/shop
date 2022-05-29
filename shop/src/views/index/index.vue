<template>
<div id="index" v-if="path!='/login'" >
  <Nav></Nav>
  <ProductList></ProductList>
</div>
</template>
<script>
  import Nav from '@/views/nav/index'
  import ProductList from '@/views/productList/index'
export default {
  name:'index',
  components: {
    Nav,
    ProductList
  },
  computed:{
    path(){
      return this.$route.path
    }
  },
}
</script>