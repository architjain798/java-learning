package com.design.pattern.singleton.factory;

// Product interface
interface Document {
    void open();
    void save();
    void close();
}

// Concrete products
class PDFDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF document");
    }
    
    @Override
    public void save() {
        System.out.println("Saving PDF document");
    }
    
    @Override
    public void close() {
        System.out.println("Closing PDF document");
    }
}

class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word document");
    }
    
    @Override
    public void save() {
        System.out.println("Saving Word document");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Word document");
    }
}

// Creator abstract class with factory method
abstract class DocumentCreator {
    // Factory method
    public abstract Document createDocument();
    
    // Template method that uses the factory method
    public void editDocument() {
        Document doc = createDocument();
        doc.open();
        System.out.println("Editing document content...");
        doc.save();
        doc.close();
    }
}

// Concrete creators
class PDFDocumentCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}

class WordDocumentCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

// Client code
public class FactoryDesginPattern {
    public static void main(String[] args) {
        DocumentCreator pdfCreator = new PDFDocumentCreator();
        pdfCreator.editDocument();
        
        // System.out.println("-------------------");
        
        // DocumentCreator wordCreator = new WordDocumentCreator();
        // wordCreator.editDocument();
    }
}
