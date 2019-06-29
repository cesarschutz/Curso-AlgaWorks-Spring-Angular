import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-operation',
  templateUrl: './register-operation.component.html',
  styleUrls: ['./register-operation.component.css']
})
export class RegisterOperationComponent implements OnInit {

  operation: Operation;
  listClients: Array<Client> = new Array();

  constructor(private router: Router) {
    //carrega clientes combobox
    let client = new Client();
    client.id = 1;
    client.name = "client 1";
    this.listClients.push(client);
    let client2 = new Client();
    client2.id = 2;
    client2.name = "client 2";
    this.listClients.push(client2);

    this.loadOperationParam();
  }

  ngOnInit() {
    
  }

  loadOperationParam() {
    //load operation
    debugger;
    const nav = this.router.getCurrentNavigation();
    let operationParam = nav.extras.state.operation;
    this.operation = operationParam ? operationParam : new Operation();
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