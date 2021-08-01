<template>
  <!--  <Button v-on:click="formState.test=!formState.test">Jest tu Mikołaj</Button>-->
  <!--  <AButton v-on:click="onClick"> BUTTON</AButton>-->
  <!--  <AButton v-on:click="diff">  BUTTON</AButton>-->
  <!--  <h1 v-if="formState.test">Vue is awesome! {{ formState.test }}</h1>-->
  <!--  <h2>Vue is awesome! {{ myData }}</h2>-->
  <!--  <h2 v-if="formState.test">Vue is awesome! {{ myCom }}</h2>-->
    <input v-model="myData"/>
  <!--  <UserFormXXX v-if="formState.test"></UserFormXXX>-->
  <a-input v-model:value="formState.search"></a-input>
  <AButton v-on:click="diff"> Search</AButton>
  <UserList></UserList>
</template>

<script lang="ts">

import {defineComponent, reactive, UnwrapRef} from "vue";
import {AButton, AInput} from "/@/components/antd";
import {createAsyncComponent} from "/@/utils/component/asyncComponent";
import {Button} from "ant-design-vue";
import ajax from "/@/utils/service/ajax/ajax";

interface StateModel {
  test: boolean,
  search: string
}

console.info(Button.name)

interface UserDto {
  d: number,
  name: string,
  lastName: string
}


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
    diff: function () {
      console.log(this.$data.myData)
      ajax.getJson<Array<UserDto>>("user").then(x => {
            const number = Math.floor(Math.random() * x.length);
            return this.$data.myData = x[number].lastName;
          }
      );
    }
  },
  setup() {
    const formState: UnwrapRef<StateModel> = reactive({
      search: "a",
      test: false
    });


    const onClick = function (values: StateModel) {
      console.log(values, formState);
    };
    return {
      formState,
      onClick
    }
  },
})


export default test
</script>

<style scoped>

</style>