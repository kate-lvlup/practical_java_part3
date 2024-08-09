package jom.com.softserve.s3.task6;

import java.util.*;

public class AddressBook implements Iterable<String> {
    private final Map<String, String> records;
    private final List<String> sortedRecords;
    private final int counter;
    private NameAddressPair[] addressBook;
    private int actualSize;

    public AddressBook(int capacity) {
        this.records = new HashMap<>();
        this.sortedRecords = new ArrayList<>();
        this.counter = capacity;
        this.addressBook = new NameAddressPair[capacity];
        this.actualSize = 0;
    }

    public boolean create(String firstName, String lastName, String address) {
        String key = generateKey(firstName, lastName);
        if (records.containsKey(key)) {
            return false;
        }
        ensureCapacity();
        records.put(key, address);
        sortedRecords.add(formatRecord(firstName, lastName, address));
        addressBook[actualSize++] = new NameAddressPair(new NameAddressPair.Person(firstName, lastName), address);
        return true;
    }

    public boolean delete(String firstName, String lastName) {
        String key = generateKey(firstName, lastName);
        if (!records.containsKey(key)) {
            return false;
        }

        records.remove(key);
        sortedRecords.removeIf(record -> record.startsWith(formatRecord(firstName, lastName, "")));

        for (int i = 0; i < actualSize; i++) {
            if (addressBook[i].person().getFirstName().equals(firstName) &&
                    addressBook[i].person().getLastName().equals(lastName)) {
                System.arraycopy(addressBook, i + 1, addressBook, i, actualSize - i - 1);
                addressBook[--actualSize] = null;
                return true;
            }
        }
        return false;
    }

    public boolean update(String firstName, String lastName, String newAddress) {
        String key = generateKey(firstName, lastName);
        if (!records.containsKey(key)) {
            return false;
        }

        records.put(key, newAddress);
        updateSortedRecords(firstName, lastName, newAddress);

        for (int i = 0; i < actualSize; i++) {
            if (addressBook[i].person().getFirstName().equals(firstName) &&
                    addressBook[i].person().getLastName().equals(lastName)) {
                addressBook[i] = new NameAddressPair(new NameAddressPair.Person(firstName, lastName), newAddress);
                return true;
            }
        }
        return false;
    }

    public String read(String firstName, String lastName) {
        return records.get(generateKey(firstName, lastName));
    }

    public int size() {
        return records.size();
    }

    public void sortedBy(SortOrder order) {
        sortedRecords.sort((s1, s2) -> {
            int cmp = s1.compareTo(s2);
            return order == SortOrder.ASC ? cmp : -cmp;
        });
    }

    @Override
    public Iterator<String> iterator() {
        return sortedRecords.iterator();
    }

    private void ensureCapacity() {
        if (actualSize >= addressBook.length) {
            addressBook = Arrays.copyOf(addressBook, addressBook.length * 2);
        }
    }

    private void updateSortedRecords(String firstName, String lastName, String newAddress) {
        for (int i = 0; i < sortedRecords.size(); i++) {
            String record = sortedRecords.get(i);
            if (record.startsWith(formatRecord(firstName, lastName, ""))) {
                sortedRecords.set(i, formatRecord(firstName, lastName, newAddress));
                break;
            }
        }
    }

    private String formatRecord(String firstName, String lastName, String address) {
        return String.format("First name: %s, Last name: %s, Address: %s", firstName, lastName, address);
    }

    private String generateKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    private class AddressBookIterator implements Iterator<String> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < actualSize;
        }

        @Override
        public String next() {
            NameAddressPair pair = addressBook[current++];
            return formatRecord(pair.person().getFirstName(), pair.person().getLastName(), pair.address());
        }
    }

    private record NameAddressPair(Person person, String address) {
        public static class Person {
            private final String firstName;
            private final String lastName;

            public Person(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }

            public String getFirstName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return firstName.equals(person.firstName) && lastName.equals(person.lastName);
            }

            @Override
            public int hashCode() {
                return Objects.hash(firstName, lastName);
            }
        }
    }
}
