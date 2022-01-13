import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

import axios from 'axios'
Vue.prototype.$axios = axios

import ElementUI from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import 'element-ui/lib/theme-chalk/display.css';
Vue.use(ElementUI)

import './style/common.css'

axios.defaults.baseURL = "http://110.40.199.128:8099"

new Vue({
  beforeCreate(){
    Vue.prototype.$bus = this
  },
  router,
  store,
  render: h => h(App)
}).$mount('#app')
