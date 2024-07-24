import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/Login";
import Index from "../components/Index";
import Home from "../components/Home";
import ErrorPage from "../components/ErrorPage";
import User from "../components/User";
import UserProfile from "../components/UserProfile";
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/error',
      name: 'ErrorPage',
      component: ErrorPage
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'Index',
      // meta:{isLogin: true},
      redirect: '/index/home',
      component: Index,
      children: [
        {
          path: 'home',
          name: 'Home',
          meta:{isLogin: true},
          component: Home
        },
        {
          path: 'user',
          name: 'User',
          meta:{isLogin: true},
          component: User
        },
        {
          path: 'userProfile',
          name: 'UserProfile',
          meta:{isLogin: true},
          component: UserProfile
        }
      ]
    }
  ]
})
