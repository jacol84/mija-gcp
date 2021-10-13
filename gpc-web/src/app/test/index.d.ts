import {Action} from "/@/utils/service/form/action";

export interface Dto {
    formState: StateModel
}

export interface ModalForm {
    opening?: number;
    id?: Number,
    action: Action,
}

export interface StateModel {
    modalForm: ModalForm,
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