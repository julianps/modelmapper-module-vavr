package org.modelmapper.module.vavr;

import org.modelmapper.internal.typetools.TypeResolver;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.PropertyInfo;

import io.vavr.collection.List;
import io.vavr.collection.Seq;

/**
 * @author Julian Stücker
 */
public class SeqConverter implements ConditionalConverter<Seq, Seq> {

	@Override
	public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
		if (List.class.isAssignableFrom(sourceType) &&
			List.class.isAssignableFrom(destinationType))
		{
			return MatchResult.FULL;
		}
		return MatchResult.NONE;
	}

	@Override
	public List convert(MappingContext<Seq, Seq> context) {
		List<?> source = (List<?>) context.getSource();
		PropertyInfo destInfo = context.getMapping().getLastDestinationProperty();
		Class<?> destinationType = TypeResolver
				.resolveRawArgument(destInfo.getGenericType(), destInfo.getInitialType());
		return source
				.map(src -> context.create(src, destinationType))
				.map(ctx -> context.getMappingEngine().map(ctx));
	}
}
