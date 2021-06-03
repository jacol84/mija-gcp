import { genMessage } from '../helper';
import antdLocale from 'ant-design-vue/es/locale/pl_PL';
// import momentLocale from 'moment/dist/locale/pl';

const modules = import.meta.globEager('./pl/**/*.ts');
export default {
  message: {
    ...genMessage(modules, 'pl'),
    antdLocale,
  },
  // momentLocale,
  momentLocaleName: 'pl',
};
