import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/Login";
import Index from "../components/Index";
import Home from "../components/Home";
import ErrorPage from "../components/ErrorPage";
import User from "../components/UserM";
import UserProfile from "../components/UserProfile";
import Jieyue from "../components/RecordB";
import RecordB from "../components/RecordB";
import UserM from "../components/UserM";
import BookM from "../components/BookM";
import CGY from "../components/CGY";
import ReM from "../components/ReM";
import Search from "../components/Search";
import ReturnB from "../components/ReturnB";
import Register from "../components/Register";
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
      path: '/register',
      name: 'Register',
      component: Register
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
          path: 'userM',
          name: 'UserM',
          meta:{isLogin: true},
          component: UserM
        },
        {
          path: 'userProfile',
          name: 'UserProfile',
          meta:{isLogin: true},
          component: UserProfile
        },
        {
          path: 'bookM',
          name: 'BookM',
          meta:{isLogin: true},
          component: BookM
        },
        {
          path: 'cgy',
          name: 'CGY',
          meta:{isLogin: true},
          component: CGY
        },
        {
          path: 'reM',
          name: 'ReN',
          meta:{isLogin: true},
          component: ReM
        },
        {
          path: 'search',
          name: 'Search',
          meta:{isLogin: true},
          component: Search
        },
        {
          path: 'returnB',
          name: 'ReturnB',
          meta:{isLogin: true},
          component: ReturnB
        },
        {
          path: 'recordB',
          name: 'RecordB',
          meta:{isLogin: true},
          component: RecordB
        },
      ]
    }
  ]
})
