package com.pontointeligente.controller;

import com.pontointeligente.controller.converter.Conversor;
import com.pontointeligente.controller.validator.PontoInteligenteValidator;

public class ControllerBase {
	
    public PontoInteligenteValidator validator = new PontoInteligenteValidator();
	
	
	public Conversor conversor = new Conversor();

}
