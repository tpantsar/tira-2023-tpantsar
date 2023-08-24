package oy.interact.tira.model;

public interface PhoneBookModelObserver {
	void modelChanged();
	void selectionChanged(int item);
}
