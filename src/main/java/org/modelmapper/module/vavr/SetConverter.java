package org.modelmapper.module.vavr;

import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

import io.vavr.collection.Set;

/**
 * @author Julian Stücker
 */
public class SetConverter implements ConditionalConverter<Set, Set> {

	@Override
	public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
		return null;
	}

	@Override
	public Set convert(MappingContext<Set, Set> context) {
		return null;
	}
}
