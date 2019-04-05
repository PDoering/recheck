package de.retest.recheck.report.action;

import de.retest.recheck.ui.actions.Action;
import de.retest.recheck.ui.descriptors.Element;

public class ActionReplayData {

	private final String description;
	private final Element element;
	private final String goldenMasterPath;

	private ActionReplayData( final String description, final Element element, final String goldenMasterPath ) {
		this.description = description;
		this.element = element;
		this.goldenMasterPath = goldenMasterPath;
	}

	public static ActionReplayData of( final Action action ) {
		if ( action != null ) {
			return withTarget( action.toString(), action.getTargetElement() );
		} else {
			return ofSutStart();
		}
	}

	public static ActionReplayData ofSutStart() {
		return withoutTarget( "Start Sut" );
	}

	public static ActionReplayData empty() {
		return new ActionReplayData( null, null, null );
	}

	public static ActionReplayData withTarget( final String description, final Element element ) {
		return withTarget( description, element, null );
	}

	public static ActionReplayData withTarget( final String description, final Element element,
			final String goldenMasterPath ) {
		return new ActionReplayData( description, element, goldenMasterPath );
	}

	public static ActionReplayData withoutTarget( final String description ) {
		return withoutTarget( description, null );
	}

	public static ActionReplayData withoutTarget( final String description, final String goldenMasterPath ) {
		return new ActionReplayData( description, null, goldenMasterPath );
	}

	public String getDescription() {
		return description;
	}

	public Element getElement() {
		return element;
	}

	public String getGoldenMasterPath() {
		return goldenMasterPath;
	}
}
