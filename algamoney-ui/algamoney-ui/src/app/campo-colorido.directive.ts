import { Directive, HostListener, HostBinding } from '@angular/core';

@Directive({
  selector: '[appCampoColorido]'
})
export class CampoColoridoDirective {

  @HostBinding('style.backgroundColor') cordDeFundo: string;

  constructor() {}

  @HostListener('focus')
  aoGanharFoco() {
    this.cordDeFundo = 'yellow';
  }

  @HostListener('blur')
  aoPerderFoco() {
    this.cordDeFundo = 'transparent';
  }
}
