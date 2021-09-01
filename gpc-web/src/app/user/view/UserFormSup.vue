<template>
  <p>{{ id }} {{ actionX }} </p>
  <UserForm :id="id" :action="actionX"></UserForm>
</template>


<script lang="ts">
import UserForm from "/@/app/user/view/UserForm.vue";
import {defineComponent, PropType} from "vue";
import {Action} from "/@/utils/service/form/action";

export default defineComponent({
  name: 'UserFormSup',
  props: {
    actionX: {
      type: Number as PropType<Action>
    },
    id: {
      type: Number as PropType<Number | undefined>,
    }
  },
  components: {
    UserForm,
  },
  setup(p) {
    console.log(p.actionX, "a")
    return {};
  },
});
</script>