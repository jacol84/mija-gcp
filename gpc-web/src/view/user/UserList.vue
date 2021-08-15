<template>
  <div>asa'{{ list }}'as</div>
  <a-table :columns="columns" :data-source="list">
    <template #name="{ text }">
      <a>
        <smile-outlined/>
        {{ text }}</a>
    </template>
    <template #action="{ record }">
      <span>
        <a>
          <a-tooltip>
            <template #title>Edytuj</template>
            <FormOutlined/>
          </a-tooltip>
        </a>
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
import {defineComponent, toRef} from 'vue';
import {ADivider, ATable, ATag, ATooltip} from "/@/components/antd";
import {UserDto} from "/@/view/test";

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
    list: Array<UserDto>
  },
  setup(props) {
    const list = toRef(props, 'list')
    return {
      list,
      columns
    };
  },
  components: {
    SmileOutlined,
    DownOutlined,
    FormOutlined,
    ATable,
    ATag,
    ADivider,
    ATooltip
  },
});
</script>