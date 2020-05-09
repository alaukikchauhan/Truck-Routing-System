import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { FpasswordComponent } from './fpassword/fpassword.component';
import { HomepageComponent } from './homepage/homepage.component';
import { TruckinfoComponent } from './truckinfo/truckinfo.component';


const routes: Routes = [
  {path: '', component : LoginComponent},
  {path: 'registration', component : RegistrationComponent},
  {path: 'forgot_password', component : FpasswordComponent},
  {path: 'homepage', component : HomepageComponent},
  {path: 'homepage/truckinfo', component : TruckinfoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
