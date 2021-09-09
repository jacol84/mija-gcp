import {StateModel, UserDto} from "/@/app/test";
import {computed, reactive} from "vue";
import ajax from "/@/utils/service/ajax/ajax";
import {Action} from "/@/utils/service/form/action";

function getIndex(x: UserDto[]) {
    return Math.floor(Math.random() * x.length);
}

export function userRegistryService() {

    const state: StateModel = reactive({
        search: "a",
        myData: "zzzz",
        test: false,
        list: undefined,
        form: {
            action: Action.NEW
        }
    });
    const closeForm = () => {
        state.form.visible = false;
    }
    const openForm = (record?: UserDto) => {
        state.form.id = record?.id
        state.form.visible = true;
    }
    const actionForm = computed(() => state.form.id ? Action.NEW : Action.EDIT);

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