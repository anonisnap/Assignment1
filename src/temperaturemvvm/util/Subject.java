package temperaturemvvm.util;

import java.beans.PropertyChangeListener;

public interface Subject {
	void addListener(String name, PropertyChangeListener listener);
	void removeListener(String name, PropertyChangeListener listener);
}
