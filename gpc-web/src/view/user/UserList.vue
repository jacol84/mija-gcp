<template>
  <div>
    <div>asa'{{ list }}'as</div>
    <a-table :columns="columns" :data-source="list" rowKey="id">
      <template #name="{ text }">
        <a>
          <smile-outlined/>
          {{ text }}</a>
      </template>
      <template #action="{ record }">
        <span>
          <a v-on:click="openForm">
            <a-tooltip>
              <template #title>Edytuj2</template>
              <FormOutlined/>
              Edytuj2
            </a-tooltip>
          </a>
          <a-divider type="vertical"/>
          <a>Invite 一 {{ record.name }}</a>
          <a-divider type="vertical"/>
          <a>Delete</a>
          <a-divider type="vertical"/>
          <a class="ant-dropdown-link">
            More actions
            <down-outlined/>
          </a>
        </span>
      </template>
    </a-table>
    <BasicModal v-model:visible="state.visible">
      <UserFormXXX></UserFormXXX>
    </BasicModal>
  </div>
</template>
<script lang="ts">
import {CloseOutlined, DownOutlined, FormOutlined, FullscreenExitOutlined, FullscreenOutlined, SmileOutlined} from '@ant-design/icons-vue';
import {defineComponent, reactive, toRef} from 'vue';
import {ADivider, ATable, ATag, ATooltip} from "/@/components/antd";
import {StateList, UserDto} from "/@/view/test";
import {createAsyncComponent} from "/@/utils/component/asyncComponent";
import BasicModal from "/@/utils/modal/BasicModal.vue";

const columns = [
  {
    title: 'Id',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
    slots: {customRender: 'name'},
  },
  {
    title: 'Last name',
    dataIndex: 'lastName',
    key: 'last name',
  },
  {
    title: 'Action',
    key: 'action',
    slots: {customRender: 'action'},
  },
];


export default defineComponent({
  props: {
    list: {
      type: Array as () => Array<UserDto>,
      default: []
    }
  },
  setup(props) {
    const state: StateList = reactive({visible: false});
    const list = toRef(props, 'list')
    const openForm = () => {
      state.visible = !state.visible;
    }

    return {
      openForm,
      list,
      columns,
      state
    };
  },
  components: {
    UserFormXXX: createAsyncComponent(() => import('/@/view/user/UserFormXXX.vue')),
    SmileOutlined,
    DownOutlined,
    FormOutlined,
    ATable,
    ATag,
    ADivider,
    ATooltip,
    FullscreenExitOutlined,
    CloseOutlined,
    FullscreenOutlined,
    BasicModal,
  },
});
</script>