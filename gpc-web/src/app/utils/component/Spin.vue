<template>
  <a-row>
    <a-col class="center" :span="24">
      <ASpin :tip="label" :spinning="show">
        <slot></slot>
      </ASpin>
    </a-col>
  </a-row>
</template>


<script lang="ts">

import {ACol, ARow, ASpin} from "/@/components/antd";
import {useI18n} from "/@/hooks/useI18n";
import {defineComponent, PropType} from "vue";

export default defineComponent({
  name: "Spin",
  components: {
    ASpin, ARow, ACol
  },
  props: {
    show: {
      type: Boolean as PropType<boolean>
    }
  },
  setup() {
    const label = useI18n().t('common.loadingText')
    return {label}
  }
});


</script>

<style scoped>
.center {
  text-align: center;
}
</style>