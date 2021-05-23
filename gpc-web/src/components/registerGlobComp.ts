import {Button} from 'ant-design-vue';

import {App} from 'vue';

const compList = [Button, Button.Group];

export function registerGlobComp(app: App) {
    compList.forEach((comp: any) => {
        app.component(comp.name || comp.displayName, comp);
    });
}
