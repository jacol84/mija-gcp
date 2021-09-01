<template>
  <!--  <Button v-on:click="state.test=!state.test">Jest tu Mikołaj</Button>-->
  <h1>Vue is awesome! {{ state.test }}</h1>
  <input v-model="state.myData"/>
  <!--  <UserFormXXX v-if="state.test"></UserFormXXX>-->
  <a-input v-model:value="state.search" v-on:keypress.enter="handleSearch"></a-input>
  <AButton v-on:click="handleSearch"> Search</AButton>
  <UserList :list="state.list"></UserList>

</template>

<script lang="ts">

import {defineComponent} from "vue";
import {AButton, AInput} from "/@/components/antd";
import {createAsyncComponent} from "/@/utils/component/asyncComponent";
import {Button} from "ant-design-vue";
import {userRegistryService} from "/@/app/user/service/UserRegistryService";


const test = defineComponent({
  name: "Test",
  components: {
    Button: Button,
    AInput: AInput,
    AButton,
    UserList: createAsyncComponent(() => import('/@/app/user/view/UserList.vue')),
  },
  setup: function () {
    return {...userRegistryService()};
  },
})


export default test
</script>

<style scoped>

</style>