<template>
  <div id="product-list">
    <div class="left"></div>
    <div class="middle">
      <el-row>
        <el-col :span="3" v-for="(item, index) in productList.slice((listQuery.page - 1) * listQuery.size, listQuery.page * listQuery.size)" :key="item.id" :offset="index > 0 ? 2 : 0">
          <el-card :body-style="{ padding: '0px' }">
            <img  @click="showProduct(item.id)" :src="require('../../'+item.cover)" class="image">
            <div style="padding: 14px;">
              <span class="text">{{item.content}}</span>
              <div class="bottom clearfix">
                <span class="price">￥{{item.price}}</span>
                <span v-show="item.quantity" class="number">
                  <template v-if="item.quantity>99">···</template>
                  <template v-else>{{item.quantity}}</template>
                </span>
                <a style="vertical-align:super;cursor:pointer;" @click="openDialog(item)">
                  <img style="width:20px;float:right" src="~@/assets/icon/addToCart.png" />
                </a>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <div>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="listQuery.page"
          :page-sizes="[20, 50, 100, 200]"
          :page-size="listQuery.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="productList.length">
        </el-pagination>
      </div>
    </div>
    <div class="right"></div>

    <el-dialog title="选择商品属性" v-model="open" width="30%">
        <div v-if="product.colorList.length!=0">
            颜色分类({{product.colorList.length}})
            <div class="content">
                <el-radio-group v-model="product.color" size="mini">
                    <el-radio v-for="item in product.colorList" :key="item" :label="item" border></el-radio>
                </el-radio-group>
            </div>
        </div>
        <div v-if="product.capacityList.length!=0">
            容量分类({{product.capacityList.length}})
            <div class="content">
                <el-radio-group v-model="product.capacity" size="mini">
                    <el-radio v-for="item in product.capacityList" :key="item" :label="item" border></el-radio>
                </el-radio-group>
            </div>
        </div>
        <div v-if="product.sizeList.length!=0">
            尺寸分类({{product.sizeList.length}})
            <div class="content">
                <el-radio-group v-model="product.size" size="mini">
                    <el-radio v-for="item in product.sizeList" :key="item" :label="item" border></el-radio>
                </el-radio-group>
            </div>
        </div>
        <div>
            购买数量 
            <div class="content">
                <el-input-number size="mini" v-model="product.quantity" :min="1" :max="10000"></el-input-number>
            </div>
        </div>
        <div style="text-align:center;">
            <el-button type="primary" @click="addToCart()">添加</el-button>
            <el-button @click="open=false">关闭</el-button>
        </div>
    </el-dialog>
  </div>
</template>
<script>
var vm;
import proApi from '@/api/product.js'
import cartApi from '@/api/cart.js'
const {getProductList} = proApi
const {addProductToCart, getCartListByUId} = cartApi
export default {
  data(){
    return{
      list: [],
      productList: [],
      cartList: [],
      listQuery:{
          page: 1,
          size: 20,
      },
      
      showNum: true,//是否显示购物车数量，>=1时显示
      userId: null,
      open: false,
      product:{
          id: null,//商品id
          colorList: [],
          sizeList: [],
          capacityList: [],
          color: null,
          size: null,
          capacity: null,
          quantity: null
      },
    }
  },
  
  beforeCreate () {
    vm = this;
  },
  created () {
    vm.getAllProducts();
    if($cookies.get('user')){
      vm.userId = $cookies.get('user').id;
    }
  },
  methods: {
    //获取所有产品列表
    getAllProducts(){
      vm.page = 1;
      getProductList().then(val => {
        if(val.code == 10000){
          vm.list = val.data
          if(vm.userId != undefined && vm.userId != null){//已登录
            vm.getCartList();
          }else{//未登录
            vm.productList = vm.list
          }
        }
      }).catch(err => {
        console.log(err)
      })
    },
    //购物车列表
    getCartList(){
      getCartListByUId({uID: vm.userId})
      .then(val => {
        if(val.code == 10000){
          vm.cartList = val.data;
          if(vm.cartList.length != 0){
            for(let i = 0; i < vm.list.length; i++){
              for(let j = 0; j < vm.cartList.length; j++){
                if(vm.list[i].id == vm.cartList[j].productID){
                  vm.list[i].quantity = vm.cartList[j].quantity;
                }
              }
            }
          }
          vm.productList = vm.list;
        }
      }).catch(err => {
        console.log(err)
      })
    },
    handleSizeChange(val){
      vm.listQuery.size = val;
    },
    handleCurrentChange(val){
      vm.listQuery.page = val;
    },
    //添加到购物车
    openDialog(item){
        this.product = {
            id: item.id,
            colorList: [],
            sizeList: [],
            capacityList: [],
            size:null,
            color:null,
            capacity:null,
            quantity:null
        }
        if(item.colors!=null){
            this.product.colorList = item.colors.split(',')
        }
        if(item.capacitys!=null){
            this.product.capacityList = item.capacitys.split(',')
        }
        if(item.sizes!=null){
            this.product.sizeList = item.sizes.split(',')
        }
        this.open = true;
    },
    addToCart(){
        addProductToCart({
            productID: this.product.id, 
            uID: vm.userId, 
            quantity: this.product.quantity,
            color: this.product.color,
            size: this.product.size,
            capacity: this.product.capacity
        }).then(val => {
            if(val.code == 10000){
                this.open = false;
                vm.getCartList();
            }
        }).catch(err => {
            console.log(err);
        })
    },
    showProduct(id){
        vm.$router.push({path:'/productDetail',query:{id:id}})
    }
  }
}
</script>
<style scoped>
#product-list{
  overflow: scroll;
  width: 100%;
  max-height: calc(100vh - 90px);
  display: flex;
}
#product-list .right{
  /* width:5%; */
  float: right;
}
#product-list .left{
  /* width:8%; */
  float: left;
}
#product-list .middle{
  float: right;
  /* width: 87%; */
  margin: 10px 10px 10px 40px;
}
#product-list .middle .price {
  font-size: 13px;
  color: red;
}
#product-list .middle .bottom {
  margin-top: 13px;
  line-height: 12px;
  vertical-align: super;
}
#product-list .middle .text{
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  text-overflow: ellipsis;
}

#product-list .middle .image {
  width: 100%;
  display: block;
  cursor: pointer;
}
#product-list .middle ::v-deep(.el-col){
  margin: 5px 10px!important;
}

#product-list .clearfix:before,
#product-list .clearfix:after {
    display: table;
    content: "";
}
#product-list .clearfix:after {
    clear: both
}
.number{
  min-width: 12px;
  max-width: 14px;
  height: 12px;
  float: right;
  background-color: red;
  color: #fff;
  border-radius: 50%;
}

#product-list>>> .el-dialog{
    text-align: left;
}
#product-list>>> .el-dialog .el-dialog__body{
    padding-top: 0!important;
}
.content{
    padding: 10px;
}
</style>