<template>
  <Loading :show="form.loading">
    <a-form
        layout="vertical"
        :model="form.formState"
        :label-col="form.labelCol"
        :wrapper-col="form.wrapperCol"
        :rules="form.rules"
        @finishFailed="form.handleFinishFailed"
        @finish="form.onSubmit"
    >
      <slot></slot>
    </a-form>
  </Loading>
</template>

<script lang="ts">
import {defineComponent, PropType} from "vue";
import {FormUtilDto} from "/@/app/utils/formUtil/dto/FormUtilDto";
import {AForm} from "/@/components/antd";
import Loading from "/@/app/utils/component/Loading.vue";


export default defineComponent({
  name: "FormUtil",
  components: {AForm, Loading,},
  props: {
    form: {
      type: Object as PropType<FormUtilDto<any>>,
      required: true,
    },
  },
})
</script>

<style scoped>

</style>