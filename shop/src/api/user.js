import request from '@/api/request'

export default {
	userLogin(params){
		return request({
			url: '/user/login',
			method: 'post',
			params
		})
	},
	userRegister(params){
		return request({
			url: '/user/regist',
			method: 'post',
			params
		})
	},
    getAddressList(params){
        return request({
            url:'/address/getAddressList',
            method:'get',
            params
        })
    },
    updateAddressById(params) {
		return request({
			url: '/address/updateAddressById',
			method: 'post',
            params
		})
    },
    insertAddressByUId(params) {
		return request({
			url: '/address/insertAddressByUId',
			method: 'post',
            params
		})
    },
    deleteAddressById(params) {
        return request({
            url: '/address/deleteAddressById',
            method: 'post',
            params
        })
    }
};