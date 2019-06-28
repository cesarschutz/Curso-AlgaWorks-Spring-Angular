import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register-operation',
  templateUrl: './register-operation.component.html',
  styleUrls: ['./register-operation.component.css']
})
export class RegisterOperationComponent implements OnInit {

  operation: Operation;

  constructor() {
    this.operation = new Operation();
  }

  ngOnInit() {
  }

  register(){
    this.operation.id = 1;
    alert(JSON.stringify(this.operation));
  }

}

export class Operation {
  id: Number;
  client: {
    id: Number;
    name: String;
  };
  manager: String;
  status: String;
}