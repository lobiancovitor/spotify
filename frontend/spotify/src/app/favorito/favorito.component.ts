import { Component, OnInit } from '@angular/core';
import { CardMusicComponent } from '../shared/card-music/card-music.component';
import { Musica } from '../models/musica';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-favorito',
  standalone: true,
  imports: [CardMusicComponent],
  templateUrl: './favorito.component.html',
  styleUrl: './favorito.component.css'
})
export class FavoritoComponent implements OnInit {
  musica = new Array<Musica>();
  user: any = undefined;
  constructor(private userService: UsuarioService) { }

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage["user_autenticated"]);

    this.userService.obter(this.user.id).subscribe(response => {
      for (const lista of response.playlist) {
        this.musica.push(...(lista as any).musics);
      }
    });


  }


}