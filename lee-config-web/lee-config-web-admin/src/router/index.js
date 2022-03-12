import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import LeeConfigAdmin from "../components/LeeConfigAdmin";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: "/lee-config-admin",
      name: 'LeeConfigAdmin',
      component: LeeConfigAdmin
    }
  ]
})
