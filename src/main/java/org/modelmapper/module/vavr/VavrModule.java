package org.modelmapper.module.vavr;

import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

/**
 * @author Julian Stücker
 */
public class VavrModule implements Module {

	@Override
	public void setupModule(ModelMapper modelMapper) {
		modelMapper.getConfiguration().getConverters().add(0, new SeqConverter());

	}
}
