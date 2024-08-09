package jom.com.softserve.s3.task2;

import java.util.NoSuchElementException;

class NameList {
    private final String[] names = {"Mike", "Emily", "Nick", "Patric", "Sara"};

    public Iterator getIterator() {
        return new Iterator();
    }

    public class Iterator {
        private int counter = 0;

        private Iterator() {
        }

        public boolean hasNext() {
            return counter < names.length;
        }

        public String next() {
            if (hasNext()) {
                return names[counter++];
            } else {
                throw new NoSuchElementException("No more names in the list");
            }
        }
    }
}