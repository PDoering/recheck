package de.retest.recheck.review.ignore.matcher;

import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.retest.recheck.review.ignore.io.RegexLoader;
import de.retest.recheck.ui.descriptors.Element;

public class ElementIdMatcher implements Matcher<Element> {

	private final String id;

	public ElementIdMatcher( final Element element ) {
		this( element.getIdentifyingAttributes().get( "id" ) != null
				? element.getIdentifyingAttributes().get( "id" ).toString() : "" );
	}

	public ElementIdMatcher( final String id ) {
		this.id = id;
	}

	@Override
	public boolean test( final Element element ) {
		final String elementId = element.getIdentifyingAttributes().get( "id" );
		return elementId != null && elementId.matches( id );
	}

	@Override
	public String toString() {
		return String.format( ElementIdMatcherLoader.FORMAT, id );
	}

	public static final class ElementIdMatcherLoader extends RegexLoader<ElementIdMatcher> {

		private static final String ID = "id=";

		private static final String FORMAT = ID + "%s";
		private static final Pattern REGEX = Pattern.compile( ID + "(.+)" );

		public ElementIdMatcherLoader() {
			super( REGEX );
		}

		@Override
		protected Optional<ElementIdMatcher> load( final MatchResult matcher ) {
			final String id = matcher.group( 1 );
			return Optional.of( new ElementIdMatcher( id ) );
		}
	}
}
