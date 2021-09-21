<template>
  <div>{{ formUtil.loading }}</div>
  <FormUtil :form="formUtil">
    <h1>{{ action }}</h1>
    <a-form-item>
      <a-date-picker v-model:value="formUtil.formState.aDate"/>
    </a-form-item>
    <a-form-item>
      <a-input v-model:value="formUtil.formState.name" :placeholder="t('user.user.name')"/>
    </a-form-item>
    <a-form-item>
      <a-input v-model:value="formUtil.formState.lastName" :placeholder="t('user.user.lastName')"/>
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
import {defineComponent, PropType, toRefs} from 'vue';
import {AButton, ADatePicker, AFormItem, AInput, AInputPassword} from "/@/components/antd";

import 'moment/dist/locale/pl';
import {useI18n} from "/@/hooks/useI18n";
import {FormState} from "/@/app/user/dto";
import {Action} from "/@/utils/service/form/action";
import FormUtil from "/@/app/utils/formUtil/view/FormUtil.vue";
import {userFormService} from "/@/app/user/service/UserFormService";

export default defineComponent({
  name: 'UserForm',
  props: {
    action: {
      type: Number as PropType<Action>
    },
    id: {
      type: Number as PropType<number | undefined>,
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

    const {handleSearch, formUtil} = userFormService()

    handleSearch(props.id || 0);

    formUtil.handleFinish = (values: FormState) => {
      console.log(values, formUtil);
    };
    formUtil.handleFinishFailed = (errors: ValidateErrorEntity<FormState>) => {
      console.log(errors);
    };
    return {
      ...props,
      action,
      t,
      formUtil,
      formState: formUtil.formState,
    };
  },
});
</script>