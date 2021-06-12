<template>
  <AButton v-on:click="formState.test=!formState.test"> BUTTON</AButton>
  <h1 v-if="formState.test">Vue is awesome! {{ formState.test }}</h1>
  <UserFormXXX v-if="formState.test"></UserFormXXX>
  <Spin></Spin>
</template>

<script lang="ts">

import {defineComponent, reactive, UnwrapRef} from "vue";
import {AButton} from "/@/components/antd";
import {createAsyncComponent} from "/@/util/component/asyncComponent";
import Spin from "/@/view/utils/Spin.vue";

interface StateModel {
  test: boolean
}


export default defineComponent({
  name: "Test",
  components: {
    UserFormXXX: createAsyncComponent(() => import('/@/view/user/UserFormXXX.vue')),
    AButton,
    Spin,
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