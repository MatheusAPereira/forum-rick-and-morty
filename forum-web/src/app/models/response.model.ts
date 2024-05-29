import { Info } from "./info.model";

export interface Response<T>{

     info: Info;
     items: T;
}