import { Component, OnInit } from '@angular/core';
import { Banda } from '../models/banda';
import { CommonModule } from '@angular/common';
import { BandaService } from '../services/banda.service';
import { FilterComponent } from '../shared/filter/filter.component';
import { CardMusicComponent } from '../shared/card-music/card-music.component';
import { Musica } from '../models/musica';
import { take } from 'rxjs';
import { MusicModalComponent } from '../shared/music-modal/music-modal.component';
import { SearchComponent } from '../shared/search/search.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FilterComponent, CardMusicComponent, MusicModalComponent, SearchComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  musica = new Array<Musica>;

  constructor(private bandaService: BandaService) { }

  ngOnInit(): void {
    this.onFilter('ALL');
  }

  public reload() {
    this.onFilter('ALL');
 }

 public filterMusic($event:any) {
  let result = this.musica.filter(x => x.id == $event);
  this.musica = [];
  this.musica = result;
}

  public onFilter($event: any) {
    let id = $event;
    this.musica = [];
    console.log(this.musica)
    if (id !== 'ALL') {
      this.bandaService.obterMusicaBanda(id).pipe(take(1)).subscribe(response => {
        this.musica = response;
      });
    } else {
      this.bandaService.obterBandas().pipe(take(1)).subscribe(bandas => {
        for (const item of bandas) {
          this.musica.push(...item.musics);
        }
      });
    }

  }
}
