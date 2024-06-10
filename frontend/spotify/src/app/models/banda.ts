import { Musica } from "./musica";

export interface Banda {
    musicas: any;
    id:String,
    nome:String;
    carros:Array<Musica>
}