import { Directive, HostListener, HostBinding, Input } from '@angular/core';

@Directive({
  selector: '[appCampoColorido]'
})
export class CampoColoridoDirective {

  @Input() cor = 'gray';

  @HostBinding('style.backgroundColor') cordDeFundo: string;

  constructor() {}

  @HostListener('focus')
  aoGanharFoco() {
    this.cordDeFundo = this.cor;
  }

  @HostListener('blur')
  aoPerderFoco() {
    this.cordDeFundo = 'transparent';
  }
}
