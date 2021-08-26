import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';

describe('AuthenticationService', () => {
  let service: AuthenticationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers: [AuthenticationService]
    });
    service = TestBed.inject(AuthenticationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should have authenticate()', () => {
    expect(service.authenticate).toBeTruthy();
  });

  it('should have isUserLoggedIn()', () => {
    expect(service.isUserLoggedIn).toBeTruthy();
  });

  it('should have logOut()', () => {
    expect(service.logOut).toBeTruthy();
  });
});
