package com.sergiobonani.crudjavaionic.services.validation;

import com.sergiobonani.crudjavaionic.domain.Cliente;
import com.sergiobonani.crudjavaionic.domain.enums.TipoCliente;
import com.sergiobonani.crudjavaionic.dto.ClienteDTO;
import com.sergiobonani.crudjavaionic.dto.ClienteNewDTO;
import com.sergiobonani.crudjavaionic.repositories.ClienteRepository;
import com.sergiobonani.crudjavaionic.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		List<FieldMessage> list = new ArrayList<>();

		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)){
			list.add(new FieldMessage("email", "E-mail já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}