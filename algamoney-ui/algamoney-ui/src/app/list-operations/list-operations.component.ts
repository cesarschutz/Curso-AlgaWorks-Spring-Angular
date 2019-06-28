import { Component, OnInit } from '@angular/core';
import { Operation } from '../register-operation/register-operation.component';

@Component({
  selector: 'app-list-operations',
  templateUrl: './list-operations.component.html',
  styleUrls: ['./list-operations.component.css']
})
export class ListOperationsComponent implements OnInit {

  listOperations: Array<Operation> = new Array();

  constructor() {
    let operation = new Operation();
    operation.id = 1;
    operation.manager = "manger 1";
    operation.client = {name: "client 1", id: 1};
    operation.status = "Registered";
    this.listOperations.push(operation);

    let operation2 = new Operation();
    operation2.id = 2;
    operation2.manager = "manger 2";
    operation2.client = {name: "client 2", id: 2};
    operation2.status = "Confirmed";
    this.listOperations.push(operation2);
  }

  ngOnInit() {
    console.log("ddd");
    console.log(this.listOperations);
  }

}
