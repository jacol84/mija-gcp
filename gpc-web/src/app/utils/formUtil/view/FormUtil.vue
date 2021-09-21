<template>
  <loading v-model:loading="form.loading">
    <a-form
        layout="inline"
        :model="form.formState"
        @finish="form.handleFinish"
        @finishFailed="form.handleFinishFailed"
    >
      <slot></slot>
    </a-form>
  </loading>
</template>

<script lang="ts">
import {defineComponent, PropType} from "vue";
import {FormUtilDto} from "/@/app/utils/formUtil/dto/FormUtilDto";
import {AForm} from "/@/components/antd";
import Loading from "/@/app/utils/component/Loading.vue";


export default defineComponent({
  name: "FormUtil",
  components: {Loading},
  props: {
    form: {
      type: Object as PropType<FormUtilDto<any>>
    },
    components: {AForm, Loading,},
  },
})
</script>

<style scoped>

</style>