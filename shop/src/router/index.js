import { createRouter, createWebHashHistory } from 'vue-router'
import Index from '@/views/index/index.vue'

const routes = [
    {
        path: '/',
        name: 'Index',
        component: Index
    },
    {
        path: '/login/:type',
        name: 'login',
        component: () => import('@/views/login/index.vue')
    },
    {
        path:'/cart',
        name:'cart',
        component: () => import('@/views/cart/index.vue')
    },
    {
        path:'/submitOrder',
        name:'submitOrder',
        component: () => import('@/views/submitOrder/index.vue')
    },
    {
        path:'/order',
        name:'order',
        component: () => import('@/views/order/index.vue')
    },
    {
        path:'/productDetail',
        name:'productDetail',
        component: () => import('@/views/productDetail/index.vue')
    },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router