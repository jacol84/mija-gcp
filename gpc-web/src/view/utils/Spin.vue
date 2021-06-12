<template>
  <a-row>
    <a-col class="center" :span="24">
      <ASpin></ASpin>
    </a-col>
  </a-row>
  <a-row>
    <a-col class="center" :span="24">
      {{ t('common.loadingText') }}
    </a-col>
  </a-row>
</template>

<script>

import {ASpin, ARow, ACol} from "/@/components/antd";
import {useI18n} from "/@/hooks/useI18n";

export default {
  name: "Spin",
  components: {
    ASpin, ARow, ACol
  },
  setup() {
    const {t} = useI18n();
    return {t}
  }
}


</script>

<style scoped>
.center {
  text-align: center;
}
</style>