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

@NgModule({
  declarations: [
    AppComponent,
    InclusaoBoletagemComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    LancamentosModule,
    PossoasModule,
    CoreModule,
    HttpClientModule
  ],
  providers: [LancamentoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
