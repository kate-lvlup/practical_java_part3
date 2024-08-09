//package jom.com.softserve.s3.task6;
//
//import java.util.*;
//
//public class AddressBook implements Iterable<String> {
//    private Map<String, String> records;
//    private List<String> sortedRecords;
//    private int capacity;
//    private int counter = 0;
//    private NameAddressPair[] addressBook;
//    private int actualSize;
//
//
//    public AddressBook(int capacity) {
//        this.records = new HashMap<>();
//        this.sortedRecords = new ArrayList<>();
//        this.capacity = capacity;
//        this.addressBook = new NameAddressPair[capacity];
//        this.actualSize = 0;
//    }
//
//    public boolean create(String firstName, String lastName, String address) {
//        String key = firstName + " " + lastName;
//        if (!records.containsKey(key)) {
//            if (actualSize >= addressBook.length) {
//                addressBook = Arrays.copyOf(addressBook, addressBook.length * 2);
//            }
//            records.put(key, address);
//            sortedRecords.add("First name: " + firstName + ", Last name: " + lastName + ", Address: " + address);
//            addressBook[actualSize] = new NameAddressPair(new NameAddressPair.Person(firstName, lastName), address);
//            actualSize++;
//            return true;
//        }
//        return false;
//    }
//
//    public boolean delete(String firstName, String lastName) {
//        String key = firstName + " " + lastName;
//        if (records.containsKey(key)) {
//            records.remove(key);
//            sortedRecords.removeIf(record -> record.startsWith("First name: " + firstName + ", Last name: " + lastName));
//            for (int i = 0; i < actualSize; i++) {
//                if (addressBook[i].person().getFirstName().equals(firstName) &&
//                        addressBook[i].person().getLastName().equals(lastName)) {
//                    System.arraycopy(addressBook, i + 1, addressBook, i, actualSize - i - 1);
//                    addressBook[--actualSize] = null; // Очистить последнюю позицию
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//
//    public boolean update(String firstName, String lastName, String newAddress) {
//        String key = firstName + " " + lastName;
//        if (records.containsKey(key)) {
//            records.put(key, newAddress);
//            for (int i = 0; i < sortedRecords.size(); i++) {
//                String record = sortedRecords.get(i);
//                if (record.startsWith("First name: " + firstName + ", Last name: " + lastName)) {
//                    sortedRecords.set(i, "First name: " + firstName + ", Last name: " + lastName + ", Address: " + newAddress);
//                    for (int j = 0; j < actualSize; j++) {
//                        if (addressBook[j].person().getFirstName().equals(firstName) &&
//                                addressBook[j].person().getLastName().equals(lastName)) {
//                            addressBook[j] = new NameAddressPair(new NameAddressPair.Person(firstName, lastName), newAddress);
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//
//    public String read(String firstName, String lastName) {
//        return records.get(firstName + " " + lastName);
//    }
//
//    public int size() {
//        return records.size();
//    }
//
//    public void sortedBy(SortOrder order) {
//        sortedRecords.sort((s1, s2) -> {
//            int cmp = s1.compareTo(s2);
//            return order == SortOrder.ASC ? cmp : -cmp;
//        });
//    }
//
//    @Override
//    public Iterator<String> iterator() {
//        return sortedRecords.iterator();
//    }
//
//    private class AddressBookIterator implements Iterator<String> {
//        private int current = 0;
//
//        @Override
//        public boolean hasNext() {
//            return current < actualSize;
//        }
//
//        @Override
//        public String next() {
//            NameAddressPair pair = addressBook[current++];
//            return "First name: " + pair.person().getFirstName() +
//                    ", Last name: " + pair.person().getLastName() +
//                    ", Address: " + pair.address();
//        }
//    }
//
//    private record NameAddressPair(Person person, String address) {
//        public static class Person {
//            private final String firstName;
//            private final String lastName;
//
//            public Person(String firstName, String lastName) {
//                this.firstName = firstName;
//                this.lastName = lastName;
//            }
//
//            public String getFirstName() {
//                return firstName;
//            }
//
//            public String getLastName() {
//                return lastName;
//            }
//
//            @Override
//            public boolean equals(Object o) {
//                if (this == o) return true;
//                if (o == null || getClass() != o.getClass()) return false;
//                Person person = (Person) o;
//                return firstName.equals(person.firstName) && lastName.equals(person.lastName);
//            }
//
//            @Override
//            public int hashCode() {
//                return Objects.hash(firstName, lastName);
//            }
//        }
//    }
//
//}
