import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EjemploGmapsComponent } from './ejemplo-gmaps.component';

describe('EjemploGmapsComponent', () => {
  let component: EjemploGmapsComponent;
  let fixture: ComponentFixture<EjemploGmapsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EjemploGmapsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EjemploGmapsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
