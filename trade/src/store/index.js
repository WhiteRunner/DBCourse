import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    userId: '',
    superuser: '',
    catalog:[]
  },
  mutations: {
    //存储token方法
    //设置token等于外部传递进来的值 
    setToken(state, token) {
      state.token = token
      localStorage.token = token //同步存储token至localStorage
    },
    //存储用户id
    setUserId(state, userId) {
      state.userId = userId
      localStorage.userId = userId
    },
    //存储用户权限
    setSuperuser(state, superuser) {
      state.superuser = superuser
      localStorage.superuser = superuser
    },
    //存储商品类别
    setCatalog(state, catalog) {
      state.catalog = catalog
      localStorage.setItem('catalog',JSON.stringify(catalog))
    }
  },
  getters: {
    //获取token方法
    //判断是否有token,如果没有重新赋值，返回给state的token
    getToken(state) {
      if (!state.token) {
        state.token = localStorage.getItem('token')
      }
      return state.token
    },
    getUserId(state) {
      if (!state.userId) {
        state.userId = localStorage.getItem('userId')
      }
      return state.userId
    },
    getSuperuser(state) {
      if (!state.superuser) {
        state.superuser = localStorage.getItem('superuser')
      }
      return state.superuser
    },
    getCatalog(state) {
      // console.log("getCatalog",state.catalog.length)
      if (state.catalog.length==0) {
        state.catalog = JSON.parse(localStorage.getItem('catalog'))
      }
      return state.catalog
    }
  },
  actions: {
  },
  modules: {
  }
})
