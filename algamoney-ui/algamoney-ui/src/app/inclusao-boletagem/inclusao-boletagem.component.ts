import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Usuario } from '../teste-reactive-forms/teste-reactive-forms.component';

@Component({
  selector: 'app-inclusao-boletagem',
  templateUrl: './inclusao-boletagem.component.html',
  styleUrls: ['./inclusao-boletagem.component.css']
})
export class InclusaoBoletagemComponent implements OnInit {

  formularioDeUsuario: FormGroup;
  
  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.criarFormularioDeUsuario();
  }

  enviarDados() {
    const dadosFormulario = this.formularioDeUsuario.value;

    const usuario = new Usuario(
      dadosFormulario.nome,
      dadosFormulario.email,
      dadosFormulario.cpf,
      dadosFormulario.nascimento,
      dadosFormulario.senha
    );

    alert(`O usuário ${usuario.nome} foi cadastrado com sucesso. \n Dados: ${JSON.stringify(usuario)}`);

    this.formularioDeUsuario.reset();
  }

  criarFormularioDeUsuario() {
    this.formularioDeUsuario = this.fb.group(
      {
        cliente: [
          '',
          Validators.compose([
            Validators.required,
            Validators.minLength(3),
            Validators.maxLength(100)
          ])
        ],
        gerente: [
          '',
          Validators.compose([
            Validators.required,
            Validators.minLength(3),
            Validators.maxLength(100)
          ])
        ]
      }
    );
  }

  // Propriedades do formulário que vamos utilizar para obter os erros
  get cliente() {
    return this.formularioDeUsuario.get('cliente');
  }

  get gerente() {
    return this.formularioDeUsuario.get('gerente');
  }
}
