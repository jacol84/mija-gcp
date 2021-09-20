<template>
  <Spin v-if="loading"></Spin>
  <slot v-if="!loading"/>
</template>

<script lang="ts">
import Spin from "/@/app/utils/component/Spin.vue";
import {defineComponent, PropType} from "vue";

const Loading = defineComponent({
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
export default Loading
</script>
