<template>
    <div id='submit-order'>
        <div class="order-content">
            <div style="flex:1">
                <p style="margin: 7px 0 5px 20px;text-align:left;font-weight:600;color:#909399;">•&nbsp;收货信息</p>
                <el-form ref="form" :rules="rules" :model="form" label-width="100px">
                    <el-form-item label="收货人姓名">
                        <el-input v-model="form.name"></el-input>
                        <el-button size="small" type="info" @click="getAddress()" style="margin-left:5px;">选择地址</el-button>
                    </el-form-item>
                    <el-form-item label="收货人电话">
                        <el-input v-model="form.phone"></el-input>
                    </el-form-item>
                    <el-form-item label="收货人地址">
                        <el-input v-model="form.address"></el-input>
                    </el-form-item>
                    <el-form-item label="省市区">
                        <el-cascader
                        v-model="district"
                        placeholder="请选择"
                        :options="districtList"
                        filterable
                        />
                    </el-form-item>
                    <el-form-item label="邮政编码">
                        <el-input v-model="form.code"></el-input>
                    </el-form-item>
                    <el-form-item label="发票信息">
                        <el-radio-group v-model="form.bill">
                            <el-radio label="0">不需要发票</el-radio>
                            <el-radio label="1">需要发票</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="支付方式">
                        <el-radio-group v-model="form.paymentWay">
                            <el-radio label="Alipay">支付宝</el-radio>
                            <el-radio label="WeChat">微信</el-radio>
                            <el-radio label="card">信用卡</el-radio>
                            <el-radio label="other">其他</el-radio>
                        </el-radio-group>
                    </el-form-item>
                     <el-form-item label="快递方式">
                        <el-radio-group v-model="form.deliverWay">
                            <el-radio label="free">普通快递-免邮</el-radio>
                            <el-radio label="sf">顺丰快递(+10元)</el-radio>
                            <el-radio label="urgent">普通快递-加急(+8元)</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-form>
            </div>

            <div class="order-msg">
                <p style="margin-bottom:5px">•&nbsp;商品信息</p>
                <el-table
                    :data="productList"
                    max-height="500"
                    border
                    style="width: 100%;">
                    <el-table-column
                    label="商品"
                    width="300">
                     <template #default="scope">
                         <div style="display:flex;">
                             <div style="flex:1;">
                                 <img class="cover" :src="require('../../'+scope.row.cover)" alt=''/>
                             </div>
                             <div style="flex:3;margin:auto;text-align:left">
                                 {{scope.row.productName}}
                             </div>
                         </div>
                    </template>
                    </el-table-column>

                    <el-table-column
                    prop="quantity"
                    label="数量"
                    width="200">
                    </el-table-column>
                    <!-- <el-table-column
                    prop="discount"
                    label="优惠"
                    width="200">
                    </el-table-column> -->
                    <el-table-column
                    label="金额">
                        <template #default="scope">
                            {{scope.row.price*scope.row.quantity}}
                        </template>
                    </el-table-column>
                </el-table>
                <div class="paymentWay">
                    <span>{{form.quantity}}</span>件商品
                    总计<span>￥{{form.totalPrice}}</span>&nbsp;+&nbsp;
                    运费<span>￥{{$filters.formatNum(form.postage)}}</span>&nbsp;-&nbsp;
                    优惠<span>￥{{$filters.formatNum(form.discounts)}}</span>
                    <div>实付金额：<span>￥{{$filters.formatNum(form.actuallyPrice)}}</span></div>
                    <el-button @click="submitOrder" type="danger" round>提交订单</el-button>
                </div>
            </div>
        </div>


        <el-dialog title="收货地址" v-model="dialog1" width="50%" custom-class="address-list">
            <div style="min-height:100px;max-height: 400px;overflow:auto;">
                <div style="text-align:left;margin-bottom:5px;">
                    <el-button type="primary" size="mini" @click="addAddress">添加</el-button>
                </div>
                <div class="address" v-for="(item,index) in addressList" :key="index" >
                    <div @click="selectAddress(item)" style="flex:40">
                        <div>
                            <span style="width:20%">姓名：{{item.name}}</span>
                            <span style="width:30%">手机号：{{item.phone}}</span>
                            <span style="width:45%">省市区：{{item.district }}</span>
                        </div>
                        <div>
                            <span style="width:20%">标签：<el-tag size="small">{{item.type}}</el-tag></span>
                            <span style="width:75%">地址：{{item.address}}</span>
                        
                        </div>
                    </div>
                    <div style="flex:1">
                        <p style="width:5%;color:green;" @click="editAddress(item)"><i class="el-icon-edit"></i></p>
                        <p style="width:5%;color:red;" @click="deleteAddress(item.id)"><i class="el-icon-delete"></i></p>
                    </div>
                </div>
            </div>
            
        </el-dialog>

        <el-dialog title="" v-model="dialog2" width="40%">
            <el-form ref="addressForm" :model="addressForm" label-width="100px">
                <el-form-item label="收货人">
                    <el-input v-model="addressForm.name"></el-input>
                </el-form-item>
                <el-form-item label="手机号码">
                    <el-input v-model="addressForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="详细地址">
                    <el-input v-model="addressForm.address"></el-input>
                </el-form-item>
                <el-form-item label="所在地区">
                    <el-cascader style="width:100%"
                    v-model="addressForm.district"
                    placeholder="请选择"
                    :options="districtList"
                    filterable
                    />
                </el-form-item>
                <el-form-item label="邮政编码">
                    <el-input v-model="addressForm.code"></el-input>
                </el-form-item>
                <el-form-item label="设为默认地址" style="text-align: left;">
                    <el-switch active-value="1" inactive-value="0" v-model="addressForm.flag"></el-switch>
                </el-form-item>
                <el-form-item label="标签" style="text-align: left;">
                     <el-radio-group v-model="addressForm.type">
                        <el-radio label="home">家</el-radio>
                        <el-radio label="company">公司</el-radio>
                        <el-radio label="school">学校</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitAddress">提交</el-button>
                    <el-button @click="dialog2=false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
