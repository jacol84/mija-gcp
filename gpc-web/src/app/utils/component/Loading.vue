<template>
  <Spin :show="show">
    <slot></slot>
  </Spin>
</template>

<script lang="ts">
import Spin from "/@/app/utils/component/Spin.vue";
import {defineComponent, PropType} from "vue";
import {ASpin} from "/@/components/antd";

export default defineComponent({
  name: "Loading",
  props: {
    show: {
      type: Boolean as PropType<boolean>,
      default: true
    }
  },
  components: {
    Spin, ASpin
  },
});
</script>
