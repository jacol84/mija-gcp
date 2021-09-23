import {reactive, UnwrapRef, watchEffect} from "vue";
import ajax from "/@/utils/service/ajax/ajax";
import {FormUtilDto} from "/@/app/utils/formUtil/dto/FormUtilDto";
import {FormState} from "/@/app/user/dto";

function loadDateForm(formUtil: UnwrapRef<FormUtilDto<FormState>>, id: number | undefined) {
    if (id) {
        const handleSearch = (id: number) => {
            ajax.getJson<FormState>(`user/${id}`).then(x => {
                    formUtil.loading = false;
                    formUtil.formState = x
                }
            );
        };
        handleSearch(id);
    } else {
        formUtil.loading= false;
    }
}

function initVal() {
    return reactive({
        loading: true,
        formState: {}
    } as FormUtilDto<FormState>);
}

export function userFormService(id: number | undefined) {
    const formUtil: UnwrapRef<FormUtilDto<FormState>> = initVal();


    watchEffect(() => {
        formUtil.loading = true;
        loadDateForm(formUtil, id)
    });

    return {formUtil}
}