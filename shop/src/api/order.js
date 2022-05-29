import request from '@/api/request'

export default {
   insertOrder(data) {
        return request({
            url: '/order/insertOrderByUId',
            method: 'post',
            data:data
        })
    },
    getOrderList(params) {
        return request({
            url: '/order/getOrderList',
            method: 'get',
            params
        })
    }
}