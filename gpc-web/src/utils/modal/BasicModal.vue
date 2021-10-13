<template>
  <a-modal :visible="dto.visible" :title="dto.title" @cancel="onClose" :footer="null" :style="dto.style">
    <template #closeIcon>
      <div>
        <a-tooltip :title="'component.modal.restore'" placement="bottom" v-if="dto.fullMode" v-on:click="onFull">
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
import {defineComponent, PropType} from "vue";
import {BasicModalDto} from "/@/utils/modal/BasicModalDto";

export default defineComponent({
  name: "BasicModal",
  props: {
    dto: {
      type: Object as PropType<BasicModalDto>,
      require: true
    },
  },
  setup: (props) => {
    const onFull = (e: Event) => {
      e && e.stopPropagation();
      if (props.dto) {
        props.dto.fullMode = !props.dto.fullMode
        if(props.dto.onFull){
          props.dto.onFull();
        }
      }
    }
    const onClose = () => {
      if (props.dto) {
        props.dto.visible = false;
        if(props.dto.onClose){
          props.dto.onClose()
        }
      }
    }
    return {onFull, onClose}
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