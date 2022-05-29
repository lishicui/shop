<template>
    <div id="productDetail">
        <div class="header">
            <!-- {{product.shopName}} -->
        </div>
        <div class="content">
            <el-card :body-style="{ padding: '0px' }">
                <div style="display:flex">
                    <div style="flex:1">
                        <img v-if="product.cover!=undefined" :src="require('../../'+product.cover)"  class="image">
                        <el-carousel v-if="imgList.length!=0" :interval="4000" type="card" height="80px" :autoplay="false">
                            <el-carousel-item  v-for="(item,index) in imgList" :key="index">
                                <img :src="require('../../'+item)">
                            </el-carousel-item>
                        </el-carousel>
                    </div>

                    <div style="flex:1;padding: 14px 0;text-align: left;">
                        <p style="font-size:16px;">{{product.content}}</p>
                        <div class="price">
                            <div>
                                原价<span style="margin-left:22px;"><del>￥{{product.price}}</del></span>
                            </div>
                            <div>
                                优惠价<span style="margin-left:10px;color:red;font-size:16px;">￥{{product.price*product.discount}}</span>
                            </div>
                        </div>
                        <div class="size">
                            <span>数量</span>
                            <el-input-number size="mini" v-model="params.quantity" :min="1" :max="10000"></el-input-number>
                        </div>
                        <div class="size" v-if="colorList.length!=0">
                            <span>颜色分类</span>
                            <el-radio-group v-model="params.color" size="mini">
                                <el-radio v-for="item in colorList" :key="item" :label="item" border></el-radio>
                            </el-radio-group>
                        </div>
                        <div  class="size" v-if="capacityList.length!=0">
                            <span>容量分类</span>
                            <el-radio-group v-model="params.capacity" size="mini">
                                <el-radio v-for="item in capacityList" :key="item" :label="item" border></el-radio>
                            </el-radio-group>
                        </div>
                        <div  class="size" v-if="sizeList.length!=0">
                            <span>尺寸分类</span>
                            <el-radio-group v-model="params.size" size="mini">
                                <el-radio v-for="item in sizeList" :key="item" :label="item" border></el-radio>
                            </el-radio-group>
                        </div>
                        <div class="size">
                            <el-button type="warning">立即购买</el-button>
                            <el-button type="danger">加入购物车</el-button>
                        </div>
                        <div class="size" style="line-height:25px;">
                            <span>承诺</span>七天无理由退货<br>
                            <span>支付</span><span>支付宝</span><span>微信</span><span>银联</span>
                        </div>
                    </div>
                </div>
            </el-card>
        </div>
    </div>
</template>
<script>
import proApi from '@/api/product'
const {getProductInfo} = proApi
export default {
    name:'productDetail',
    data(){
        return{
            product:{},
            imgList:[],
            params:{
                productID:null,
                quantity:null,
                color:null,
                size:null,
                capacity:null
            },
            sizeList:[],
            colorList:[],
            capacityList:[],
        }
    },
    mounted () {
        this.getProduct(this.$route.query.id)
    },
    methods:{
        getProduct(id){
            getProductInfo({
                id: id
            }).then(val => {
                if(val.code == 10000){
                    let data = val.data
                    this.product = data
                    for(let i=0;i<6;i++){
                        this.imgList.push(data.cover)
                    }
                    if(data.colors!=null){
                        this.colorList = data.colors.split(',')
                    }
                    if(data.capacitys!=null){
                        this.capacityList = data.capacitys.split(',')
                    }
                    if(data.sizes!=null){
                        this.sizeList = data.sizes.split(',')
                    }
                }
            }).catch(err=>{
                console.log(err)
            })
        }
    }
}
</script>
<style scoped>
.content{
    margin-top: 50px;
    left:20%;
    width: 60%;
    position: absolute;
    line-height: 30px;
    /* display: flex; */
}
.image{
    height: 400px;
}

.price{
    margin: 10px 10px 10px 0;
    background-color: #e6dfe7;
    padding: 10px 5px;
    color: #666;
}
.size{
    padding: 10px 5px;
}
.size span{
    width: 60px;
    display: inline-block;
}
.el-carousel__item img {
    width: auto;
    height: 80px;
  }
</style>