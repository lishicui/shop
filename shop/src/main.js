import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import Cookies from 'vue-cookies'

import ElementPlus from 'element-plus'
import 'element-plus/lib/theme-chalk/index.css'
import locale from 'element-plus/lib/locale/lang/zh-cn.js' //element汉化

import {formatNum} from './plugins/filter'  //全局过滤器

const app = createApp(App)
//  app.config.globalProperties.$bus = createApp({})  //创建全局事件总线--组件通信


ElementPlus.useLang = (app, ref, locale) => {
  const template = (str, option) => {
      if (!str || !option) return str
      return str.replace(/\{(\w+)\}/g, (_, key) => {
      return option[key]
      })
  }
  // 注入全局属性,子组件都能通过inject获取
  app.provide('ElLocaleInjection', {
      lang: ref(locale.name),
      locale: ref(locale),
      t: (...args) => {
      const [path, option] = args
      let value
      const array = path.split('.')
      let current = locale
      for (let i = 0, j = array.length; i < j; i++) {
          const property = array[i]
          value = current[property]
          if (i === j - 1) return template(value, option)
          if (!value) return ''
          current = value
      }
      },
  })
}

ElementPlus.useLang(app, createApp, locale)
app.use(ElementPlus)

app.config.globalProperties.axios = axios
app.config.globalProperties.$cookies = Cookies

app.config.globalProperties.$filters = {
    formatNum,
}
    
app.use(store).use(router).mount('#app')