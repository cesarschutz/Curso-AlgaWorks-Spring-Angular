package com.algamoney.api.exceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class AlgaMoneyExceptionHanlder extends ResponseEntityExceptionHandler {

	@Autowired
	MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagemUsuario = "Campo não reconhecido: ";
		
		if(ex.getCause() instanceof UnrecognizedPropertyException) {
			UnrecognizedPropertyException cause = (UnrecognizedPropertyException) ex.getCause();
			
			mensagemUsuario += cause.getPropertyName();
			
			Collection<Object> _propertyIds = cause.getKnownPropertyIds();
			 StringBuilder sb = new StringBuilder(100);
	            int len = _propertyIds.size();
	            if (len == 1) {
	                sb.append(" (1 propriedade conhecida: '");
	                sb.append(String.valueOf(_propertyIds.iterator().next()));
	            } else {
	                sb.append(" (").append(len).append(" Propriedades conhecidas: [");
	                Iterator<Object> it = _propertyIds.iterator();
	                while (it.hasNext()) {
	                    sb.append(String.valueOf(it.next()));
	                    if (it.hasNext()) {
	                        sb.append(", ");
	                    }
	                }
	                sb.append("])");
	            }
	            
	            mensagemUsuario += sb.toString();
		} else {
			mensagemUsuario = ex.toString();
		}
		
		
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return super.handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		return super.handleExceptionInternal(ex, criarListaDeErros(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Erro> criarListaDeErros(BindingResult bidingResult){
		List<Erro> erros = new ArrayList<>();
		
		for (FieldError fieldEror : bidingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fieldEror, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldEror.toString();
			erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		}
		
		return erros;
	}
	

	public static class Erro {
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;

		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
		
		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
	}
}
