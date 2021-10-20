import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {resolve} from "path";

export default defineConfig({
    build: {
        // generate manifest.json in outDir
        manifest: true,
    },
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
    resolve: {
        alias: [
            {
                find: /\/@\//,
                replacement: resolve(process.cwd(), '.', 'src') + '/',
            },
        ]
    },
    plugins: [
        vue(),
    ],
    optimizeDeps: {
        include: [
            'ant-design-vue/es/locale/pl_PL',
            'moment/dist/locale/pl',
            'ant-design-vue/es/locale/en_US',
            'moment/dist/locale/eu',
        ],
        exclude: ['vue-demi'],
    },
})
