import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetiroCreditosComponent } from './retiro-creditos.component';

describe('RetiroCreditosComponent', () => {
  let component: RetiroCreditosComponent;
  let fixture: ComponentFixture<RetiroCreditosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetiroCreditosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetiroCreditosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
