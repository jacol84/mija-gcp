<template>
  <div>asa'{{ list }}'as</div>
  <a-table :columns="columns" :data-source="list" rowKey="id">
    <template #name="{ text }">
      <a>
        <smile-outlined/>
        {{ text }}</a>
    </template>
    <template #action="{ record }">
        <span>
          <a v-on:click="state.visible=!state.visible">
            <a-tooltip>
              <template #title>Edytuj</template>
              <FormOutlined/>
              Edytuj
            </a-tooltip>
          <a-modal v-model:visible="state.visible" title="Formularz" width="100%" wrapClassName="full-modal">
            <UserFormXXX></UserFormXXX>
          </a-modal>
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
</template>
<script lang="ts">
import {DownOutlined, FormOutlined, SmileOutlined} from '@ant-design/icons-vue';
import {defineComponent, reactive, toRef} from 'vue';
import {ADivider, AModal, ATable, ATag, ATooltip} from "/@/components/antd";
import {StateList} from "/@/view/test";
import {createAsyncComponent} from "/@/utils/component/asyncComponent";

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
      type: Array,
      default: []
    }
  },
  setup(props) {
    const state: StateList = reactive({visible: false});
    const list = toRef(props, 'list')
    return {
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
    AModal,
    ADivider,
    ATooltip
  },
});
</script>