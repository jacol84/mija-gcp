<template>
  <UserForm></UserForm>
</template>


<script lang="ts">

import UserForm from "/@/view/user/UserForm.vue";
import {defineComponent} from "vue";


export default defineComponent({
  name: 'BasicFormXXX',
  components: {
    UserForm,
  },
  setup() {
   return {}
  },
});
</script>