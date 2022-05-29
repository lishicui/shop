<template>
  <div id="login">
		<div class="login-content">
			<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
				<!-- <h3 class="title">购物系统</h3> -->
				<el-form-item label="用户名" prop="name">
					<el-input v-model.number="ruleForm.name"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="pwd">
					<el-input type="password" v-model="ruleForm.pwd" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="确认密码" prop="checkpwd" v-if="!login">
					<el-input type="password" v-model="ruleForm.checkpwd" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 0px!important;">
					<el-button type="primary" @click="submitForm()">
						<template v-if="login">登录</template>
						<template v-else>注册</template></el-button>
					<el-button @click="resetForm('ruleForm')">重置</el-button>
				</el-form-item>
				<el-form-item>
					<span class="left" @click="login=!login">
						<template v-if="login">立即注册</template>
						<template v-else>立即登录</template>
					</span>
					<span class="right">忘记密码</span>
				</el-form-item>
			</el-form>
		</div>
  </div>
</template>
<script>
var vm;
import api from '@/api/user.js'
const {userLogin,userRegister} = api
export default {
  name:'login',
  data(){
		var validatepwd = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请输入密码'));
			} else {
				if (this.ruleForm.checkpwd !== '') {
					this.$refs.ruleForm.validateField('checkpwd');
				}
				callback();
			}
		};
		var validatepwd2 = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请再次输入密码'));
			} else if (value !== this.ruleForm.pwd) {
				callback(new Error('两次输入密码不一致!'));
			} else {
				callback();
			}
		};
    return{
		login:true,
        ruleForm: {
            pwd: '',
            checkpwd: '',
            name: ''
        },
        rules: {
            pwd: [
                { required: true, validator: validatepwd, trigger: 'blur' }
            ],
            checkpwd: [
                { required: true, validator: validatepwd2, trigger: 'blur' }
            ],
            name: [
                { required: true, message: '请输入用户名', trigger: 'blur' },
                { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
            ]
        }
    }
  },
  beforeCreate () {
    vm = this;
  },
  mounted () {
		let type = this.$route.params && this.$route.params.type
		if(type == 'log'){
			this.login = true;
		}else if(type=='reg'){
			this.login = false;
		}
				
  },
  methods:{
		resetForm(formName) {
      vm.$refs[formName].resetFields();
    },
		submitForm() {
			vm.$refs['ruleForm'].validate((valid) => {
				if (valid) {
					if(vm.login){
						userLogin({
							pwd:vm.ruleForm.pwd,
							name:vm.ruleForm.name
						}).then(val=>{
							if(val.code == 10000){
								let obj = val.data
								$cookies.set('user',obj)
								// vm.$store.dispatch('user/setUser',obj)
								vm.$router.push('/')
							}
						}).catch(err=>{
							console.log(err)
						})
					}else{
						userRegister({
							pwd:vm.ruleForm.pwd,
							name:vm.ruleForm.name
						}).then(val=>{
							if(val.code == 10000){
								vm.$message.success('注册成功!')
								vm.login = false;
								vm.resetForm('ruleForm');
							}
						}).catch(err=>{
							console.log(err)
						})
					}
				} else {
					return false;
				}
			});
		},
  }
}
</script>
<style scoped>
#login{
	height: 100%;
	background-size: 100% 100%;
	background-image: url('~@/assets/images/bg.jpg');
}
.login-content{
	box-shadow: 10px 10px 5px #9ca5b2;
  background-color:rgba(199, 199, 199, 0.6);
	border-radius: 5px;
	width: 450px;
	margin: 0;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}
.login-content .left{
	color: #666;
	cursor: pointer;
	float:left;
	font-size:12px;
} 
.login-content .right{
	color: #666;
	cursor: pointer;
	float:right;
	font-size:12px;
} 
.login-content ::v-deep(.el-form){
	padding: 40px 50px 0px 0;
}
.login-content ::v-deep(.el-button){
	width: 48%;
}
/* .title {
			font-size: 26px;
			font-weight: 400;
			color: #eee;
			margin: 0px auto 40px auto;
			text-align: center;
			font-weight: bold;
	} */
</style>