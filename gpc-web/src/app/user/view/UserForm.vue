<template>
  <div>{{formState.loading}}aaaaaaaaaa</div>
  <Loading v-model:loading="formState.loading">
    <a-form
        layout="inline"
        :model="formState"
        @finish="handleFinish"
        @finishFailed="handleFinishFailed"
    >
      <h1>{{ action }}</h1>
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
        <a-input-password v-model:value="formState.password" type="password" :placeholder="t('user.user.userName')">
          <template #prefix>
            <LockOutlined style="color: rgba(0, 0, 0, 0.25)"/>
          </template>
        </a-input-password>
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
  </Loading>

</template>


<script lang="ts">

import {LockOutlined, UserOutlined} from '@ant-design/icons-vue';
import locale from 'ant-design-vue/es/date-picker/locale/pl_PL';
import {ValidateErrorEntity} from 'ant-design-vue/es/form/interface';
import {defineComponent, PropType, reactive, toRefs, UnwrapRef} from 'vue';
import {AButton, ADatePicker, AForm, AFormItem, AInput, AInputPassword} from "/@/components/antd";
import moment from 'moment';

import 'moment/dist/locale/pl';
import {useI18n} from "/@/hooks/useI18n";
import {FormState} from "/@/app/user/dto";
import {Action} from "/@/utils/service/form/action";
import Loading from "/@/app/utils/Loading.vue";

export default defineComponent({
  name: 'UserForm',
  props: {
    action: {
      type: Number as PropType<Action>
    },
    id: {
      type: Number as PropType<Number | undefined>,
    }
  },
  components: {
    Loading,
    UserOutlined,
    LockOutlined,
    AButton,
    AInput,
    AForm,
    AFormItem,
    ADatePicker,
    AInputPassword,
  },
  setup(props) {
    const {t} = useI18n();
    const {action} = toRefs(props)
    const formState: UnwrapRef<FormState> = reactive({
      loading: false,
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
      ...props,
      action,
      t,
      formState,
      handleFinish,
      handleFinishFailed,
      locale,
      labelCol: {span: 4},
      wrapperCol: {span: 14},
    };
  },
});
</script>