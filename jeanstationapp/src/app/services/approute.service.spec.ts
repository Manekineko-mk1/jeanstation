import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { ApprouteService } from './approute.service';

describe('ApprouteService', () => {
  let router:ApprouteService;
  beforeEach(() => {
    
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule, RouterTestingModule],
      providers: [ApprouteService]
    });
    router = TestBed.get(ApprouteService);
  });

  it('should be created', () => {
    expect(router).toBeTruthy();
  });
});
