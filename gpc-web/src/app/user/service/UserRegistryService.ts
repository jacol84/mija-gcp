import {ModalForm, StateModel, UserDto} from "/@/app/test";
import {reactive} from "vue";
import ajax from "/@/utils/service/ajax/ajax";
import {Action} from "/@/utils/service/form/action";
import {FormExt} from "/@/app/utils/formUtil/dto/FormUtilDto";
import {BasicModalDto, creatorDto} from "/@/utils/modal/BasicModalDto";
import {UnwrapNestedRefs} from "@vue/reactivity";

function getIndex(x: UserDto[]) {
    return Math.floor(Math.random() * x.length);
}

function modalAction(basicModalDto: UnwrapNestedRefs<BasicModalDto>) {
    const modalForm: UnwrapNestedRefs<ModalForm> = reactive({
            action: Action.NEW
        }
    )
    const openForm = (record?: UserDto) => {
        basicModalDto.visible = true
        modalForm.opening = new Date().getTime();
        modalForm.id = record?.id;
    }
    return {modalForm, openForm}
}


export function userRegistryService() {
    const basicModalDto = reactive(creatorDto());

    const {modalForm, openForm} = modalAction(basicModalDto)
    const state: StateModel = reactive({
        search: "",
        myData: "",
        test: false,
        list: undefined,
        modalForm: modalForm
    });
    const formExt: FormExt = {
        close: () => {
            basicModalDto.visible = false
        }
    };

    const handleSearch = () => {
        ajax.getJson<UserDto[]>("user").then(x => {
                state.test = !state.test
                state.myData = x[getIndex(x)].lastName;
                state.list = state.test ? x || [] : []
            }
        );
    };

    const handleAdd = () => {
        openForm();
    };

    return {
        state, formExt, openForm, basicModalDto,
        handleAdd,
        handleSearch,
    }
}