<template>
  <FormUtil :form="formUtil">
    <a-form-item :label="labels.date">
      <a-date-picker v-model:value="formUtil.formState.aDate" :placeholder="labels.date" style="width: 100%"/>
    </a-form-item>
    <a-form-item :label="labels.name">
      <a-input v-model:value="formUtil.formState.name" :placeholder="labels.name"/>
    </a-form-item>
    <a-form-item :label="labels.lastName">
      <a-input v-model:value="formUtil.formState.lastName" :placeholder="labels.lastName"/>
    </a-form-item>
    <a-form-item>
      <a-button
          type="primary"
          html-type="submit"
          :disabled="!(formUtil.formState.name  || formUtil.formState.lastName) "
      >
        {{ labels.save }}
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

const {t} = useI18n();
const labels = {
  date: t('user.user.date'),
  name: t('user.user.name'),
  lastName: t('user.user.lastName'),
  save: t('user.user.save'),
}

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

    const {action, id} = toRefs(props)

    const formUtil = userFormService(id)

    formUtil.handleFinish = (values: FormState) => {
      console.log(values, formUtil);
    };
    formUtil.handleFinishFailed = (errors: ValidateErrorEntity<FormState>) => {
      console.log(errors);
    };
    return {
      ...props,
      action,
      labels,
      formUtil
    };
  },
});
</script>