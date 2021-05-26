import {createApp} from 'vue';
import antd from 'ant-design-vue';
import App from './App.vue';
import {registerGlobComp} from "./components/registerGlobComp";
// createApp(App).use(antd).mount('#app');
import "ant-design-vue/dist/antd.css"; // or 'ant-design-vue/dist/antd.less'


const elementApp = createApp(App);
elementApp.use(antd)
registerGlobComp(elementApp)
elementApp.mount('#app')
