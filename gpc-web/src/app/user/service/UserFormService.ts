import {reactive, Ref, UnwrapRef, watchEffect} from "vue";
import ajax from "/@/utils/service/ajax/ajax";
import {FormUtilDto, makeDto} from "/@/app/utils/formUtil/dto/FormUtilDto";
import {FormUserState} from "/@/app/user/dto";
import {UnwrapNestedRefs} from "@vue/reactivity";

function loadDateForm(formUtil: UnwrapRef<FormUtilDto<FormUserState>>, id: Number | undefined) {
    if (id) {
        const handleSearch = (id: Number) => {
            ajax.getJson<FormUserState>(`user/${id}`).then(x => {
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

function resetValue(formUtil: UnwrapNestedRefs<FormUtilDto<FormUserState>>) {
    formUtil.loading = true;
    formUtil.formState = {};
}

function onCreate(formUtil: UnwrapNestedRefs<FormUtilDto<FormUserState>>) {
    console.log(JSON.stringify(formUtil.formState));

    ajax.sendPost<FormUserState>('user', formUtil.formState).then(x => {
            formUtil.loading = false;
            console.log(x, formUtil)

            //FIXME what next
            //when error
            //when valid
        }
    );
}

function onUpdate(id: Number, formUtil: UnwrapNestedRefs<FormUtilDto<FormUserState>>) {
    console.log(JSON.stringify(formUtil.formState));

    ajax.sendPut<FormUserState>(`user/${id}`, formUtil.formState).then(x => {
            formUtil.loading = false;
            console.log(x, formUtil)
            //FIXME what next
            //when error
            //when valid
        }
    );
}

function onSubmit(formUtil: UnwrapNestedRefs<FormUtilDto<FormUserState>>, id: Number | undefined) {
    return () => {
        formUtil.loading = true;
        id ? onUpdate(id, formUtil) : onCreate(formUtil);
    }
}

export function userFormService(id: Ref<UnwrapRef<Number | undefined>>) {

    const formUtil = reactive(makeDto<FormUserState>());
    resetValue(formUtil);


    watchEffect(() => {
        resetValue(formUtil);
        loadDateForm(formUtil, id.value);
        formUtil.onSubmit = onSubmit(formUtil, id.value);
    })

    return formUtil
}