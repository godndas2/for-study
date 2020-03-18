import Vue from "vue";
import Router from "vue-router";
import AuthenticationService from "./service/AuthenticationService.js"

Vue.use(Router);

const router = new Router({
    mode: 'history', // browser history
    routes: [
        {
            path: "/",
            name: "Login",
            component: () => import("./components/Login")
        },
        {
            path: "/login",
            name: "Login",
            component: () => import("./components/Login"),
        },
        {
            path: "/users",
            name: "User",
            component: () => import("./components/User"),
            beforeEnter: (to, from, next) => {
                if (AuthenticationService.isUserLoggedIn()) {
                    next()
                } else {
                    next({ path: '/login'})
                }
            }
        },
        {
            path: "/logout",
            name: "Logout",
            component: () => import("./components/Logout"),
            beforeEnter: (to, from, next) => {
                if (AuthenticationService.isUserLoggedIn()) {
                    AuthenticationService.logout();
                }
                next();
            },

        },
    ]
});

export default router;