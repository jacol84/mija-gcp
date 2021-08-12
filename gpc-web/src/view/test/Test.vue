<template>
  <!--  <Button v-on:click="state.test=!state.test">Jest tu Mikołaj</Button>-->
  <!--  <AButton v-on:click="onClick"> BUTTON</AButton>-->
  <!--  <AButton v-on:click="onSearch">  BUTTON</AButton>-->
  <h1>Vue is awesome! {{ state.test }}</h1>
  <AButton v-on:click="()=> state.test = !state.test "> Search</AButton>
  <!--  <h2>Vue is awesome! {{ myData }}</h2>-->
  <h2 v-if="state.test">Vue is awesome! {{ JSON.stringify(state.list) }}</h2>
  <input v-model="state.myData"/>
  <UserFormXXX v-if="state.test"></UserFormXXX>
  <a-input v-model:value="state.search" v-on:keypress.enter="handleSearch"></a-input>
  <AButton v-on:click="handleSearch"> Search</AButton>

  sasasssssssssss
  <UserList :list="state.list"></UserList>
  sss

</template>

<script lang="ts">

import {computed, defineComponent, reactive} from "vue";
import {AButton, AInput} from "/@/components/antd";
import {createAsyncComponent} from "/@/utils/component/asyncComponent";
import {Button} from "ant-design-vue";
import ajax from "/@/utils/service/ajax/ajax";
import {StateModel, UserDto} from "/@/view/test/index";


function getIndex(x: Array) {
  return Math.floor(Math.random() * x.length);
}

const test = defineComponent({
  name: "Test",
  components: {
    UserFormXXX: createAsyncComponent(() => import('/@/view/user/UserFormXXX.vue')),
    Button: Button,
    AInput: AInput,
    AButton,
    UserList: createAsyncComponent(() => import('/@/view/user/UserList.vue')),
  },
  setup: function () {

    const state: StateModel = reactive({
      search: "a",
      myData: "zzzz",
      test: false,
      list: null
    });

    const myCom = computed(() => "jacek" + state.search + new Date());

    const handleSearch = () => {
      ajax.getJson<Array<UserDto>>("user").then(x => {
            state.test = !state.test
            state.myData = x[getIndex(x)].lastName;
            state.list = state.test ? x || [] : []
          }
      );
    };

    return {state, handleSearch, myCom};
  },
})


export default test
</script>

<style scoped>

</style>