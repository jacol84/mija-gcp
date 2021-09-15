<template>
  <Spin v-if="loading"></Spin>
  <slot v-if="!loading"/>
</template>

<script lang="ts">
import Spin from "/@/app/utils/Spin.vue";
import {defineComponent, PropType} from "vue";

const loading = defineComponent({
  name: "Loading",
  props: {
    loading: {
      type: Boolean as PropType<boolean>
    }
  },
  components: {
    Spin,
  }
});
export default loading
</script>
