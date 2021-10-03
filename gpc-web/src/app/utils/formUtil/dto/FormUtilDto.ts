export interface FormUtilDto<U> {
    loading?: boolean,
    onSubmit: Fn,
    close: Function,
    labelCol: { span: 24 },
    wrapperCol: { span: 24 },
    handleFinishFailed: Fn,
    formState: U
}

export function makeDto<U>(): FormUtilDto<U> {
    return {
        loading: true,
        labelCol: {span: 24},
        wrapperCol: {span: 24},
        formState:{}
    } as FormUtilDto<U>
}