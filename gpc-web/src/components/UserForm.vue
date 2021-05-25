<template>
  <a-form
      layout="inline"
      :model="formState"
      @finish="handleFinish"
      @finishFailed="handleFinishFailed"
  >
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

import {UserOutlined, LockOutlined} from '@ant-design/icons-vue';
import {ValidateErrorEntity} from 'ant-design-vue/es/form/interface';
import {defineComponent, reactive, UnwrapRef} from 'vue';
import {Form, Row} from 'ant-design-vue';
// import {registerGlobComp} from "/@/components/registerGlobComp";
// registerGlobComp([Form,Row])
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
  },
  setup() {
    const formState: UnwrapRef<FormState> = reactive({
      user: '',
      userA: '',
      userB: '',
      userC: '',
      password: '',
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
    };
  },
});
</script>