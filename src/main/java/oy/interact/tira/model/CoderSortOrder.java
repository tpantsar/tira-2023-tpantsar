package oy.interact.tira.model;

import java.util.Comparator;

public enum CoderSortOrder {
	FULLNAME_ASCENDING ("Full name (ascending)"),
	FULLNAME_DESCENDING ("Full name (descending)"),
	CODER_NAME_ASCENDING ("Coder name (ascending)"),
	CODER_NAME_DESCENDING ("Coder name (descending)");

	private String name;

	CoderSortOrder(final String name) {
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public static CoderSortOrder getOrder(String usingStringValue) {
		switch (usingStringValue) {
			case "Full name (ascending)":
				return FULLNAME_ASCENDING;
			case "Full name (descending)":
				return FULLNAME_DESCENDING;
			case "Coder name (ascending)":
				return CODER_NAME_ASCENDING;
			case "Coder name (descending)":
				return CODER_NAME_DESCENDING;
			default:
				return null;
		}
	}

	public boolean isReversed(CoderSortOrder another) {
		return ( 
			(this == FULLNAME_ASCENDING && another == FULLNAME_DESCENDING) || 
			(this == FULLNAME_DESCENDING && another == FULLNAME_ASCENDING) || 
			(this == CODER_NAME_ASCENDING && another == CODER_NAME_DESCENDING) || 
			(this == CODER_NAME_DESCENDING && another == CODER_NAME_ASCENDING)
		);
	}

	public static final String [] getNames() {
		return new String [] {
			FULLNAME_ASCENDING.getName(),
			FULLNAME_DESCENDING.getName(),
			CODER_NAME_ASCENDING.getName(),
			CODER_NAME_DESCENDING.getName()
		};
	}

	// TODO: Students: Implement Comparators for comparing Coder full names
	// and coder names as required, below, following the instructions in the task!

	public Comparator<Coder> getComparator() {
		return null;
	}
}
