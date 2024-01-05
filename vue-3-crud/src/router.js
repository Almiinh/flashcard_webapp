import { createWebHistory, createRouter } from "vue-router";

const routes = [
    {
        path: "/",
        alias: "/cards",
        name: "cards",
        component: () => import("./components/CardsList")
    },
    {
        path: "/cards/:id",
        name: "card-details",
        component: () => import("./components/Card")
    },
    {
        path: "/cards/add",
        name: "cards-add",
        component: () => import("./components/AddCard")
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;