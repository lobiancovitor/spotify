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

  public login(email:String, password: String) : Observable<Usuario> {

    let body = {
      email: email,
      password: password
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
    return this.http.post(`${this.url}/${id}/like/${idMusic}`,body, options);
  }

  public desfavoritar(id:String, idMusic: String) {
    let body = {  
    };
    let options = {
      headers: environment.headers
    };
    return this.http.post(`${this.url}/${id}/dislike/${idMusic}`,body, options);
  }

  public obter(id:String) :Observable<Usuario> {
    let options = {
      headers: environment.headers
    };
    return this.http.get<Usuario>(`${this.url}/${id}`, options);
  }
}
