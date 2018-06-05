import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostMapComponent } from './post-map.component';

describe('PostMapComponent', () => {
  let component: PostMapComponent;
  let fixture: ComponentFixture<PostMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
