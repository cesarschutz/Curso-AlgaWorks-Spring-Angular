import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register-operation',
  templateUrl: './register-operation.component.html',
  styleUrls: ['./register-operation.component.css']
})
export class RegisterOperationComponent implements OnInit {

  operation: Operation;
  listClients: Array<Client> = new Array();

  constructor() {
    this.operation = new Operation();

    let client = new Client();
    client.id = 1;
    client.name = "cliente 1";
    this.listClients.push(client);

    let client2 = new Client();
    client2.id = 2;
    client2.name = "cliente 2";
    this.listClients.push(client2);

    this.operation.client = client2;
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
  client: Client = new Client();
  manager: String;
  status: String;
}

export class Client {
  id: Number;
  name: String;
}