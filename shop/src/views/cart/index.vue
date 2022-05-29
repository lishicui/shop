<template>
  <div id="cart">
    <div class="title">
      <span>我的购物车(全部{{productList.length}})</span>
    </div> 
    <div class="header" v-show="cartList.length!=0" >
        <div style="width:15%;">
            <input v-model="selectAll" @change="allChecked($event)" type="checkbox" style="vertical-align: text-bottom;">
            全选
        </div>
        <div style="width:34%;">商品信息</div>
        <div style="width:16%;">单价</div>
        <div style="width:17%;">数量</div>
        <div style="width:8%;">售价</div>
        <div style="width:10%;">
            <el-button v-show="selectAnyOne" @click="removeProduct()" size="mini" type="danger">删除</el-button>
        </div>
    </div>

    <div v-show="cartList.length==0" style="text-align:center;">
        <img :src="require('../../assets/images/emptyCart.gif')" alt=""/>
    </div>

    <div class="card">
        <div class="content" v-for="(item,index) in cartList" :key="index">
            <div class="name">
                <input type="checkbox" @change="shopChecked" v-model="shopNameList" :value="item.shopName">店铺：{{item.shopName}}
            </div>
            <div class="dec" v-for="(m,i) in item.list" :key="i">
                <span><input type="checkbox" v-model="idList" @change="singleChecked" :value="m.productID"></span>
                <div style="width:10%"><img :src="require('../../'+m.cover)" /></div>
                <div style="width:15%">{{m.productName}} </div>
                <div style="width:7%">
                    {{m.color}}
                    {{m.size}}
                    {{m.capacity}}
                </div>
                <div style="width:16%">
                    <template v-if="m.discount!=null">
                        <p style="color:#8d8c8c;line-height:1px;"><s>￥{{m.price}}</s></p>
                        ￥{{m.price*m.discount}}
                    </template>
                    <template v-else>￥{{m.price}}</template>
                </div>
                <div style="width:16%"><!--数量限制到1-9999-->
                    <el-input-number size="mini" v-model="m.quantity" @change="changeQuantity(m)" :min="1" :max="9999"></el-input-number>
                </div>
                <div style="width:16%">￥{{calculateSum(m)}}</div>
                <i style="color:red" @click="removeProduct(m.productID)" class="el-icon-delete"></i>
            </div>
        </div>
    </div>
    <div class="footer" v-show="cartList.length!=0">
        已选商品<span>{{num}}</span>件，
        合计：<span>{{$filters.formatNum(sum)}}</span>
        <el-button @click="submit" :class="{'submit':selectAnyOne}" round :disabled="!selectAnyOne">结算</el-button>
    </div>
    
  </div>
