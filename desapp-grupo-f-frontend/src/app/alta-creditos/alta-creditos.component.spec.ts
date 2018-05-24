import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AltaCreditosComponent } from './alta-creditos.component';

describe('AltaCreditosComponent', () => {
  let component: AltaCreditosComponent;
  let fixture: ComponentFixture<AltaCreditosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AltaCreditosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AltaCreditosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
