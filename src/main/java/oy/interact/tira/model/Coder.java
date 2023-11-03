package oy.interact.tira.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Comparable.html#compareTo(T)
 * It is strongly recommended, but not strictly required that (x.compareTo(y)==0) == (x.equals(y)).
 * Generally speaking, any class that implements the Comparable interface and violates this condition
 * should clearly indicate this fact. The recommended language is "Note: this class has a natural
 * ordering that is inconsistent with equals."
 */
public class Coder implements Comparable<Coder> {
	private String id;
	private String lastName;
	private String firstName;
	private String coderName;
	private String phoneNumber;

	/**
	 * Only to be used when reading objects from JSON or other
	 * storage/source. When creating new Coders, id should be
	 * randomly generated by using the other constructor.
	 * @param id The already created ID for the Coder.
	 */
	public Coder(String id) {
		this.id = id;
	}

	public Coder(String firstName, String lastName, String phoneNumber) {
		id = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public String getCoderName() {
		if (null != coderName) {
			return coderName;
		}
		return "";
	}

	public void setCoderName(String coderName) {
		this.coderName = coderName;
	}

	public String getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFullName() {
		return lastName + " " + firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private Set<String> languages;
	private Set<String> friendIDs;

	public boolean hasLanguages() {
		if (null != languages) {
			return !languages.isEmpty();
		}
		return false;
	}

	public void addLanguage(String language) {
		if (null == languages) {
			languages = new HashSet<>();
		}
		languages.add(language);
	}

	public Set<String> getLanguages() {
		return languages;
	}

	public String getLanguagesString() {
		if (null != languages && !languages.isEmpty()) {
			StringBuilder builder = new StringBuilder();
			int counter = 0;
			for (String language : languages) {
				builder.append(language);
				if (counter < languages.size() - 1) {
					builder.append(", ");
				}
				counter++;
			}
			return builder.toString();
		}
		return "";
	}

	public boolean knowsLanguage(String language) {
		if (null != languages) {
			return languages.contains(language);
		}
		return false;
	}

	public boolean hasFriends() {
		if (null != friendIDs) {
			return !friendIDs.isEmpty();
		}
		return false;
	}

	public void addFriend(String friendID) {
		if (null == friendIDs) {
			friendIDs = new HashSet<>();
		}
		friendIDs.add(friendID);
	}

	public Set<String> getFriendIDs() {
		return friendIDs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(lastName);
		builder.append(" ");
		builder.append(firstName);
		builder.append(" (");
		builder.append(coderName);
		builder.append(")");
		return builder.toString();
	}

	/**
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 * 
	 * Equals compares the equality of the Coder.id, as compareTo compares
	 * the Coder full names. This is to make sure we can hold several coders
	 * in containers having the same name (as happens in real world), but still
	 * can identify them as different Coders.
	 */
	@Override
	public boolean equals(Object another) {
		if (this == another) {
			return true;
		}
		if (null == another) {
			return false;
		}
		if (another.getClass() != this.getClass()) {
			return false;
		}
		return id.equals(((Coder) another).id);
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	// TODO: Students: implement the two methods below following the instructions _carefully_!
	// Expecially see the note in the class comment above, and the comments below.
	////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 * 
	 * Equals compares the equality of the Coder.id, as compareTo compares
	 * the Coder full names. This is to make sure we can hold several coders
	 * in containers having the same name (as happens in real world), but still
	 * can identify them as different Coders using the Coder.id.
	 * 
	 * Implement compareTo so that the order of coders ordered by using this method
	 * will be natural order. Meaning, alphabetical order A...Ö (by lastname-firstname order).
	 */
	@Override
	public int compareTo(Coder another) {
		String thisCoder = this.toString();
		String anotherCoder = another.toString();
		int lengthOfShorterString = getLengthOfShorterString(thisCoder, anotherCoder);

		// Compare coder characters one by one: lastName -> firstName -> coderName
		for (int i = 0; i < lengthOfShorterString; i++) {
			if (thisCoder.charAt(i) < anotherCoder.charAt(i)) {
				return -1;
			}
			if (thisCoder.charAt(i) > anotherCoder.charAt(i)) {
				return 1;
			}
		}

		// If coder names are partially same but other is longer eg.
		// "coderNameStringExample" vs. "coderNameStringExampleLonger"
		if (thisCoder.length() < anotherCoder.length()) {
			// "".compareTo("a") = -1
			return -1;
		} else if (thisCoder.length() > anotherCoder.length()) {
			// "a".compareTo("") = 1
			return 1;
		}

		// return 0 if Coder objects are exactly the same (lastName, firstName and coderName)
		return 0;
	}

	public int getLengthOfShorterString(String str1, String str2) {
		if (str1.length() < str2.length()) {
			return str1.length();
		} else {
			return str2.length();
		}
	}

	/**
	 * You need to implement this in Exercise 8 on hash tables. No need to implement
	 * this before!
	 * 
	 * TODO: Students (task 8): Calculate the hash for the Coder. In this case, the
	 * hash must be related
	 * to the unique identity of the coder. Since coders can have a same full name,
	 * calculate the hash from the permanent id of the Coder, which does not change.
	 * 
	 * @return The hash calculated from the id of the Coder.
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		int keyLength = this.id.length();

		for (int i = 0; i < keyLength; i++) {
			int charAsInt = this.id.charAt(i);
			hash = hash * 31 + (charAsInt & 0xff);
		}

		/*
		"hashCode():n toteutus ei saa rajoittaa palautettavan kokonaisluvun arvoa millään tavoin;
		sen pitää voida olla erittäin suuri tai pieni positiivinen tai negatiivinen kokonaisluku.
		Tämä luku rajataan taulukon kokoon vasta hajautustaulu -tietorakenteessa."
		*/
		return hash;
	}

}
