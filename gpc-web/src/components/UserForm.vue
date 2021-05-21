<template>
  <div>
    <div>
      <label for="name">Nazwa</label>
      <input name="name" id="name" type="text" class="input" placeholder="Nazwa" v-model="name"/>
    </div>
    <div>
      <label for="lastName">Nazwisko</label>
      <input name="lastName" id="lastName" type="text" class="input" placeholder="Nazwisko" v-model="lastName"/>
    </div>
    <div>
      <label for="email">Email</label>
      <input name="email" id="email" type="text" class="input" placeholder="Email" v-model="email"/>
    </div>
    <button v-on:click="event.getJson" v-bind:disabled="name.length ===0">MY button</button>
    <div>{{ gson }}</div>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, ref, toRefs} from 'vue'

interface StateForm {
  email: string;
  name: string;
  lastName: string;
}

export default defineComponent({
  name: 'UserForm',

  setup: () => {
    const state = reactive<StateForm>({
      email: "string",
      name: "string",
      lastName: "string",
    });
    const gson = ref()
    const event = {
      getJson: () => {
        gson.value = new Date().getTime() + "  " + JSON.stringify(state)
      }
    }
    return {...toRefs(state), gson, event}
  }
})


</script>

<style scoped>

.input {
  width: 600px;
}

input[type='text'] {
  padding: 5px;
}

button {
  margin-top: 20px;
}
</style>

