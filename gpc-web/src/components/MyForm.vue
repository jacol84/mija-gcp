<template>
  <div>
    <input ref="el" type="text" placeholder="Name" v-model="name"/>
    <div>
      <button v-on:click="submitForm" v-bind:disabled="name.length ===0">Submit</button>
    </div>
    <div>
      <input type="text" class="input" placeholder="Url" v-model="url"/>
      <div>{{ gson }}</div>
      <button v-on:click="getAjax" v-bind:disabled="name.length ===0">Get google fetch json</button>
    </div>
  </div>
</template>

<script lang="ts">
// import {fetch} from 'cross-fetch';
import {defineComponent, onMounted, ref} from 'vue'

export default defineComponent({
  name: 'MyForme',

  setup: () => {
    const name = ref('name')
    const url = ref("http://localhost:8080/json/gson")
    // const url = ref("json/gson")
    const gson = ref('waiting ...')
    const el = ref()

    const submitForm = () => {
      console.log(`Form submitted! Name = ${name.value}`)
    }
    let myIndex: number = 0;
    const getAjax = () => {
      const requestHeaders: HeadersInit = new Headers();
      requestHeaders.set('Content-Type', 'application/json');
      // requestHeaders.set('Access-Control-Allow-Origin', '*');
      // requestHeaders.set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
      // requestHeaders.set('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS');
      // requestHeaders.set("Sec-Fetch-Mode", "no-cors");
      // requestHeaders.set("mode", "no-cors");
      // requestHeaders.set("DNT", "1");


      fetch(url.value, {
            method: 'GET', // *GET, POST, PUT, DELETE, etc.
            // mode: 'no-cors', // no-cors, *cors, same-origin
            // mode: 'no-cors', // no-cors, *cors, same-origin
            // cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
            // credentials: 'include', // include, *same-origin, omit
            headers: requestHeaders,
            // redirect: 'follow', // manual, *follow, error
            // referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            // body: JSON.stringify(data)
        }

        ).then(response => response.json())
          .catch(rej => {
                console.log(rej.message);
                throw rej;
              }
          )
          .then(data => gson.value = data)
          .catch(rej => {
            const mes = rej.message
            const index = myIndex++;
            gson.value = mes
            setTimeout(function () {
              if (index === myIndex && mes === gson.value) {
                gson.value = "waiting ..."
              } else {
                console.log("request " + index)
              }
            }, 3000);
          });
    }

    onMounted(() => {
      let value = el.value;
      console.log()
      value.focus()
    });

    return {gson, url, name, submitForm, el, getAjax,}
  }
})


</script>

<style>

.input {
  width: 1000px;
}

input[type='text'] {
  padding: 5px;
}

button {
  margin-top: 20px;
}
</style>

