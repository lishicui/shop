import request from '@/api/request'

export default {
    addProductToCart(params){
        return request({
            url: '/cart/addProductToCart',
            method: 'post',
            params
        })
    },
    getCartListByUId(params){
        return request({
            url: '/cart/getCartListByUId',
            method: 'get',
            params
        })
    },
    deleteProduct(params){
        return request({
            url: '/cart/deleteProductById',
            method: 'post',
            params
        })
    }
}