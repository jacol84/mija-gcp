<template>
  <UserForm :modalForm="modalForm" :time="modalForm.opening" :form-ext="formExt"></UserForm>
</template>

<script lang="ts">
import UserForm from "/@/app/user/view/UserForm.vue";
import {defineComponent, PropType} from "vue";
import {FormExt} from "/@/app/utils/formUtil/dto/FormUtilDto";
import {ModalForm} from "/@/app/test";

export default defineComponent({
  name: 'UserFormSup',
  props: {
    modalForm: {
      type: Object as PropType<ModalForm>,
    },
    formExt: {
      type: Object as PropType<FormExt>,
    }
  },
  components: {
    UserForm,
  },
});
</script>
