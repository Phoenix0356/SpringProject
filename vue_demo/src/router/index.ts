import * as VueRouter from "vue-router";
import LoginPage from "@/pages/LoginPage.vue";

const router = VueRouter.createRouter({
  history: VueRouter.createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/user/login'
    },
    {
      name: "login",
      path: "/user/login",
      component: LoginPage,
    }
  ]
});

export default router;