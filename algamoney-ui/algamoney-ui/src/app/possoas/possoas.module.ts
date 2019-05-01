import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CampoColoridoDirective } from './campo-colorido.directive';
import { TooltipModule } from 'primeng/tooltip';
import { TableModule } from 'primeng/table';
import { InputMaskModule } from 'primeng/inputmask';
import { FormsModule } from '@angular/forms';
import { PessoasPesquisaComponent } from './pessoas-pesquisa/pessoas-pesquisa.component';
import { PessoaCadastroComponent } from './pessoa-cadastro/pessoa-cadastro.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoasGridComponent } from './pessoas-grid/pessoas-grid.component';

@NgModule({
  declarations: [
    PessoaCadastroComponent,
    PessoasGridComponent,
    PessoasPesquisaComponent,
    CampoColoridoDirective
  ],
  imports: [
    CommonModule,
    FormsModule,
    InputMaskModule,
    TableModule,
    TooltipModule,
    InputTextModule,
    ButtonModule
  ],
  exports: [
    PessoaCadastroComponent,
    PessoasPesquisaComponent
  ]
})
export class PossoasModule { }
