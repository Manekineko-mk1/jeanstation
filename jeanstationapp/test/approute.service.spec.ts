import { TestBed } from '@angular/core/testing';

import { ApprouteService } from '../src/app/services/approute.service';

describe('ApprouteService', () => {
  let service: ApprouteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApprouteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
