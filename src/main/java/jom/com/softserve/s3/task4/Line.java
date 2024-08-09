package jom.com.softserve.s3.task4;

public class Line {
    public static String drawLine(LineType lineType) {
        switch (lineType) {
            case SOLID:
                return "The line is solid type.";
            case DOTTED:
                return "The line is dotted type.";
            case DASHED:
                return "The line is dashed type.";
            case DOUBLE:
                return "The line is double type.";
            default:
                throw new IllegalArgumentException("Unknown line type: " + lineType);
        }
    }

}

