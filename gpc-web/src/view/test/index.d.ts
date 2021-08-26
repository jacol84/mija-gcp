export interface Dto {
    formState: StateModel
}

export interface StateModel {
    test: boolean,
    search: string,
    myData: string,
    list: UserDto[] | undefined | null
}

export interface StateList {
    visible: boolean
    id: Number | undefined
}

export interface UserDto {
    id: number,
    name: string,
    lastName: string
}