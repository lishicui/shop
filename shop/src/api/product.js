import request from '@/api/request'
export default {
    getProductList() {
        return request({
            url: '/product/getProductList',
            method: 'get',
        })
    },
    getProductInfo(params){
        return request({
            url: '/product/getProductInfo',
            method: 'get',
            params
        })
    }
}