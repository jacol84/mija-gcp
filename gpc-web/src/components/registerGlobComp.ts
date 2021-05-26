import {Button, Form, Input} from 'ant-design-vue';
import type {App, DefineComponent} from 'vue';
// import { setupPermissionDirective } from './permission';
// import { setupLoadingDirective } from './loading';
const compList: Array<any> = [Button, Button.Group, Input, Form, Form.Item];

// const compList:Array<any> = []

export function registerGlobComp(app: App) {
    compList.forEach((comp: any) => {
        app.component(comp.name || comp.displayName, comp);
    });
}

export function setupGlobDirectives(app: App) {
    // setupPermissionDirective(app);
    // setupLoadingDirective(app);
}