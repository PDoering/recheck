package de.retest.recheck.printer;

import java.util.stream.Collectors;

import de.retest.recheck.ignore.ShouldIgnore;
import de.retest.recheck.ui.DefaultValueFinder;
import de.retest.recheck.ui.descriptors.IdentifyingAttributes;
import de.retest.recheck.ui.diff.ElementDifference;

public class ElementDifferencePrinter implements Printer<ElementDifference> {

	private final DefaultValueFinder finder;
	private final ShouldIgnore ignore;

	public ElementDifferencePrinter( final DefaultValueFinder finder, final ShouldIgnore ignore ) {
		this.finder = finder;
		this.ignore = ignore;
	}

	@Override
	public String toString( final ElementDifference difference, final String indent ) {
		return formatElementDifference( difference, indent );
	}

	private String formatElementDifference( final ElementDifference difference, final String indent ) {
		final IdentifyingAttributes id = difference.getIdentifyingAttributes();
		final String prefix = String.format( "%s%s at '%s':%n", indent, id, id.getPath() );
		return prefix + to( difference, indent + "\t" );
	}

	private String to( final ElementDifference difference, final String indent ) {
		final PrinterValueProvider defaults = PrinterValueProvider.of( finder, difference.getIdentifyingAttributes() );
		final AttributeDifferencePrinter delegate = new AttributeDifferencePrinter( defaults );
		return difference.getAttributeDifferences( ignore ).stream() //
				.map( d -> delegate.toString( d, indent ) ) //
				.collect( Collectors.joining( "\n" ) );
	}
}
