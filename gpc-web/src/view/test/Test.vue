<template>
  <AButton v-on:click="formState.test=!formState.test"> BUTTON</AButton>
  <h1 v-if="formState.test">Vue is awesome! {{ formState.test }}</h1>
  <UserFormXXX v-if="formState.test"></UserFormXXX>
</template>

<script lang="ts">

import {defineAsyncComponent, defineComponent, reactive, UnwrapRef} from "vue";
import {AButton} from "/@/components/antd";
import {Spin} from "ant-design-vue";

interface FormState {
  test: boolean

}

const UserFormXXX = defineAsyncComponent({
  loader: () => import('/@/view/user/UserFormXXX.vue'),
  loadingComponent: Spin,
  timeout: 3000,
});


export default defineComponent({
  name: "Test",
  components: {
    UserFormXXX,
    AButton,
  },
  setup() {
    const formState: UnwrapRef<FormState> = reactive({
      test: false
    });
    const onClick = (values: FormState) => {
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