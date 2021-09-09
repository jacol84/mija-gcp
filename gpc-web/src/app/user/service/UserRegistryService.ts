import {ModalForm, StateModel, UserDto} from "/@/app/test";
import {computed, reactive} from "vue";
import ajax from "/@/utils/service/ajax/ajax";
import {Action} from "/@/utils/service/form/action";

function getIndex(x: UserDto[]) {
    return Math.floor(Math.random() * x.length);
}

function modalAction() {
    const form: ModalForm = reactive({
            action: Action.NEW
        }
    )
    const closeForm = () => {
        form.visible = false;
    }
    const openForm = (record?: UserDto) => {
        form.id = record?.id
        form.visible = true;
    }
    const actionForm = computed(() => form.id ? Action.NEW : Action.EDIT);
    return {form, closeForm, openForm, actionForm}
}

export function userRegistryService() {
    const {form, closeForm, openForm, actionForm} = modalAction()
    const state: StateModel = reactive({
        search: "a",
        myData: "zzzz",
        test: false,
        list: undefined,
        form: form
    });

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

    return {
        state, myCom, closeForm, openForm,
        handleAdd,
        handleSearch, actionForm,
    }
}