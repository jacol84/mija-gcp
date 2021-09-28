import {Moment} from "moment";

export interface FormUserState {
    id?: number,
    name?: string,
    lastName?: string,
    aDate?: Moment;
}