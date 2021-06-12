import {defineAsyncComponent} from "vue";
import {noop} from "ant-design-vue/es/vc-menu/util";
import Spin from "/@/view/utils/Spin.vue"
interface Options {
    delay?: number;
    timeout?: number;
    loading?: boolean;
    retry?: boolean;
}

export function createAsyncComponent(loader: Fn, options: Options = {}) {
    const {delay = 100, timeout = 30000, loading = true, retry = true} = options;

    return defineAsyncComponent({
        loader,
        loadingComponent: loading ? Spin : undefined,
        timeout,
        delay,
        onError: !retry
            ? noop
            : (error, retry, fail, attempts) => {
                //TODO
                retry()
                console.error(error);
            },
    });
}