import userApi from '@/api/user'
const { getAddressList, updateAddressById, insertAddressByUId, deleteAddressById} = userApi
import orderApi from '@/api/order'
const {insertOrder} = orderApi
import options from '@/plugins/district'
export default {
    name:'submitOrder',
    data(){
        return{
            productList:[],//商品列表
            district:null,
            form:{//订单表单
                uID:null,
                name:null,
                phone:null,
                address:null,
                adcode:null,
                district:null,//区划
                productIds:null,//商品id字符串
                deliverWay:'free',//快递
                paymentWay:'Alipay',//支付方式
                bill:'0',//发票信息-默认不需要
                quantity:0,//总数
                discounts:0,//优惠金额
                totalPrice:0,//总金额
                actuallyPrice:0,//实付
                postage:0,//运费
            },
            addressForm:{},//地址表单
            districtList:options,//行政区
            rules: {//校验规则
                name: [
                    { required: true, message: '请输入联系人姓名', trigger: 'blur' },
                    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
                ],
                phone: [
                    { required: true, message: '请输入联系人电话', trigger: 'change' }
                ],
                address: [
                    { required: true, message: '请选择日期', trigger: 'change' }
                ],
                district: [
                    { required: true, message: '请选择时间', trigger: 'change' }
                ], 
            },

            dialog1:false,//地址列表弹窗布尔值
            dialog2:false,//添加-编辑弹窗布尔值

            loading:true,//遮罩层布尔值

            userId:'',   //用户id

            addressList:[], //地址列表    
        }
            
    },
    watch:{
        'form.deliverWay':function(val) {
            if(val == 'sf'){
                this.form.postage = 10
            }
            if(val=='urgent'){
                this.form.postage = 8
            }
            if(val == 'free'){
                this.form.postage = 0
            }
            this.form.actuallyPrice = this.form.totalPrice + this.form.postage - this.form.discounts
        }
    },
    created () {
        if($cookies.get('user')){
            this.userId = $cookies.get('user').id;
        }
        this.form.uID = this.userId
        this.productList = JSON.parse(sessionStorage.getItem('proList'))
        if(this.productList!=undefined){
            let quantityList = []
            this.productList.forEach(item=>{
                this.form.quantity += item.quantity
                this.form.discounts += (1-item.discount)*item.price*item.quantity
                this.form.totalPrice += item.quantity*item.price
                quantityList.push({id:item.productID,quantity:item.quantity,size:item.size,color:item.color,capacity:item.capacity})
            })
            this.form.productIds = JSON.stringify(quantityList)
            this.form.actuallyPrice = this.form.totalPrice - this.form.discounts
        }
    },
    methods: {
        //获取地址列表
        getAddress(type){
            if(type==undefined){
                this.dialog1 = true
            }
            getAddressList({uId:this.userId})
            .then(val=>{
                if(val.code==10000){
                    this.addressList = val.data
                }else{
                    console.log(val.message)
                }
            }).catch(err=>{
                console.log(err)
            })
        },
        //选择地址
        selectAddress(data){
            this.dialog1 = false;
            this.form.name = data.name
            this.form.phone = data.phone
            this.form.address = data.address
            this.form.code = data.code
            this.district = data.district.split(',') 
        },
        //提交订单
        submitOrder(){
            // console.log(this.form)
            this.form.district = this.district.join(',')
            insertOrder(this.form)
            .then(val=>{
                if(val.code==10000){
                    this.$message.success('提交成功');
                    this.$router.push('/order')
                }
            }).catch(err=>{
                console.log(err)
            })
        },
        //添加地址
        addAddress(){
            this.dialog2 = true;
            this.addressForm = {
                uID:this.userId,
                name:null,
                phone:null,
                address:null,
                code:null,
                district:null,
                type:null,
                flag:0
            }
        },
        //编辑地址
        editAddress(item){
            this.addressForm = JSON.parse(JSON.stringify(item));
            this.addressForm.district = this.addressForm.district.split(',');
            this.dialog2 = true;
        },
        //修改或添加地址提交
        submitAddress(){
            this.$refs.addressForm.validate((valid) => {
                if (valid) {
                    this.addressForm.district = this.addressForm.district.join(',')
                    if(this.addressForm.id!=undefined){
                        updateAddressById(this.addressForm)
                        .then(val=>{
                            if(val.code==10000){
                                this.$message.success('修改成功');
                                this.dialog2 = false;
                                this.getAddress(1);
                            }else{
                                this.$message.error('修改失败，请稍后再试')
                            }
                        }).catch(err=>{
                            console.log(err)
                            this.$message.error('网络异常，请稍后再试')
                        })
                    }else{
                        insertAddressByUId(this.addressForm)
                        .then(val=>{
                            if(val.code==10000){
                                this.$message.success('添加成功');
                                this.dialog2 = false;
                                this.getAddress(1);
                            }
                        }).catch(err=>{
                            console.log(err)
                        })
                    }
                }
            }) 
        },
        //删除地址
        deleteAddress(id){
            this.$confirm('是否永久删除该地址?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                deleteAddressById({id:id}).then(val=>{
                    if(val.code==10000){
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                    }
                }).catch(err=>{
                    this.$message({
                        type: 'error',
                        message: '删除失败，请稍后重试!'
                    });
                })
            }).catch(err=>{
                console.log('取消删除')
            })
        }
    }
}
</script>
<style scoped>
#submit-order{
    width: 100%;
    font-size: 14px;
}
#submit-order .order-content{
    display: flex;
    margin: 20px 50px 0 50px;
}

.order-content .order-msg{
    height: calc(100vh - 80px);
    margin: 5px 10px;
    text-align:left;
    flex:1;
    float: right;
}
.order-content .order-msg p{
    color: #909399;
    font-weight: 600;
}

.order-content .cover{
    width:80px;
}
.order-content .paymentWay{
    width: 100%;
    font-size: 14px;
    line-height: 35px;
    text-align: right;
}
.order-content .paymentWay span{
    color:red
}

.order-content>>> .el-form {
    width: 560px;
}
.order-content>>>  .el-input{
    display: block;
    width: 380px;
}
.order-content>>>  .el-form-item__content {
    display: -webkit-inline-box;
}

.address{
    text-align: left;
    margin-bottom: 5px;
    cursor: pointer;
    line-height: 25px;
    padding:5px;
    border-bottom: 1px solid #e1e0e0;
    display: flex;
}
.address:hover{
    background-color: #d7d7d7;
}
.address div span{
    display: inline-block;
}
</style>

<!--全局样式-->
<style>
.address-list .el-dialog__body{
    padding: 5px 20px!important;
}
.address-list .el-dialog__header{
    padding-bottom: 0!important;
} 
</style>