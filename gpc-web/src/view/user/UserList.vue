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
    <a-modal v-model:visible="state.visible" title="Formularz" width="100%" wrapClassName="full-modal">
      <template #closeIcon>
        <div>
          <a-tooltip :title="'component.modal.restore'" placement="bottom">
            <FullscreenExitOutlined role="full"/>
          </a-tooltip>
          <a-tooltip :title="'component.modal.maximize'" placement="bottom">
            <FullscreenOutlined role="close"/>
          </a-tooltip>
          <a-tooltip :title="'component.modal.close'" placement="bottom">
            <CloseOutlined/>
          </a-tooltip>
        </div>
      </template>
      <UserFormXXX></UserFormXXX>

      <div>
        <a-tooltip :title="'component.modal.restore'" placement="bottom">
          <FullscreenExitOutlined role="full"/>
        </a-tooltip>
        <a-tooltip :title="'component.modal.maximize'" placement="bottom">
          <FullscreenOutlined role="close"/>
        </a-tooltip>
        <a-tooltip :title="'component.modal.close'" placement="bottom">
          <CloseOutlined/>
        </a-tooltip>
      </div>
    </a-modal>
  </div>
</template>
<script lang="ts">
import {DownOutlined, FormOutlined, SmileOutlined, FullscreenExitOutlined, FullscreenOutlined, CloseOutlined} from '@ant-design/icons-vue';
import {createVNode, defineComponent, reactive, toRef} from 'vue';
import {ADivider, AModal, ATable, ATag, ATooltip} from "/@/components/antd";
import {StateList, UserDto} from "/@/view/test";
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
      type: Array as () => Array<UserDto>,
      default: []
    }
  },
  setup(props) {
    const state: StateList = reactive({visible: false});
    // const [registerModal, { openModal }] = useModal();
    const list = toRef(props, 'list')
    const openForm = () => {
      state.visible = !state.visible;
      AModal.info({
        icon: undefined,
        title: 'This is a notification message',
        content: createVNode(createAsyncComponent(() => import('/@/view/user/UserFormXXX.vue'))),
        okButtonProps: {
          "disabled": true,
        },
        okText:undefined
      })
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
    AModal,
    ADivider,
    ATooltip,
    FullscreenExitOutlined,
    CloseOutlined,
    FullscreenOutlined
  },
});
</script>