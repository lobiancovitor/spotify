import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { BandaService } from '../../services/banda.service';
import { Banda } from '../../models/banda';

@Component({
  selector: 'app-filter',
  standalone: true,
  imports: [],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent implements OnInit {
  @Output()
  public filterAction = new EventEmitter<String>();

  banda: Array<Banda> = [];

  constructor(private bandaService: BandaService) { }

  ngOnInit(): void {
    this.bandaService.obterBandas().subscribe(response => {
      this.banda = response;
    });
  }

  public filter(id: String) {
    this.filterAction.emit(id);
  }
}
