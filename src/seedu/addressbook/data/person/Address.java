package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, #01-234, 123456";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must be in the format of BLOCK, STREET, UNIT, POSTAL CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+,.+,.+,.+";


    private final int BLOCK = 0;
    private final int STREET = 1;
    private final int UNIT = 2;
    private final int POSTALCODE = 3;


    public Block block;
    public Street street;
    public Unit unit;
    public PostalCode postalCode;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;

        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        processAddress(trimmedAddress);
    }

    /**
     * processes different components of address(block,street,unit,postal code)
     * @param trimmedAddress
     */
    public void processAddress(String trimmedAddress){
        String[] components = trimmedAddress.split(",");
        block = new Block(components[BLOCK].trim());
        street = new Street(components[STREET].trim());
        unit = new Unit(components[UNIT].trim());
        postalCode = new PostalCode(components[POSTALCODE].trim());

    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    /**
     * returns components of address in the form of BLOCK, STREET, UNIT, POSTAL CODE
     */
    public String getAddress(){
        return block.toString() + ", "  + street.toString() + ", " + unit.toString() + ", " + postalCode.toString();
    }

    @Override
    public String toString() {
        return getAddress();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && getAddress().equals(((Address) other).getAddress())); // state check
    }

    @Override
    public int hashCode() {
        return getAddress().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
