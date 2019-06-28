import { HttpClientModule } from '@angular/common/http';
import { CoreModule } from './core/core.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { PossoasModule } from './possoas/possoas.module';
import { LancamentosModule } from './lancamentos/lancamentos.module';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LancamentoService } from './lancamentos/lancamento.service';
import { InclusaoBoletagemComponent } from './inclusao-boletagem/inclusao-boletagem.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { TesteReactiveFormsComponent } from './teste-reactive-forms/teste-reactive-forms.component';
import { RegisterOperationComponent } from './register-operation/register-operation.component';
import { ListOperationsComponent } from './list-operations/list-operations.component';

@NgModule({
  declarations: [
    AppComponent,
    InclusaoBoletagemComponent,
    TesteReactiveFormsComponent,
    RegisterOperationComponent,
    ListOperationsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    LancamentosModule,
    PossoasModule,
    CoreModule,
    HttpClientModule,
    ReactiveFormsModule, 
    FormsModule
  ],
  providers: [LancamentoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
