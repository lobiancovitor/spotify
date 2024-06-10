import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MusicModalComponent } from './music-modal.component';

describe('MusicModalComponent', () => {
  let component: MusicModalComponent;
  let fixture: ComponentFixture<MusicModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MusicModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MusicModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
