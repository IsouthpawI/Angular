import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CurrentDetailComponent} from "./current-detail/current-detail.component";
import {SearchMobilesComponent} from "./search-mobiles/search-mobiles.component";
import {CreateMobileComponent} from "./create-mobile/create-mobile.component";
import {MobileListComponent} from "./mobile-list/mobile-list.component";
import {CanDeactivateGuard} from "./guard/can-deactivate.guard";


const routes: Routes = [
  // {path: '', redirectTo: 'mobile', pathMatch: 'full'},
  {path: '', pathMatch: 'full', redirectTo: 'mobile'},
  // { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent },
  {path: 'mobile', component: MobileListComponent},
  {path: 'add', canDeactivate: [CanDeactivateGuard], component: CreateMobileComponent},
  {path: 'findbyfirstname', component: SearchMobilesComponent},
  {path: 'edit/:phonenumberm', component: CurrentDetailComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
