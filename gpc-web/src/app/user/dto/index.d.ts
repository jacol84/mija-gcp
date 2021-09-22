import {Moment} from "moment";

export interface FormState {
    id?: number,
    name?: string,
    lastName?: string,
    aDate?: Moment;
}