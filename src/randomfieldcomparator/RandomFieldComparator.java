package randomfieldcomparator;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * By default it compares only accessible fields, but this can be configured via a constructor property. If no field is
 * available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 */
public class RandomFieldComparator<T> implements Comparator<T> {

    private Class<T> type;
    private Field field;

    public RandomFieldComparator(Class<T> targetType) {
        this(targetType, true);
    }

    /**
     * A constructor that accepts a class and a property indicating which fields can be used for comparison. If property
     * value is true, then only public fields or fields with public getters can be used.
     *
     * @param targetType                  a type of objects that may be compared
     * @param compareOnlyAccessibleFields config property indicating if only publicly accessible fields can be used
     */
    public RandomFieldComparator(Class<T> targetType, boolean compareOnlyAccessibleFields) {
        type = targetType;
        List<Field> collect = Arrays.stream(type.getDeclaredFields())
                .filter(f -> Comparable.class.isAssignableFrom(f.getType()))
                .filter(f -> !compareOnlyAccessibleFields ||
                        compareOnlyAccessibleFields && Modifier.isPublic(f.getModifiers()) || isGetterExists(f.getName()))
                .collect(Collectors.toList());

        if (collect.isEmpty()) {
            throw new IllegalArgumentException("no required fields or getters found");
        }

        field = collect.get(new Random().nextInt(collect.size()));
        field.setAccessible(true);
    }

    private boolean isGetterExists(String name) {
        StringBuilder sb = new StringBuilder("get");
        sb.append(name.substring(0, 1).toUpperCase());
        sb.append(name.substring(1));
        try {
            type.getMethod(sb.toString());
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value grater than a non-null value (nulls last).
     */
    @Override
    @SneakyThrows
    public int compare(T o1, T o2) {
        Comparable c1 = (Comparable) field.get(o1);
        Comparable c2 = (Comparable) field.get(o2);

        if (c1 == null && c2 == null) {
            return 0;
        }
        if (c1 != null && c2 == null) {
            return -1;
        }
        if (c2 != null && c1 == null) {
            return 1;
        }
        return c1.compareTo(c2);

    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        return String.format("Random field comparator of class '%s' is comparing '%s'", type.getName(), field.getName());
    }
}