<template>
  <UserForm :id="id" :action="actionX" :form-ext="formExt"></UserForm>
</template>


<script lang="ts">
import UserForm from "/@/app/user/view/UserForm.vue";
import {defineComponent, PropType} from "vue";
import {Action} from "/@/utils/service/form/action";
import {FormExt} from "/@/app/utils/formUtil/dto/FormUtilDto";


export default defineComponent({
  name: 'UserFormSup',
  props: {
    actionX: {
      type: Number as PropType<Action>
    },
    id: {
      type: Number as PropType<Number | undefined>,
    },
    formExt: {
      type: Object as PropType<FormExt>,
    },
    idz: {
      type: Number as PropType<Number | undefined>,
    },
  },
  components: {
    UserForm,
  },
});
</script>
