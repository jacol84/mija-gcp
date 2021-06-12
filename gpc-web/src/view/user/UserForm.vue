<template>
  <a-form
      layout="inline"
      :model="formState"
      @finish="handleFinish"
      @finishFailed="handleFinishFailed"
  >
    <a-form-item>
      <a-date-picker v-model:value="formState.aDate"/>
    </a-form-item>
    <a-form-item>
      <a-input v-model:value="formState.user" :placeholder="t('user.user.userName')">
        <template #prefix>
          <UserOutlined style="color: rgba(0, 0, 0, 0.25)"/>
        </template>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input v-model:value="formState.password" type="password" :placeholder="t('user.user.userName')">
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
import locale from 'ant-design-vue/es/date-picker/locale/pl_PL';
import {ValidateErrorEntity} from 'ant-design-vue/es/form/interface';
import {defineComponent, reactive, UnwrapRef} from 'vue';
import {AButton, ADatePicker, AForm, AFormItem, AInput} from "/@/components/antd";
import moment, {Moment} from 'moment';

import 'moment/dist/locale/pl';
import {useI18n} from "/@/hooks/useI18n";


interface FormState {
  user: string;
  password: string;
  aDate: Moment;
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
    const {t} = useI18n();
    const formState: UnwrapRef<FormState> = reactive({
      user: '',
      password: '',
      aDate: moment(1622725402000),
    });
    const handleFinish = (values: FormState) => {
      console.log(values, formState);
    };
    const handleFinishFailed = (errors: ValidateErrorEntity<FormState>) => {
      console.log(errors);
    };
    return {
      t,
      formState,
      handleFinish,
      handleFinishFailed,
      locale
    };
  },
});
</script>