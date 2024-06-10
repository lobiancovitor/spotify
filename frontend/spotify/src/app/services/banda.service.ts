import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';
import { Banda } from '../models/banda';
import { Musica } from '../models/musica';

@Injectable({
  providedIn: 'root'
})
export class BandaService {

  private url = `${environment.apiUrlBase}/bands`;

  constructor(private http: HttpClient) { }

  public obterBandas() : Observable<Array<Banda>> {
    let options = {
      headers: environment.headers
    };
    return this.http.get<Array<Banda>>(`${this.url}`, options);
}

public obterMusicaBanda(id:String) : Observable<Array<Musica>> {
  let options = {
    headers: environment.headers
  };
  return this.http.get<Array<Musica>>(`${this.url}/${id}/music`, options);
}

public criarMusica(request:any, id:String = "89c4a77d-5d41-4c0b-9826-0941e3f68e3e") {
  let options = {
    headers: environment.headers
  };

  return this.http.post<Banda>(`${this.url}/${id}/carro`, request, options);
}

public autocomplete(searchText: String): Observable<any> {
  let options = {
    headers: environment.headers
  };
  return this.http.get(`${this.url}/autocomplete?search=${searchText}`, options);

}
}
