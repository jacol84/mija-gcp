<template>
  <div>{{ formUtil.loading }}</div>
  <FormUtil :form="formUtil">
    <h1>{{ action }}</h1>
    <a-form-item>
      <a-date-picker v-model:value="formUtil.formState.aDate"/>
    </a-form-item>
    <a-form-item>
      <a-input v-model:value="formUtil.formState.user" :placeholder="t('user.user.userName')">
        <template #prefix>
          <UserOutlined style="color: rgba(0, 0, 0, 0.25)"/>
        </template>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input-password v-model:value="formUtil.formState.password" type="password" :placeholder="t('user.user.userName')">
        <template #prefix>
          <LockOutlined style="color: rgba(0, 0, 0, 0.25)"/>
        </template>
      </a-input-password>
    </a-form-item>
    <a-form-item>
      <a-button
          type="primary"
          html-type="submit"
          :disabled="formUtil.formState.user === '' || formUtil.formState.password === ''"
      >
        Log in
      </a-button>
    </a-form-item>
  </FormUtil>

</template>


<script lang="ts">

import {LockOutlined, UserOutlined} from '@ant-design/icons-vue';
import {ValidateErrorEntity} from 'ant-design-vue/es/form/interface';
import {defineComponent, onUnmounted, onUpdated, PropType, reactive, toRefs, UnwrapRef} from 'vue';
import {AButton, ADatePicker, AFormItem, AInput, AInputPassword} from "/@/components/antd";
import moment from 'moment';

import 'moment/dist/locale/pl';
import {useI18n} from "/@/hooks/useI18n";
import {FormState} from "/@/app/user/dto";
import {Action} from "/@/utils/service/form/action";
import FormUtil from "/@/app/utils/formUtil/view/FormUtil.vue";
import {FormUtilDto} from "/@/app/utils/formUtil/dto/FormUtilDto";

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
    FormUtil,
    UserOutlined,
    LockOutlined,
    AButton,
    AInput,
    AFormItem,
    ADatePicker,
    AInputPassword,
  },
  setup(props) {
    const {t} = useI18n();
    const {action} = toRefs(props)

    const formUtil: UnwrapRef<FormUtilDto<FormState>> = reactive({
      loading: true,
      formState: {
        user: '',
        password: '',
        aDate: moment(1622725402000),
      } as FormState
    } as FormUtilDto<FormState>)

    // onUnmounted(() => {
    //   alert("111")
    // })
    // onUpdated(() => {
    //   alert("222")
    // })

    setTimeout(() => {
      formUtil.loading = false;
    }, 5000)

    formUtil.handleFinish = (values: FormState) => {
      console.log(values, formState);
    };
    formUtil.handleFinishFailed = (errors: ValidateErrorEntity<FormState>) => {
      console.log(errors);
    };
    return {
      ...props,
      action,
      t,
      formUtil
    };
  },
});
</script>