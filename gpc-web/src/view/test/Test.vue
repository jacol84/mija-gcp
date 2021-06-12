<template>
  <AButton v-on:click="formState.test=!formState.test"> BUTTON</AButton>
  <h1 v-if="formState.test">Vue is awesome! {{ formState.test }}</h1>
  <UserFormXXX v-if="formState.test"></UserFormXXX>
  <UserList></UserList>
</template>

<script lang="ts">

import {defineComponent, reactive, UnwrapRef} from "vue";
import {AButton} from "/@/components/antd";
import {createAsyncComponent} from "/@/utils/component/asyncComponent";
import UserList from "/@/view/user/UserList.vue";

interface StateModel {
  test: boolean
}

export default defineComponent({
  name: "Test",
  components: {
    UserFormXXX: createAsyncComponent(() => import('/@/view/user/UserFormXXX.vue')),
    AButton,
    UserList
  },
  setup() {
    const formState: UnwrapRef<StateModel> = reactive({
      test: false
    });
    const onClick = (values: StateModel) => {
      console.log(values, formState);
    };
    return {
      formState,
      onClick
    }
  },
})

</script>

<style scoped>

</style>