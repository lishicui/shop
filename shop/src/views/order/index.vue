<template>
    <div id="order">
        <div class="header">
            <div class="search">
                <el-input placeholder="请输入内容" v-model="keyWord" class="input-with-select">
                    <template v-slot:prepend>
                        <el-select v-model="select"  placeholder="请选择">
                            <el-option label="订单编号" value="id"></el-option>
                            <el-option label="商品名称" value="proName"></el-option>
                        </el-select>
                    </template>
                    <template v-slot:append>
                        <el-button icon="el-icon-search" @click="getOrderData()"></el-button>
                    </template>
                </el-input>
            </div>
            <div class="manu">
                <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                    <el-menu-item index="all">所有订单</el-menu-item>
                    <el-menu-item index="non-pay">待付款</el-menu-item>
                    <el-menu-item index="non-receive">待收货</el-menu-item>
                    <el-menu-item index="non-evalute">待评价</el-menu-item>
                </el-menu>
            </div>
            
            <div style="text-align:left;padding: 10px 40px 10px 20px;">
                <div style="text-align:center;display:flex;background-color:#f5f7fa;padding:10px 0;line-height:25px;margin-bottom:10px;">
                    <span style="width:20%">宝贝</span>
                    <span style="width:20%">单价</span>
                    <span style="width:20%">数量</span>
                    <span style="width:20%">实付</span>
                    <span style="width:20%">操作</span>
                </div>
                <template v-if="orderList.length!=0">
                    <input v-model="selectAll" @change="allChecked($event)" type="checkbox" style="vertical-align: text-bottom;">
                    全选
                    <el-button style="margin-left:10px;" size="mini" @click="confirmReceipt()">批量确认收货</el-button>
                </template>
                <template v-else>
                    <div style="align-items:center;justify-content:center;display:flex;height:100px">暂无订单，去首页逛逛哦~</div>
                </template>
            </div>
        </div>
        
        <div class="main">
            <div v-for="item in orderList" :key="item.id" class="order-list">
                <div style="float:left;line-height:30px;">
                    <input type="checkbox" v-model="idList" @change="singleChecked" :value="item.id">
                    {{item.orderTime.slice(0,10)}}
                    订单编号：{{item.id}}
                </div>
                <table>
                    <tr v-for="(m,i) in item.plist" :key="i">
                        <td>
                             <div style="display:flex">
                                <div>
                                    <img :src="require('../../'+m.cover)" />
                                </div>
                                <div style="display: block;padding-top: 10px;">
                                    <span style="vertical-align: top;">{{m.productName}}</span>
                                    <span v-if="m.color!=null">颜色：{{m.color}}</span>
                                    <span v-if="m.size!=null">尺码：{{m.color}}</span>
                                    <span v-if="m.capacity!=null">容量：{{m.capacity}}</span>
                                </div>
                            </div>
                        </td>
                        <td style="width:20%;">
                            <del>￥{{$filters.formatNum(m.price)}}</del>
                            <p style="color:#000;">￥{{$filters.formatNum(m.price*m.discount)}}</p>
                        </td>
                        <td style="width:20%;">
                            {{m.num}}
                        </td>
                        <td style="width:20%;" v-if="i==0" :rowspan="item.plist.length">
                            ￥{{item.actuallyPrice}}
                        </td>
                        <td style="width:20%;" v-if="i==0" :rowspan="item.plist.length">
                            订单详情
                        </td>
                    </tr>
                </table>
            </div>
            
        </div>
        
    </div>
</template>
<script>
import orderApi from '@/api/order'
const {getOrderList} = orderApi
export default {
    name:'order',
    data(){
        return{
            keyWord:'',//搜索关键词
            activeIndex:'all',//菜单索引

            select:'id',
            params:{
                id:null,
                uID:'',
            },
            orderList:[],
            idList:[],
            selectAll:false,//全选
            
        }
    },
    mounted () {
        let user = $cookies.get('user')
        if(user!=null&&user!=undefined){
            this.params.uID = user.id;
        }  
        this.getOrderData();
    },
    methods:{
        //菜单切换
        handleSelect(key){
            if(key=='all'){
                this.getOrderData();
            }else{
                
            }
        },
        //商品名称模糊查询
        changeData(){
            let aa = this.orderList;
            let list = []
            for(let i = 0;i < aa.length;i++){
                let bb = false;
                for(let j = 0; j < aa[i].plist.length;j++){
                    if(aa[i].plist[j].productName.indexOf(this.keyWord)!=-1){
                        bb = true
                        break;
                    }
                }
                if(bb){
                    list.push(aa[i])
                } 
            }
            this.orderList = list;
        },
        //获取订单列表
        getOrderData(){
            if(this.select=='proName'){
                this.changeData();
            }else{
                this.params.id = this.keyWord.replace(/[^(\d)]/g,"");
                console.log(this.params.id)
                getOrderList(this.params).then(val=>{
                    if(val.code==10000){
                        this.orderList  = val.data;
                    }
                }).catch(err=>{
                    console.log(err)
                })
            }
        },
        //单休
        singleChecked(e){
            if(this.orderList.length == this.idList.length){
                this.selectAll = true;
            }else{
                this.selectAll = false;
            }
        },
        //全选
        allChecked(){
            if(this.selectAll){
                this.idList = []
                this.orderList.forEach(item =>{
                    this.idList.push(item.id)
                })
            }else{
                this.idList = []
                this.shopNameList = []
            }
        },
        //确认收货
        confirmReceipt(){
            if(this.idList.length==0){
                this.$message.warning('请选择订单')
            }else{
                this.$message('功能接入中~')
            }
        }
    }
}
</script>
<style scoped>
#order{
    width: 100%;
    font-size:14px;
    /* padding: 20px 50px; */
}
#order .header{
    text-align: left;
    padding: 10px 30px 0 30px;
    text-align: left;
}
.header>>> .el-input-group{
    width: 420px;
}
.header .search{
    float: right;
    margin-top: 15px;
    position: absolute;
    right: 100px;
    z-index: 9999999;
}
.header .search>>> .el-input-group__prepend{
    width: 120px;
}
.main{
    max-height: calc(100vh - 150px);
    overflow: auto;
    padding: 0 30px;
}
.main .order-list:first-child{
    margin-top:0;
}
.order-list{
    margin:10px 20px;
}
.order-list table{
    color: #666;
    width: 100%;
    border-collapse: collapse;
   
}
.order-list table td{
   border: 2px solid #fff;
   background-color: #f5f7fa;
}

.order-list table tr td img{
    padding: 5px;
    width: 80px;
}
</style>