import {ReadonlyRecordable} from "/#/global";
import {defineComponent} from 'vue';
import {LocaleSpecification} from 'moment';

declare module '*.vue' {
    const Component: ReturnType<typeof defineComponent>;
    // export default Component;
}

declare module 'ant-design-vue/es/locale/*' {
    // import { Locale } from 'ant-design-vue/types/locale-provider';
    // const locale: Locale & ReadonlyRecordable;
    // export default locale as Locale & ReadonlyRecordable;
}

declare module 'moment/dist/locale/*' {
    import { LocaleSpecification } from 'moment';
    const locale: LocaleSpecification & ReadonlyRecordable;
    export default locale;
}

declare module 'virtual:*' {
    const result: any;
    export default result;
}
