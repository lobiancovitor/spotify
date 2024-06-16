import { Component, EventEmitter, Output } from '@angular/core';
import {AutocompleteLibModule} from 'angular-ng-autocomplete';

import { take } from 'rxjs';
import { BandaService } from '../../services/banda.service';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [AutocompleteLibModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  data = [];
  keyword = 'nome_musicas';
  @Output() onSelectedItem = new EventEmitter<String>();
  @Output() onClearedItem = new EventEmitter();
  constructor(private bandaService: BandaService) {}

  onChangeSearch(text: string) {
    this.bandaService.autocomplete(text).pipe(take(1)).toPromise().then(response => {
      this.data = response;
      console.log(this.data);
    });

  }

  onCleared(){
    this.data = [];
    this.onClearedItem.emit();
  }

  selectEvent($event: any) {
    this.onSelectedItem.emit($event.id);
  }
}
