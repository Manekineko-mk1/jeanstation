import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { AdminComponent } from './admin/admin.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { OrderManagementComponent } from './order-management/order-management.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserprofileComponent } from './userprofile/userprofile.component';
import { MyorderComponent } from './myorder/myorder.component';
import { PasswordComponent } from './password/password.component';
import { FiltersComponent } from './filters/filters.component';
import { CartItemComponent } from './cart/cart-item/cart-item.component';
import { ProductItemComponent } from './product/product-item/product-item.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    HomeComponent,
    ProductComponent,
    CartComponent,
    OrderComponent,
    AdminComponent,
    OrderManagementComponent,
    UserprofileComponent,
    MyorderComponent,
    PasswordComponent,
    FiltersComponent,
    CartItemComponent,
    ProductItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
