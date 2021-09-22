import {reactive, UnwrapRef} from "vue";
import ajax from "/@/utils/service/ajax/ajax";
import {FormUtilDto} from "/@/app/utils/formUtil/dto/FormUtilDto";
import {FormState} from "/@/app/user/dto";


export function userFormService() {
    const formUtil: UnwrapRef<FormUtilDto<FormState>> = reactive({
        loading: true,
        formState: {}
    } as FormUtilDto<FormState>);

    const handleSearch = (id: number) => {
        ajax.getJson<FormState>(`user/${id}`).then(x => {
                formUtil.loading = false;
                formUtil.formState = x
            }
        );
    };


    return {formUtil, handleSearch}
}