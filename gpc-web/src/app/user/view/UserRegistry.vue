<template>
  <div>
    <h1>Vue is awesome! {{ state.test }}</h1>
    <input v-model="state.myData"/>
    <a-input v-model:value="state.search" v-on:keypress.enter="handleSearch"></a-input>
    <a-button v-on:click="handleSearch"> Search</a-button>
    <a-button v-on:click="handleAdd">
      <template #icon>
        <PlusSquareOutlined/>
      </template>
      Add
    </a-button>
    <UserList :list="state.list" @openForm="openForm" :openFormXYZ="openForm"></UserList>

    <basic-modal title="Formularza" :modal-form="state.modalForm" :mija-visible="state.modalForm.visible" @cancel="closeForm">
      <user-form-sup :actionX="actionForm" :id="state.modalForm.id"></user-form-sup>
    </basic-modal>
  </div>
</template>

<script lang="ts">

import {defineComponent} from "vue";
import {PlusSquareOutlined} from '@ant-design/icons-vue';
import {AButton, AInput} from "/@/components/antd";
import {createAsyncComponent} from "/@/utils/component/asyncComponent";
import {userRegistryService} from "/@/app/user/service/UserRegistryService";
import BasicModal from "/@/utils/modal/BasicModal.vue";


const test = defineComponent({
  name: "Test",
  components: {
    Button: AButton,
    AInput: AInput,
    PlusSquareOutlined,
    AButton,
    BasicModal,
    UserFormSup: createAsyncComponent(() => import('/@/app/user/view/UserFormSup.vue')),
    UserList: createAsyncComponent(() => import('/@/app/user/view/UserList.vue')),
  },
  setup: function () {
    return {...userRegistryService()};
  },
})


export default test
</script>

<style scoped>

</style>