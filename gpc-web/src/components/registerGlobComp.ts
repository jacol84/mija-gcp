import {Button} from 'ant-design-vue';

import type { App } from 'vue';
// import { setupPermissionDirective } from './permission';
// import { setupLoadingDirective } from './loading';

// const compList = [Button, Button.Group];
const compList = [];

export function registerGlobComp(app: App) {
    // compList.forEach((comp: any) => {
    //     app.component(comp.name || comp.displayName, comp);
    // });
}

export function setupGlobDirectives(app: App) {
    // setupPermissionDirective(app);
    // setupLoadingDirective(app);
}