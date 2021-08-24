<template>
  <a-modal v-model:visible="visible" title="Formularz" @cancel="cancel">
    <template #closeIcon>
      <div>
        <a-tooltip :title="'component.modal.restore'" placement="bottom" v-if="state.mode" v-on:click="onFull">
          <FullscreenExitOutlined role="full"/>
        </a-tooltip>
        <a-tooltip :title="'component.modal.maximize'" placement="bottom" v-else v-on:click="onFull">
          <FullscreenOutlined role="close"/>
        </a-tooltip>
        <a-tooltip :title="'component.modal.close'" placement="bottom">
          <CloseOutlined/>
        </a-tooltip>
      </div>
    </template>
    <slot></slot>
  </a-modal>
</template>

<script lang="ts">
import {AModal, ATooltip} from "/@/components/antd";
import {CloseOutlined, FullscreenExitOutlined, FullscreenOutlined} from "@ant-design/icons-vue";
import {defineComponent, reactive} from "vue";

export default defineComponent({
  name: "BasicModal",
  props: {
    visible: {type: Boolean, default: false},
    fullMode: {type: Boolean, default: false},
  },
  emits: ["cancel"],
  setup: (props) => {
    const fullMode = props.fullMode;
    const state = reactive({
      mode: fullMode
    });
    const onFull = (e: Event) => {
      e && e.stopPropagation();
      state.mode = !state.mode
    }

    return {state, onFull}
  },
  components: {
    FullscreenOutlined,
    FullscreenExitOutlined,
    CloseOutlined,
    ATooltip,
    AModal
  },
})
</script>

<style scoped>

</style>