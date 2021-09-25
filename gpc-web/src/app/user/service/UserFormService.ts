import {reactive, Ref, UnwrapRef, watchEffect} from "vue";
import ajax from "/@/utils/service/ajax/ajax";
import {FormUtilDto, makeDto} from "/@/app/utils/formUtil/dto/FormUtilDto";
import {FormState} from "/@/app/user/dto";
import {UnwrapNestedRefs} from "@vue/reactivity";

function loadDateForm(formUtil: UnwrapRef<FormUtilDto<FormState>>, id: Number | undefined) {
    if (id) {
        const handleSearch = (id: Number) => {
            ajax.getJson<FormState>(`user/${id}`).then(x => {
                    formUtil.loading = false;
                    formUtil.formState = x
                }
            );
        };
        handleSearch(id);
    } else {
        formUtil.loading = false;
    }
}

function resetValue(formUtil: UnwrapNestedRefs<FormUtilDto<FormState>>) {
    formUtil.loading = true;
    formUtil.formState = {};
}

export function userFormService(id: Ref<UnwrapRef<Number | undefined>>) {

    const formUtil = reactive(makeDto<FormState>());
    resetValue(formUtil);

    watchEffect(() => {
        resetValue(formUtil);
        loadDateForm(formUtil, id.value)
    })

    return formUtil
}