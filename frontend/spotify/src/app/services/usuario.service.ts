import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private url = `${environment.apiUrlBase}/users`;
  constructor(private http: HttpClient) { }

  public login(email:String, senha: String) : Observable<Usuario> {

    let body = {
      email: email,
      senha: senha
    };
    let options = {
      headers: environment.headers
    };

    return this.http.post<Usuario>(`${this.url}/login`, body, options);
  }

  public favoritar(id:String, idMusic: String) {
    let body = {  
    };
    let options = {
      headers: environment.headers
    };
    return this.http.post(`${this.url}/${id}/favoritar/${idMusic}`,body, options);
  }

  public obter(id:String) :Observable<Usuario> {
    let options = {
      headers: environment.headers
    };
    return this.http.get<Usuario>(`${this.url}/${id}`, options);
  }
}
