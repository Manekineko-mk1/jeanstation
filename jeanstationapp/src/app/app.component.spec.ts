import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { async, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { AdminComponent } from 'src/app/admin/admin.component';
import { CartComponent } from 'src/app/cart/cart.component';
import { FooterComponent } from 'src/app/footer/footer.component';
import { HeaderComponent } from 'src/app/header/header.component';
import { HomeComponent } from 'src/app/home/home.component';
import { LoginComponent } from 'src/app/login/login.component';
import { ProductComponent } from 'src/app/product/product.component';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { ToastrModule } from 'ngx-toastr';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        ReactiveFormsModule,
        CommonModule,
        BrowserAnimationsModule,
        // ToastrModule.forRoot({
        //   positionClass: 'toast-bottom-right',
        //   preventDuplicates: true,
        //   maxOpened: 1,
        //   closeButton: true,
        // }),
        HttpClientModule
      ],
      declarations: [
        AppComponent,
        HeaderComponent,
        FooterComponent,
        LoginComponent,
        HomeComponent,
        ProductComponent,
        CartComponent,
        AdminComponent
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'JeanStation'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('JeanStation');
  });
});
