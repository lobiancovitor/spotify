import { lista } from "./lista";

export interface Usuario {
    id:String;
    nome:String;
    email:String;
    senha:String;
    playlist: Array<lista>;
}