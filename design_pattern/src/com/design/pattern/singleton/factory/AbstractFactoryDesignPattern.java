package com.design.pattern.singleton.factory;

// Abstract product interfaces
interface Button {
    void render();

    void click();
}

interface TextField {
    void render();

    void getText();
}

// Concrete products for Windows
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a button in Windows style");
    }

    @Override
    public void click() {
        System.out.println("Windows button click");
    }
}

class WindowsTextField implements TextField {
    @Override
    public void render() {
        System.out.println("Rendering a text field in Windows style");
    }

    @Override
    public void getText() {
        System.out.println("Getting text from Windows text field");
    }
}

// Concrete products for macOS
class MacOSButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering a button in macOS style");
    }

    @Override
    public void click() {
        System.out.println("macOS button click");
    }
}

class MacOSTextField implements TextField {
    @Override
    public void render() {
        System.out.println("Rendering a text field in macOS style");
    }

    @Override
    public void getText() {
        System.out.println("Getting text from macOS text field");
    }
}

// Abstract factory interface
interface GUIFactory {
    Button createButton();

    TextField createTextField();
}

// Concrete factories
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public TextField createTextField() {
        return new WindowsTextField();
    }
}

class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public TextField createTextField() {
        return new MacOSTextField();
    }
}

// Client code
class Application {
    private Button button;
    private TextField textField;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        textField = factory.createTextField();
    }

    public void createUI() {
        button.render();
        textField.render();
    }

    public void buttonClick() {
        button.click();
    }

    public void getTextFromField() {
        textField.getText();
    }
}

public class AbstractFactoryDesignPattern {
    public static void main(String[] args) {
        // Determine which factory to use based on the operating system
        String osName = System.getProperty("os.name").toLowerCase();
        GUIFactory factory;

        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }

        Application app = new Application(factory);
        app.createUI();
        app.buttonClick();
        app.getTextFromField();
    }
}
