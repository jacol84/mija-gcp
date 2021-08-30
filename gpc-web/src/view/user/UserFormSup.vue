<template>
  <p>{{ id }} {{ action }} </p>
  <UserForm :id="id" :action="action"></UserForm>
</template>


<script lang="ts">
import UserForm from "/@/view/user/UserForm.vue";
import {defineComponent, PropType} from "vue";
import {Action} from "/@/utils/service/form/action";

export default defineComponent({
  name: 'UserFormSup',
  props: {
    action: {
      type: Number as PropType<Action>
    },
    id: {
      type: Number as PropType<Number | undefined>,
    }
  },
  components: {
    UserForm,
  },
  setup() {

    return {};
  },
});
</script>