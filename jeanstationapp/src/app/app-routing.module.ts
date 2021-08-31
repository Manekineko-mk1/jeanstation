import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from './admin.guard';
import { AdminComponent } from './admin/admin.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MyorderComponent } from './myorder/myorder.component';
import { OrderManagementComponent } from './order-management/order-management.component';
import { OrderComponent } from './order/order.component';
import { PasswordComponent } from './password/password.component';
import { ProductComponent } from './product/product.component';
import { RegistrationComponent } from './registration/registration.component';
import { UserGuard } from './user.guard';
import { UserprofileComponent } from './userprofile/userprofile.component';

const routes: Routes = [
  {path: 'login', component:LoginComponent},
  {path: '', component:HomeComponent},
  {path: 'order', component:OrderComponent},
  {path: 'cart', component:CartComponent},
  {path: 'checkout', component:CheckoutComponent},
  {path: 'admin', component:AdminComponent, canActivate:[AdminGuard]},
  {path: 'product', component:ProductComponent},
  {path: 'order-management', component:OrderManagementComponent, canActivate:[AdminGuard]},
  {path: 'profile', component:UserprofileComponent, canActivate:[UserGuard]},
  {path: 'myorder', component:MyorderComponent, canActivate:[UserGuard]},
  {path: 'password', component:PasswordComponent, canActivate:[UserGuard]},
  {path: 'registration', component:RegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    scrollPositionRestoration: 'enabled'
})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
