import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  nome = "Vitor";
  incremento = 0;

  public getNome() {
    return this.nome;
  }

  public somar() {
    this.incremento++;
  }
}
