<template>
  <a-modal :visible="visible" :title="title" @cancel="onCancel" :footer="null">
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
import {computed, defineComponent, reactive} from "vue";

export default defineComponent({
  name: "BasicModal",
  props: {
    mijaVisible: {type: Boolean, default: false},
    title: {type: String, default: "Title"},
    fullMode: {type: Boolean, default: false},
  },
  emits: ["cancel", "onFull"],
  setup: (props, {emit}) => {
    const fullMode = props.fullMode;
    const state = reactive({
      mode: fullMode
    });
    const visible = computed(() => props.mijaVisible);
    const onFull = (e: Event) => {
      e && e.stopPropagation();
      state.mode = !state.mode
      emit("onFull", state.mode)
    }
    const onCancel = () => {
      emit("cancel")
    }
    return {visible, state, onFull, onCancel}
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