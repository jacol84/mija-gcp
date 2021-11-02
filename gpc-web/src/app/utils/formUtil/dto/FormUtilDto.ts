export interface FormExt {
    close: Fn,
}

export interface FormUtilDto<U> {
    loading?: boolean,
    onSubmit: Fn,
    close: Fn,
    labelCol: { span: 24 },
    wrapperCol: { span: 24 },
    handleFinishFailed: Fn,
    formState: U,
    rules: any
}

export function makeDto<U>(formExt?: FormExt): FormUtilDto<U> {
    return {
        loading: true,
        labelCol: {span: 24},
        wrapperCol: {span: 24},
        formState: {},
        close: formExt?.close ? formExt?.close : emptyFunction,
        rules: {}
    } as FormUtilDto<U>
}