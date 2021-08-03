<template>
  <!--  <Button v-on:click="formState.test=!formState.test">Jest tu Mikołaj</Button>-->
  <!--  <AButton v-on:click="onClick"> BUTTON</AButton>-->
  <!--  <AButton v-on:click="onSearch">  BUTTON</AButton>-->
  <h1 v-if="formState.test">Vue is awesome! {{ formState.test }}</h1>
  <!--  <h2>Vue is awesome! {{ myData }}</h2>-->
  <!--  <h2 v-if="formState.test">Vue is awesome! {{ myCom }}</h2>-->
  <input v-model="myData"/>
  <UserFormXXX v-if="formState.test"></UserFormXXX>
  <a-input v-model:value="formState.search" v-on:keypress.enter="onSearch"></a-input>
  <AButton v-on:click="onSearch"> Search</AButton>
  <UserList></UserList>
</template>

<script lang="ts">

import {defineComponent, reactive, UnwrapRef} from "vue";
import {AButton, AInput} from "/@/components/antd";
import {createAsyncComponent} from "/@/utils/component/asyncComponent";
import {Button} from "ant-design-vue";
import ajax from "/@/utils/service/ajax/ajax";
import {StateModel, UserDto} from "/@/view/test/index";

const test = defineComponent({
  name: "Test",
  components: {
    UserFormXXX: createAsyncComponent(() => import('/@/view/user/UserFormXXX.vue')),
    Button: Button,
    AInput: AInput,
    AButton,
    UserList: createAsyncComponent(() => import('/@/view/user/UserList.vue')),
  },
  data() {
    return {
      myData: "Jacek",
    };
  },
  computed: {
    myCom: () => "jacek" + new Date()
  },
  methods: {
    onSearch: function () {
      ajax.getJson<Array<UserDto>>("user").then(x => {
            const number = Math.floor(Math.random() * x.length);
            this.$data.myData = x[number].lastName;
            this.$data.myData = x[number].lastName;
          }
      );
    }
  },

  setup: function () {
    return {
      formState: reactive<UnwrapRef<StateModel>>({
        search: "a",
        test: false
      }),
    }

  },

})


export default test
</script>

<style scoped>

</style>