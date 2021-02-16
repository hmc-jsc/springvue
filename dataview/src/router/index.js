import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../views/login'
import regedit from '../views/regedit'
import index from  '../views/index'
import addData from  '../views/addData'
import dataAll from  '../views/dataAll'
import upDate from  '../views/upDate'
import clientView from '../views/clientView'
Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    component:login,
    show:false,
    meta: {allowBack: false}
  },
  {
    path: '/login',
    name: 'login',
    component: login,
    show:false,
    meta: {allowBack: false}
  },
  {
    path: '/regedit',
    name: 'regedit',
    component: regedit,
    show:false,
    meta: {allowBack: false}
  },
  {
    path: '/index',
    name: '主页',
    component: index,
    show:true,
    redirect:'/dataAll',
  meta: {allowBack: false},
    children:[
      {
        path:'/dataAll',
        name:'所有信息',
        component:dataAll,
        show:true,
        meta: {allowBack: false}
      },
      {
        path:'/addData',
        name:"添加图书",
        component:addData,
        show:true,
        meta: {allowBack: false}
      },
      {
        path:'/upDate',
        name:"添加图书",
        component:upDate,
        show:false,
        meta: {allowBack: false}
      }
    ]
  },
  {
    path:'/clientView',
    name:"查看书籍",
    component:clientView,
    show:false,
    meta: {allowBack: false}
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
