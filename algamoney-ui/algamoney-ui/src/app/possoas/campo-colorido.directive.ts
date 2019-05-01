import { Directive, HostListener, HostBinding, Input } from '@angular/core';

@Directive({
  selector: '[appCampoColorido]',
  exportAs: 'campoColorido'
})
export class CampoColoridoDirective {

  @Input() cor = 'gray';

  @HostBinding('style.backgroundColor') cordDeFundo: string;

  constructor() {}

  @HostListener('focus')
  colorir() {
    this.cordDeFundo = this.cor;
  }

  @HostListener('blur')
  descolorir() {
    this.cordDeFundo = 'transparent';
  }
}
