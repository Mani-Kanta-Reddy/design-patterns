package com.neon.lld.solid;

public class ISP {
    public static void main(String[] args)  {
        Printer oldFashionedPrinter = new PhotoCopier();
        oldFashionedPrinter.print();

    }
}


// BAD
//interface Printer {
//    void print();
//    void scan();
//    void fax() throws OperationNotSupportedException;
//}

//class OldFashionedPrinter implements Printer {
//    @Override
//    public void print() {
//        System.out.println("Old fashioned Printer printing logic...");
//    }
//
//    @Override
//    public void scan() {
//        System.out.println("Old fashioned printer can't support scanning...");
//    }
//
//    @Override
//    public void fax() {
//        System.out.println("Old fashioned printer doesn't support faxing...");
//    }
//}

//class NewPrinter implements Printer {
//    @Override
//    public void print() {
//        System.out.println("New Printer printing logic...");
//    }
//
//    @Override
//    public void scan() {
//        System.out.println("New printer scanning logic...");
//    }
//
//    @Override
//    public void fax() throws OperationNotSupportedException {
////        System.out.println("New Printer cannot support faxing...");
//        throw new OperationNotSupportedException("New Printer cannot support faxing...");
//    }
//}

//class PhotoCopier implements Printer {
//    @Override
//    public void print() {
//        System.out.println("PhotoCopier printing logic...");
//    }
//
//    @Override
//    public void scan() {
//        System.out.println("PhotoCopier scanning logic...");
//    }
//
//    @Override
//    public void fax() {
//        System.out.println("PhotoCopier faxing logic...");
//    }
//}

// GOOD
interface Printer {
    void print();
}

interface Scanner extends Printer{
    void scan();
}

interface MFD extends Scanner {
    void fax();
}

class OldFashionedPrinter implements Printer {
    @Override
    public void print() {
        System.out.println("Old Fashioned Printer printing logic...");
    }
}

class NewPrinter implements Scanner {
    @Override
    public void print() {
        System.out.println("New Printer printing logic...");
    }

    @Override
    public void scan() {
        System.out.println("New Printer scanning logic...");
    }
}

class PhotoCopier implements MFD {
    @Override
    public void print() {
        System.out.println("PhotoCopier printing logic...");
    }

    @Override
    public void scan() {
        System.out.println("PhotoCopier scanning logic...");
    }

    @Override
    public void fax() {
        System.out.println("PhotoCopier faxing logic...");
    }
}
