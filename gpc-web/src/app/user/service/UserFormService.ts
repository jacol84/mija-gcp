import {reactive, UnwrapRef, watch} from "vue";
import ajax from "/@/utils/service/ajax/ajax";
import {FormExt, FormUtilDto, makeDto} from "/@/app/utils/formUtil/dto/FormUtilDto";
import {FormUserState} from "/@/app/user/dto";
import {UnwrapNestedRefs} from "@vue/reactivity";
import {ModalForm} from "/@/app/test";

function loadDateForm(formUtil: UnwrapRef<FormUtilDto<FormUserState>>, id?: number) {
    if (id) {
        const handleSearch = (idz: number) => {
            Promise.all([
                ajax.getJson<FormUserState>(`user/${idz}`),
                ajax.getJson<FormUserState>(`user/params`)
            ]).then(x => {
                    formUtil.loading = false;
                    formUtil.formState = x[0];
                    console.log(x[1])
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
    return ajax.sendPost<FormUserState>('user', formUtil.formState);
}

function onUpdate(id: number, formUtil: UnwrapNestedRefs<FormUtilDto<FormUserState>>) {
    console.log(JSON.stringify(formUtil.formState));
    return ajax.sendPut<FormUserState>(`user/${id}`, formUtil.formState)
}

function onSubmit(formUtil: UnwrapNestedRefs<FormUtilDto<FormUserState>>, id?: number) {
    return () => {
        formUtil.loading = true;
        const formUserStatePromise = id ? onUpdate(id, formUtil) : onCreate(formUtil);
        Promise.all([formUserStatePromise]
        ).then(x => {
                formUtil.loading = false;
                formUtil.close()
                //FIXME what next
                //when error
                //when valid
            }
        );
    }
}


const rules = {
    name: [
        {required: true, message: 'Please input Activity name', trigger: 'blur'},
        {min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur'},
    ],
    // region: [{ required: true, message: 'Please select Activity zone', trigger: 'change' }],
    // date1: [{ required: true, message: 'Please pick a date', trigger: 'change', type: 'object' }],
    // type: [
    //     {
    //         type: 'array',
    //         required: true,
    //         message: 'Please select at least one activity type',
    //         trigger: 'change',
    //     },
    // ],
    // resource: [{ required: true, message: 'Please select activity resource', trigger: 'change' }],
    // desc: [{ required: true, message: 'Please input activity form', trigger: 'blur' }],
};


export function userFormService(modalForm?: ModalForm | undefined, formExt?: FormExt | undefined) {
    const formUtil = reactive(makeDto<FormUserState>(formExt));
    formUtil.rules = rules
    resetValue(formUtil);
    loadDateForm(formUtil, modalForm?.id);
    watch(() => modalForm?.opening, (y, x) => {
        resetValue(formUtil);
        loadDateForm(formUtil, modalForm?.id);
        formUtil.onSubmit = onSubmit(formUtil, modalForm?.id);
    });

    return {formUtil}
}