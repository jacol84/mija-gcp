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


<script lang="ts">

import {ASpin, ARow, ACol} from "/@/components/antd";
import {useI18n} from "/@/hooks/useI18n";
import {defineComponent} from "vue";

export default defineComponent({
  name: "Spin",
  components: {
    ASpin, ARow, ACol
  },
  setup() {
    const {t} = useI18n();
    return {t}
  }
});


</script>

<style scoped>
.center {
  text-align: center;
}
</style>