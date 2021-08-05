export interface Dto {
    formState: StateModel
}

export interface StateModel {
    test: boolean,
    search: string
}

export interface UserDto {
    id: number,
    name: string,
    lastName: string
}