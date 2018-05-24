import { TestBed, inject } from '@angular/core/testing';

import { SharedSearchFilterService } from './shared-search-filter.service';

describe('SharedSearchFilterService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SharedSearchFilterService]
    });
  });

  it('should be created', inject([SharedSearchFilterService], (service: SharedSearchFilterService) => {
    expect(service).toBeTruthy();
  }));
});
