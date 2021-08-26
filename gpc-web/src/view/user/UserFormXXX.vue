<template>
  {{id}}
  {{action}}
  <UserForm></UserForm>
</template>


<script lang="ts">

import UserForm from "/@/view/user/UserForm.vue";
import {defineComponent, PropType} from "vue";


export default defineComponent({
  name: 'BasicFormXXX',
  props: {
    action: {
      type: String,
      validator(value: string) {
        return ['NEW', 'EDIT'].includes(value)
      }
    },
    id: {
      type: Number as PropType<Number | undefined>,
    }
  },
  components: {
    UserForm,
  },
  setup() {
    return {}
  },
});
</script>