import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {TabViewModule} from 'primeng/tabview';
import {ButtonModule} from 'primeng/button';

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    TabViewModule,
    ButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
