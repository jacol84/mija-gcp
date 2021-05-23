import { createApp } from 'vue'
import App from './App.vue'
import {registerGlobComp} from "./components/registerGlobComp";

import "ant-design-vue/dist/antd.css"; // or 'ant-design-vue/dist/antd.less'


const elementApp = createApp(App);
registerGlobComp(elementApp)
elementApp.mount('#app')
