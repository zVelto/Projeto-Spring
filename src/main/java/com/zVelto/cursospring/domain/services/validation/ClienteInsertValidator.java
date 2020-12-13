package com.zVelto.cursospring.domain.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zVelto.cursospring.domain.enums.TipoCliente;
import com.zVelto.cursospring.domain.services.validation.utils.BR;
import com.zVelto.cursospring.dto.ClienteNewDTO;
import com.zVelto.cursospring.resources.exceptions.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		if(objDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())){
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())){
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
