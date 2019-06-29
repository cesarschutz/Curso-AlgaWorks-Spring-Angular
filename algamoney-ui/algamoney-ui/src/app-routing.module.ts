import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListOperationsComponent } from './app/list-operations/list-operations.component';
import { RegisterOperationComponent } from './app/register-operation/register-operation.component';

const routes: Routes = [
  { path: '', redirectTo: '/list-operations', pathMatch: 'full' },
  { path: 'list-operations', component: ListOperationsComponent },
  { path: 'register-operation', component: RegisterOperationComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
