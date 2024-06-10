import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { BandaService } from '../../services/banda.service';
import { Banda } from '../../models/banda';

@Component({
  selector: 'app-music-modal',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './music-modal.component.html',
  styleUrl: './music-modal.component.css'
})
export class MusicModalComponent implements OnInit {
  nome = new FormControl('', [Validators.required]);
  descricao = new FormControl('', [Validators.required]);
  banda = new FormControl('', [Validators.required]);
  itensBanda = new Array<Banda>();
  @Output() onCreated = new EventEmitter();

  constructor(private bandaService: BandaService) { }

  ngOnInit(): void {
    this.bandaService.obterBandas().subscribe(response => {
      this.itensBanda = response;
    });
  }

  public criar() {
    let inputFile = document.getElementById("fileImg");

    var reader = new FileReader();
    reader.readAsDataURL((inputFile as any).files[0]);
    reader.onload = (result) => {
      let idBanda = this.banda.value;
      let base64Img = reader.result?.toString().replace(/^data:image\/?[A-z]*;base64,/, "");
      let request = {
        nome: this.nome.value,
        descricao: this.descricao.value,
        imagemBase64: base64Img
      }

      this.bandaService.criarMusica(request, idBanda as String).subscribe(response => {
        this.onCreated.emit();
        document.getElementById("btnClose")?.click();
      });

    };

  }

}
