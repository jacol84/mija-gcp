import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
// import createImportPlugin from 'vite-plugin-import';
// import antdImportPlugin from '@vitejs/plugin-antd-import';
// import antdViteImportPlugin from 'antd-vite-import-plugin';

const path = require('path')

// import ViteComponents, {AntDesignVueResolver} from 'vite-plugin-components'

// https://vitejs.dev/config/
export default defineConfig({
    resolve:{
        alias: [
            {
                find: '/@',
                replacement: path.resolve(__dirname,'/src')
            }
        ]
    },
    plugins: [
        vue(),
        // createImportPlugin([
        //     {
        //         libraryName: 'antd',
        //         'style': true,   // or 'css'
        //     }
        // ]),
        // antdViteImportPlugin(),
    ]
})
