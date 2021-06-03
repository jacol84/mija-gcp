import {createApp} from 'vue';
import antd from 'ant-design-vue';
import App from './App.vue';
import {registerGlobComp} from "./components/registerGlobComp";
// createApp(App).use(antd).mount('#app');
import "ant-design-vue/dist/antd.min.css"; // or 'ant-design-vue/dist/antd.less'
import {setupI18n} from "/@/locales/setupI18n";

// import "ant-design-vue/lib/button/style/css";
// import "ant-design-vue/lib/form/style/css";
// import 'ant-design-vue/lib/button/style/css';

async function bootstrap() {
    const elementApp = createApp(App);
// elementApp.use(antd)
    registerGlobComp(elementApp)


    // Multilingual configuration
    await setupI18n(elementApp);

    elementApp.mount('#app')
}

void bootstrap()
