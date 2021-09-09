import {Action} from "/@/utils/service/form/action";

export interface Dto {
    formState: StateModel
}

export interface ModalForm {
    visible?: boolean,
    id?: Number,
    action: Action,
}

export interface StateModel {
    form: ModalForm,
    test: boolean,
    search: string,
    myData: string,
    list: UserDto[] | undefined | null
}

export interface StateList {
    visible: boolean
    id?: Number
}

export interface UserDto {
    id: number,
    name: string,
    lastName: string
}