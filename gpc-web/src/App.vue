<template>
  <ConfigProvider :locale="locale">
    <img alt="Vue logo" src="./assets/logo.png"/>
    <Test/>
    <HelloWorld msg="Hello Vue 3 + TypeScript + Vite"/>
    <MyForm/>
  </ConfigProvider>
</template>

<script lang="ts">
import plPL from 'ant-design-vue/es/locale/pl_PL';
import {defineComponent} from 'vue'
import ConfigProvider from 'ant-design-vue/lib/config-provider';
import HelloWorld from '/@/components/HelloWorld.vue'
import MyForm from '/@/components/MyForm.vue'
import Test from '/@/app/user/view/UserRegistry.vue'

export default defineComponent({
  name: 'App',
  components: {
    ConfigProvider,
    HelloWorld,
    MyForm,
    Test
  },
  setup() {

    // let form = () => import("/@/components/MyForm.vue")

    return {locale: plPL};
  },
})
</script>

<style>
/*#app {*/
/*  font-family: Avenir, Helvetica, Arial, sans-serif;*/
/*  -webkit-font-smoothing: antialiased;*/
/*  -moz-osx-font-smoothing: grayscale;*/
/*  text-align: center;*/
/*  color: #2c3e50;*/
/*  margin-top: 60px;*/
/*}*/
</style>
