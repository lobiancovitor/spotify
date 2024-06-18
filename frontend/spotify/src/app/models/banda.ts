import { Musica } from "./musica";

export interface Banda {
    id:String;
    name:String;
    musics:Array<Musica>;
}