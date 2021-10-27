import {reactive, UnwrapRef, watch} from "vue";
import ajax from "/@/utils/service/ajax/ajax";
import {FormExt, FormUtilDto, makeDto} from "/@/app/utils/formUtil/dto/FormUtilDto";
import {FormUserState} from "/@/app/user/dto";
import {UnwrapNestedRefs} from "@vue/reactivity";
import {ModalForm} from "/@/app/test";

function loadDateForm(formUtil: UnwrapRef<FormUtilDto<FormUserState>>, id?: number ) {
    if (id) {
        const handleSearch = (idz: number) => {
            ajax.getJson<FormUserState>(`user/${idz}`).then(x => {
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
            formUtil.close()
            //FIXME what next
            //when error
            //when valid
        }
    );
}

function onUpdate(id: number, formUtil: UnwrapNestedRefs<FormUtilDto<FormUserState>>) {
    console.log(JSON.stringify(formUtil.formState));

    ajax.sendPut<FormUserState>(`user/${id}`, formUtil.formState).then(x => {
            formUtil.loading = false;
            formUtil.close()
            //FIXME what next
            //when error
            //when valid
        }
    );
}

function onSubmit(formUtil: UnwrapNestedRefs<FormUtilDto<FormUserState>>, id?: number) {
    return () => {
        formUtil.loading = true;
        id ? onUpdate(id, formUtil) : onCreate(formUtil);
    }
}

export function userFormService(modalForm?: ModalForm | undefined, formExt?: FormExt | undefined) {
    const formUtil = reactive(makeDto<FormUserState>(formExt));
    resetValue(formUtil);
    watch(() => modalForm?.opening, (y, x) => {
        resetValue(formUtil);
        loadDateForm(formUtil, modalForm?.id);
        formUtil.onSubmit = onSubmit(formUtil, modalForm?.id);
    });

    return {formUtil}
}