import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
// import createImportPlugin from 'vite-plugin-import';
// import antdImportPlugin from '@vitejs/plugin-antd-import';
// import antdViteImportPlugin from 'antd-vite-import-plugin';

const path = require('path')

// import ViteComponents, {AntDesignVueResolver} from 'vite-plugin-components'

// https://vitejs.dev/config/
export default defineConfig({
    server: {
        proxy: {
            '/api': {
                target: 'http://localhost:8080',
                changeOrigin: false,
                secure: false,
                // rewrite: (path) => {
                //     // debugger
                //     // console.log("aaa" + path)
                //     // const x = path.replace(/^\/api/, '')
                //     // console.log("zzz" + path +">>>"+ x)
                //     return path;
                // }
            },
        },
    },
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
    ],
    optimizeDeps: {
        // @iconify/iconify: The dependency is dynamically and virtually loaded by @purge-icons/generated, so it needs to be specified explicitly
        include: [
            // '@iconify/iconify',
            'ant-design-vue/es/locale/pl_PL',
            'moment/dist/locale/pl',
            'ant-design-vue/es/locale/en_US',
            'moment/dist/locale/eu',
        ],
        exclude: ['vue-demi'],
    },
})
