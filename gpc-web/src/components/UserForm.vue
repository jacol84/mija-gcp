<template>
  <a-form
      layout="inline"
      :model="formState"
      @finish="handleFinish"
      @finishFailed="handleFinishFailed"
  >
    <a-form-item>
      <a-date-picker v-model:value="value1" :locale="locale"/>
    </a-form-item>
    <a-form-item>
      <a-input v-model:value="formState.user" placeholder="Username">
        <template #prefix>
          <UserOutlined style="color: rgba(0, 0, 0, 0.25)"/>
        </template>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input v-model:value="formState.password" type="password" placeholder="Password">
        <template #prefix>
          <LockOutlined style="color: rgba(0, 0, 0, 0.25)"/>
        </template>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-button
          type="primary"
          html-type="submit"
          :disabled="formState.user === '' || formState.password === ''"
      >
        Log in
      </a-button>
    </a-form-item>
  </a-form>
</template>
<script lang="ts">

import {LockOutlined, UserOutlined} from '@ant-design/icons-vue';
// import {Button as AButton, Form as AForm, Input as AInput} from 'ant-design-vue';
import locale from 'ant-design-vue/es/date-picker/locale/pl_PL';
import {ValidateErrorEntity} from 'ant-design-vue/es/form/interface';
import {defineComponent, reactive, UnwrapRef} from 'vue';
import {AButton, AForm, AFormItem, AInput, ADatePicker} from "/@/components/antd";
// import {registerGlobComp} from "/@/components/registerGlobComp";
// registerGlobComp([Form,Row])

import 'moment/dist/locale/pl';

interface FormState {
  user: string;
  userA: string;
  userB: string;
  userC: string;
  password: string;
}


export default defineComponent({
  name: 'BasicForm',
  components: {
    UserOutlined,
    LockOutlined,
    AButton,
    AInput,
    AForm,
    AFormItem,
    ADatePicker,
  },
  setup() {
    const formState: UnwrapRef<FormState> = reactive({
      user: '',
      userA: '',
      userB: '',
      userC: '',
      password: '',
      value1:null,
    });
    const handleFinish = (values: FormState) => {
      console.log(values, formState);
    };
    const handleFinishFailed = (errors: ValidateErrorEntity<FormState>) => {
      console.log(errors);
    };
    return {
      formState,
      handleFinish,
      handleFinishFailed,
      locale
    };
  },
});
</script>