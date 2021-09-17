

export interface FormUtilDto<U> {
    loading?: boolean,
    handleFinish: Fn,
    handleFinishFailed: Fn,
    formState: U
}