</template>
<script>
var vm;
import cartApi from '@/api/cart.js'
const {addProductToCart, getCartListByUId, deleteProduct} = cartApi
export default {
    name:'cart',
    data(){
        return{
            productList: [],
            cartList: [],
            multipleSelection: [],
            selectAnyOne: false,//有一个多多个选中
            idList: [],
            selectAll: false,//全选
            shopNameList: [],//选中商家
            num: 0,//选中商品数量
            sum: 0,//总金额

            userId:null
        }
    },
    watch:{
        idList(newData){
            this.sum = 0
            this.num = 0
            if(newData.length != 0){
                this.selectAnyOne = true  //解除提交按钮禁用
                for(let i = 0; i < newData.length; i++){
                    for(let j = 0; j < this.productList.length; j++){
                        if(newData[i] == this.productList[j].productID){
                            this.sum += this.productList[j].price*this.productList[j].discount*this.productList[j].quantity
                            this.num += this.productList[j].quantity
                            break;
                        }
                    }
                }
            }else{
                this.selectAnyOne = false
            }
        },
    },
    beforeCreate () {
        vm = this
    },
    mounted(){
        if($cookies.get('user')){
            vm.userId = $cookies.get('user').id;
            vm.initData();
        }
    },
    methods: {
        //初始化数据
        initData(){
           getCartListByUId({uID: vm.userId})
            .then(val => {
                if(val.code == 10000){
                   vm.productList = val.data;
                   let list = []
                   vm.productList.forEach(item => {
                        var shopName = item.shopName
                        if (!list[shopName]) {
                            list[shopName] = {
                                shopName,
                                list: []
                            }
                        }
                        list[shopName].list.push(item);
                    });
                    vm.cartList = JSON.parse(JSON.stringify(Object.values(list)));//解除双向绑定
                }
            }).catch(err => {
                console.log(err)
            })
        },
        //改变商品数量
        changeQuantity(data){
            addProductToCart({
                productID: data.productID,
                uID: data.uID, 
                quantity: data.quantity,
             })
            .then(val => {
                if(val.code == 10000){
                    this.initData();
                }
            }).catch(err => {
                console.log(err);
            })
        },
        removeProduct(id){
            let idStr = ''
            if(id == undefined){
                idStr = this.idList.join(",")
            }else{
                idStr = id
            }
            deleteProduct({uID:this.userId,ids:idStr}).then(val=>{
                if(val.code==10000){
                    this.$message.success('删除成功');
                    if(id==undefined){
                        this.idList = []
                    }else{
                        let index= this.idList.indexOf(id)
                        if(index!=-1){
                            this.idList.splice(index,1)
                        }
                    }
                    this.initData();
                }else{
                    this.$message.error('删除失败，请稍后重试')
                }
            }).catch(err=>{
                console.log(err)
                this.$message.error('网络异常，请稍后重试')
            })
        },
        //单选
        singleChecked(e){
            this.shopNameList = []
            if(this.productList.length == this.idList.length){
                this.selectAll = true;
                this.cartList.forEach(item =>{
                    this.shopNameList.push(item.shopName)
                })
            }else{
                this.selectAll = false 
                let list = []
                for(let i =0; i < this.cartList.length; i++){
                    for(let j = 0; j < this.cartList[i].list.length; j++){
                        for(let k = 0; k < this.idList.length; k++){
                            if(this.cartList[i].list[j].productID == this.idList[k]){
                                list.push(this.idList[k])
                                if(list.length == this.cartList[i].list.length){
                                    this.shopNameList.push(this.cartList[i].shopName)
                                    list = []
                                }
                                break;
                            }
                        }
                    }
                }
            }
        },
        //全选
        allChecked(e){
            if(this.selectAll){
                this.idList = []
                this.shopNameList = []
                this.productList.forEach(item =>{
                    this.idList.push(item.productID)
                })
                this.cartList.forEach(item =>{
                    this.shopNameList.push(item.shopName)
                })
            }else{
                this.idList = []
                this.shopNameList = []
            }
        },
        //选中商家
        shopChecked(e){
            this.idList = []
            if(this.shopNameList.length == this.cartList.length){
                this.productList.forEach(item =>{
                    this.idList.push(item.productID)
                })
                this.selectAll = true;
            }else{
                this.shopNameList.forEach(item =>{
                    this.productList.forEach(m =>{
                        if(m.shopName == item){
                            this.idList.push(m.productID)
                        }
                    })
                })
                this.selectAll = false;
            }
        },
        submit(){
            let list = []
            this.idList.forEach(id=>{
                this.productList.forEach(m=>{
                    if(id == m.productID){
                        list.push(m)
                    }
                })
            })
            sessionStorage.setItem("proList", JSON.stringify(list));
            sessionStorage.setItem("idList", JSON.stringify(this.idList));
            vm.$router.push('/submitOrder')
        },
        calculateSum(data){
            if(data.discount!=null){
                return (data.quantity*data.price*data.discount).toFixed(2)
            }
            return (data.quantity*data.price).toFixed(2)
        }
    },
  
}
</script>
<style scoped>
#cart{
    width: 100%;
    color: #303133;
    display: block;
    line-height: 30px ;
}
.title{
    text-align: left;
    width: 100%;
    font-size: 14px;
    font-weight: 800;
    margin: 10px;
}
.header{
   margin: 0 80px;
}
.header div{
    text-align: left;
    display: inline-block;
}
.card{
    width:100%;
    overflow: auto;
    max-height: calc(100vh - 150px);
    padding: 0 50px;
}
.card .content{
    background-color: #e6dfe759;
    border-radius: 30px;
    margin-bottom: 10px;
}
.card .content .name{
    margin-left: 80px;
    font-weight: 800;
    font-size: 14px;
    text-align: left;
}
.card .content .name input{
    vertical-align: middle;
    margin-right: 4px;
}




.card .content .dec span{
    vertical-align: top;
}
.card .content .dec img{
    width: 100px;
}
.card .content .dec div{
    display: inline-block;
    vertical-align: middle;
}


.card .content .dec ::v-deep(.el-input__inner){
    text-align: center!important;
}


.card .content .dec i{
    margin: 0 5px;
    font-weight: 600;
    color: #666;
    cursor: pointer;
}


.footer{
    font-size: 14px;
    margin: 5px 100px 0 100px;
    text-align: right;
}
.footer span{
    color: red;
    font-size: 16px;
    margin: 0 2px;
}
.footer button{
    padding: 5px 20px;
    margin-left: 10px;
}
.submit{
    background:#f56c6c!important;
    color:#fff!important;
}
.submit:hover{
    background-color: #f77d7d!important;
}



::-webkit-scrollbar {
	width: 7px;
    height: 7px;
}
::-webkit-scrollbar-track {
	border-radius: 10px;
	background-color: #f6f4f7;
}
::-webkit-scrollbar-thumb {
	border-radius: 10px;
	box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.1);
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.1);
	background-color: #cac3cb;
}
</style>