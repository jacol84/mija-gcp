import {Moment} from "moment";

export interface FormState {
    loading?: boolean,
    user: string;
    password: string;
    aDate: Moment;
}