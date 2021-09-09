<template>
  <div>
    <a-table :columns="columns" :data-source="list" rowKey="id">
      <template #name="{ text }">
        <a>
          <smile-outlined/>
          {{ text }}</a>
      </template>
      <template #action="{ record }">
        <span>
          <a v-on:click="openForm(record)">
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
  </div>
</template>
<script lang="ts">
import {CloseOutlined, DownOutlined, FormOutlined, FullscreenExitOutlined, FullscreenOutlined, SmileOutlined} from '@ant-design/icons-vue';
import {defineComponent, PropType, reactive, toRef} from 'vue';
import {ADivider, ATable, ATag, ATooltip} from "/@/components/antd";
import {StateList, UserDto} from "/@/app/test";
import {createAsyncComponent} from "/@/utils/component/asyncComponent";
import BasicModal from "/@/utils/modal/BasicModal.vue";
import {Action} from "/@/utils/service/form/action";

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
    },
    openFormXYZ: {
      type: Function as PropType<(record?: UserDto) => undefined>,
      require: true,
    },
  },
  emits: ["openForm"],
  setup(props) {
    const {openFormXYZ} = props
    const state: StateList = reactive({visible: false});
    const list = toRef(props, 'list')

    const openForm = (record: UserDto) => {
      if (openFormXYZ != undefined) {
        openFormXYZ(record)
      }
    }
    return {
      openForm,
      list,
      columns,
      state,
    };
  },
  components: {
    UserFormSup: createAsyncComponent(() => import('/@/app/user/view/UserFormSup.vue')),
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