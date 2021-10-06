import {ModalForm, StateModel, UserDto} from "/@/app/test";
import {computed, reactive} from "vue";
import ajax from "/@/utils/service/ajax/ajax";
import {Action} from "/@/utils/service/form/action";
import {FormExt} from "/@/app/utils/formUtil/dto/FormUtilDto";

function getIndex(x: UserDto[]) {
    return Math.floor(Math.random() * x.length);
}

function modalAction() {
    const modalForm: ModalForm = reactive({
            action: Action.NEW
        }
    )
    const closeForm = () => {
        modalForm.visible = false;
    }
    const openForm = (record?: UserDto) => {
        modalForm.id = record?.id
        modalForm.visible = true;
    }
    const actionForm = computed(() => modalForm.id ? Action.NEW : Action.EDIT);
    return {modalForm, actionForm, closeForm, openForm}
}

export function userRegistryService() {
    const {modalForm, closeForm, openForm, actionForm} = modalAction()
    const state: StateModel = reactive({
        search: "a",
        myData: "zzzz",
        test: false,
        list: undefined,
        modalForm: modalForm
    });
    const formExt: FormExt = {
        close: closeForm
    };
    const myCom = computed(() => "jacek" + state.search + new Date());

    const handleSearch = () => {
        ajax.getJson<UserDto[]>("user").then(x => {
                state.test = !state.test
                state.myData = x[getIndex(x)].lastName;
                state.list = state.test ? x || [] : []
            }
        );
    };

    const handleAdd = () => {
        openForm(undefined);
    };

    console.log(formExt, typeof formExt)
    return {
        state, myCom, formExt, actionForm, openForm,
        handleAdd,
        handleSearch,
    }
}