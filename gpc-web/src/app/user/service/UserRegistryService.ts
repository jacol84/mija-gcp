import {StateModel, UserDto} from "/@/app/test";
import {computed, reactive} from "vue";
import ajax from "/@/utils/service/ajax/ajax";

function getIndex(x: UserDto[]) {
    return Math.floor(Math.random() * x.length);
}

export function userRegistryService() {

    const state: StateModel = reactive({
        search: "a",
        myData: "zzzz",
        test: false,
        list: null
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
    return {
        state, myCom,
        handleSearch,
    }
}