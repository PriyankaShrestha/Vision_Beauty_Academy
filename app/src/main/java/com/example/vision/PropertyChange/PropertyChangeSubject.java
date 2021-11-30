package com.example.vision.PropertyChange;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject {
    void addPropertyChangeListener(String name, PropertyChangeListener listener);
    void removerPropertyChangeListener(String name, PropertyChangeListener listener);
}
