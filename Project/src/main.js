// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from "axios";
import ElementUI from 'element-ui'; //引入核心js组件
import 'element-ui/lib/theme-chalk/index.css';
//引入图标库
import './assets/icons/iconfont/iconfont.css'//引入图标
//引入依赖的样式

Vue.use(ElementUI)
Vue.prototype.$http=axios
Vue.prototype.$tokenErrorNum = 0
axios.defaults.baseURL="http://localhost:8088"
Vue.config.productionTip = false

//路由拦截器
router.beforeEach((to, from, next) => {

  if(to.meta.isLogin){
    const username = sessionStorage.getItem("username")
    if(username==null){
      next("/login")
    }else{
      next()
    }
  }else {
    next()
  }
})

//axios的请求拦截器
axios.interceptors.request.use(config=>{
  // _self.$http.defaults.headers.common['satoken'] = sessionStorage.getItem("satoken")
  const token = sessionStorage.getItem("satoken")
  if(token!=null){
    config.headers.satoken = token
  }
  return config;
})
//axios的响应拦截器
axios.interceptors.response.use(resp=>{
  console.log(resp)
  if(resp.request.responseURL.includes("/api/logout")){
    return resp
  }
  if(resp.data.code==11011){
    if(_self.$tokenErrorNum==0){
      _self.$tokenErrorNum = _self.$tokenErrorNum + 1
      _self.$confirm('token异常，请重新登录！', '提示',{
        confirmButtonText: '确定',
        type: 'warning',
        showCancelButton: false,
        // showClose: false,
        // closeOnPressEscape: false,
        // closeOnClickModal: false
      }).then(function () {
        _self.$tokenErrorNum = 0
        _self.$router.replace("/login")
      }).catch(function () {
        _self.$tokenErrorNum = 0
        _self.$router.replace("/login")
      })
    }else {
      return
    }
  }
  return resp;
},error =>{
  _self.$message.error("服务器出错！")
  return Promise.reject(error.response.data)
} )



/* eslint-disable no-new */
const _self = new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
