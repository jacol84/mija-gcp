export interface Dto {
    formState: StateModel
}

export interface StateModel {
    test: boolean,
    search: string,
    myData: string,
    list: Array<UserDto>
}

export interface StateList {
    visible: boolean
}

export interface UserDto {
    id: number,
    name: string,
    lastName: string
}