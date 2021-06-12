import {createApp} from 'vue';
import App from './App.vue';
import {registerGlobComp} from "./components/registerGlobComp";
import "ant-design-vue/dist/antd.min.css"; // or 'ant-design-vue/dist/antd.less'
import {setupI18n} from "/@/locales/setupI18n";


async function bootstrap() {
    const elementApp = createApp(App);
    registerGlobComp(elementApp)
    // Multilingual configuration
    await setupI18n(elementApp);

    elementApp.mount('#app')
}

void bootstrap()
